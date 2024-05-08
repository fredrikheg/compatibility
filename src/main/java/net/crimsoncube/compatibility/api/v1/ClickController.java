package net.crimsoncube.compatibility.api.v1;

import net.crimsoncube.compatibility.api.v1.response.ClickResponse;
import net.crimsoncube.compatibility.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/click")
public class ClickController {

    private final ClickService clickService;

    @Autowired
    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getClicks(@PathVariable Long clickerId) {

        ClickResponse response = new ClickResponse();
        response.setClicks(clickService.getClicks(clickerId));

        return ResponseEntity.ok(response);
    }
}
