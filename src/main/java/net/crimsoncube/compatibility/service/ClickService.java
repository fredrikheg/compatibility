package net.crimsoncube.compatibility.service;

import net.crimsoncube.compatibility.entity.Click;
import net.crimsoncube.compatibility.repository.ClickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClickService {

    private final ClickRepository clickRepository;

    @Autowired
    public ClickService(ClickRepository clickRepository) {
        this.clickRepository = clickRepository;
    }

    public Integer getClicks(Long clickerId) {

        Optional<Click> click = clickRepository.findById(clickerId);
        if(click.isPresent()) {
            return click.get().getClicks();
        }

        return -1;
    }
}
