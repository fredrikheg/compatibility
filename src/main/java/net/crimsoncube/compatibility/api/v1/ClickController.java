package net.crimsoncube.compatibility.api.v1;

import net.crimsoncube.compatibility.api.v1.request.ClickRequest;
import net.crimsoncube.compatibility.api.v1.response.ClickResponse;
import net.crimsoncube.compatibility.api.v1.response.MessageResponse;
import net.crimsoncube.compatibility.entity.Click;
import net.crimsoncube.compatibility.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClickController {

    private final ClickService clickService;

    @Autowired
    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @GetMapping("/api/click/{clickerId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getClicks(@PathVariable Long clickerId) {

        ClickResponse clickResponse = new ClickResponse();
        clickResponse.setClicks(clickService.getClicks(clickerId));

        return ResponseEntity.ok(clickResponse);
    }

    @PostMapping("/api/click/click")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> click(@RequestBody ClickRequest request) {

        Integer clicks = clickService.increaseAndReturnClicks(request.getId());

        ClickResponse clickResponse = new ClickResponse();
        clickResponse.setClicks(clicks);

        return ResponseEntity.ok(clickResponse);
    }

    @PostMapping("/api/click/reset")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reset(@RequestBody ClickRequest request) {

        Integer clicks = clickService.setClicks(request.getId(), 0);
        ClickResponse clickResponse = new ClickResponse();
        clickResponse.setClicks(clicks);

        return ResponseEntity.ok(clickResponse);
    }
}
