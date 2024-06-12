package net.crimsoncube.compatibility.service;

import net.crimsoncube.compatibility.api.v1.response.exposed.ClickDto;
import net.crimsoncube.compatibility.entity.Click;
import net.crimsoncube.compatibility.entity.User;
import net.crimsoncube.compatibility.repository.ClickRepository;
import net.crimsoncube.compatibility.repository.UserRepository;
import net.crimsoncube.compatibility.service.exception.ClickerNotFoundException;
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

    public Integer setClicks(Long clickerId, Integer clicks) {

        Optional<Click> click = clickRepository.findById(clickerId);
        if(click.isPresent()) {
            click.get().setClicks(clicks);
            clickRepository.save(click.get());
            return clicks;
        }
        return -1;
    }

    public Set<ClickDto> getClicks(String username) {

        User user;
        Set<ClickDto> response = new HashSet<>();

        try {
            user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        }catch (UserNotFoundException e) {
            log.error("Trying to get a click for missing user {}", username);
            return response;
        }

        try {
            Set<Click> clicks = clickRepository.findByOwnerId(user.getId()).orElseThrow(ClickerNotFoundException::new);
            clicks.forEach(c -> response.add(ClickDto.fromClick(c)));

        } catch (ClickerNotFoundException e) {
            response.add(createAndSaveNewClickForOwner(user));
        }
        return response;
    }

    public Click increaseAndReturnClick(Long clickerId) {
        try {
            Click click = clickRepository.findById(clickerId).orElseThrow(ClickerNotFoundException::new);
            click.setClicks(click.getClicks()+1);
            clickRepository.save(click);
            return click;
        } catch (ClickerNotFoundException e ) {
            log.warn("Clicker not found {}.", clickerId);
        }
        return null;
    }

    public void createClickForUser(Long userId) {

        User user;

        try {
            user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        } catch (UserNotFoundException e ) {
            // This should not happen in a natural flow
            log.error("Missing userID used in trying to create a new clicker: " + userId);
            return;
        }

        try {
            clickRepository.findByOwnerId(userId).orElseThrow(ClickerNotFoundException::new);
            // User already have a click, ignore.
            log.warn("User {} already have a clicker", userId);
        } catch (ClickerNotFoundException e) {
            createAndSaveNewClickForOwner(user);
        }
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
