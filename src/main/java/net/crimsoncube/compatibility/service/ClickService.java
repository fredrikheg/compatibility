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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public Set<ClickDto> getClicks(String username) {

        return findClickDtosForUser(username);
    }

    public Set<ClickDto> increaseAndReturnClicks(String userName, Long clickerId) {
        try {
            Click click = clickRepository.findById(clickerId).orElseThrow(ClicksNotFoundException::new);
            click.setClicks(click.getClicks()+1);
            clickRepository.save(click);

            return findClickDtosForUser(userName);

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

        try {
            clickRepository.findByOwnerIdOrderById(user.getId()).orElseThrow(ClicksNotFoundException::new);
            // User already have a click, ignore.
            log.warn("User {} already have a clicker", userName);
        } catch (ClicksNotFoundException e) {
            createAndSaveNewClickForOwner(user);
            return true;
        }

        return false;
    }

    private Set<ClickDto> findClickDtosForUser(String userName) {

        Set<ClickDto> result = new HashSet<>();

        try {
            User user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
            Set<Click> clicks = clickRepository.findByOwnerIdOrderById(user.getId()).orElseThrow(ClicksNotFoundException::new);
            clicks.forEach(c -> result.add(ClickDto.fromClick(c)));

        } catch (UserNotFoundException e) {
            log.error("User {} not found getting clicks", userName);
        } catch (ClicksNotFoundException e) {
            log.error("No clicks found for user {}", userName);
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
}
