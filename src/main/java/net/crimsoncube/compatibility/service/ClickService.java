package net.crimsoncube.compatibility.service;

import net.crimsoncube.compatibility.api.v1.response.exposed.ClickDto;
import net.crimsoncube.compatibility.entity.Click;
import net.crimsoncube.compatibility.entity.User;
import net.crimsoncube.compatibility.repository.ClickRepository;
import net.crimsoncube.compatibility.repository.UserRepository;
import net.crimsoncube.compatibility.service.exception.ClicksNotFoundException;
import net.crimsoncube.compatibility.service.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClickService {

    private static final Logger log = LogManager.getLogger(ClickService.class);
    private final ClickRepository clickRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClickService(ClickRepository clickRepository, UserRepository userRepository) {
        this.clickRepository = clickRepository;
        this.userRepository = userRepository;
    }

    public List<ClickDto> getClicks(String username) {

        return findClicksForUser(username);
    }

    public List<ClickDto> increaseAndReturnClicks(String userName, Long clickerId) {
        try {
            Click click = clickRepository.findById(clickerId).orElseThrow(ClicksNotFoundException::new);
            click.setClicks(click.getClicks()+1);
            clickRepository.save(click);

            return findClicksForUser(userName);

        } catch (ClicksNotFoundException e ) {
            log.warn("Clicker not found {}.", clickerId);
        }
        return null;
    }

    public boolean createClickForUser(String userName) {

        User user;

        try {
            user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);

        } catch (UserNotFoundException e ) {
            // This should not happen in a natural flow
            log.error("Missing userID used in trying to create a new clicker: " + userName);
            return false;
        }

        if(clickRepository.findClicksByOwner(user.getId()).isEmpty() ) {
            createAndSaveNewClickForOwner(user);
            return true;
            // User already have a click, ignore.

        } else {
            log.warn("User {} already have a clicker", userName);
        }
        return false;
    }

    private List<ClickDto> findClicksForUser(String userName) {

        List<ClickDto> result = new ArrayList<>();

        try {
            User user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
            List<Click> clicks =  clickRepository.findClicksByOwner(user.getId());
            clicks.iterator().forEachRemaining(c -> result.add(ClickDto.fromClick(c)));

        } catch (UserNotFoundException e) {
            log.error("User {} not found getting clicks", userName);
        }

        return result;
    }

    private ClickDto createAndSaveNewClickForOwner(User owner) {

        // Loose coupling, must be maintained
        Click click = new Click();

        click.setOwnerId(owner.getId());
        click.setClicks(0);

        click = clickRepository.save(click);

        ClickDto dto = new ClickDto();
        dto.setNumClicks(0);
        dto.setClickId(click.getId());

        return dto;
    }

    public List<ClickDto> resetClicks(String userName, Long clickId) {

        List<ClickDto> result = new ArrayList<>();

        try {
            User user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);

            Click click = clickRepository.findById(clickId).orElseThrow(ClicksNotFoundException::new);
            click.setClicks(0);
            clickRepository.save(click);


            List<Click> clicks =  clickRepository.findClicksByOwner(user.getId());
            clicks.iterator().forEachRemaining(c -> result.add(ClickDto.fromClick(c)));

        } catch (UserNotFoundException e) {
            log.error("User {} not found getting clicks", userName);
        } catch (ClicksNotFoundException e) {
            log.error("Click {} not found for user {}", clickId, userName);
        }

        return result;
    }
}
