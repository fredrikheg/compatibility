package net.crimsoncube.compatibility.service;

import net.crimsoncube.compatibility.entity.Click;
import net.crimsoncube.compatibility.repository.ClickRepository;
import net.crimsoncube.compatibility.service.exception.ClickerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClickService {

    private final ClickRepository clickRepository;

    @Autowired
    public ClickService(ClickRepository clickRepository) {
        this.clickRepository = clickRepository;
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

    public Integer getClicks(Long clickerId) {

        try {
            Click click = clickRepository.findById(clickerId).orElseThrow(ClickerNotFoundException::new);
            return click.getClicks();

        } catch (ClickerNotFoundException e) {
            Click click = new Click();
            click.setId(clickerId);
            click.setClicks(0);
            clickRepository.save(click);
            return 0;
        }
    }

    public Integer increaseAndReturnClicks(Long clickerId) {
        Optional<Click> clickResult = clickRepository.findById(clickerId);

        if(clickResult.isPresent()) {
            Click click = clickResult.get();
            click.setClicks(click.getClicks()+1);
            clickRepository.save(click);
            return click.getClicks();
        }
        return -3;
    }
}
