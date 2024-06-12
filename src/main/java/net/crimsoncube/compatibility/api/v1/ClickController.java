package net.crimsoncube.compatibility.api.v1;

import net.crimsoncube.compatibility.api.v1.request.ClickRequest;
import net.crimsoncube.compatibility.api.v1.request.createClickRequest;
import net.crimsoncube.compatibility.api.v1.response.ClickResponse;
import net.crimsoncube.compatibility.api.v1.response.MessageResponse;
import net.crimsoncube.compatibility.api.v1.response.exposed.ClickDto;
import net.crimsoncube.compatibility.entity.Click;
import net.crimsoncube.compatibility.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClickController {

    private final ClickService clickService;

    @Autowired
    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @PutMapping("/api/click/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createClick(Principal principal) {


        return ResponseEntity.ok(clickResponse);
    }

    @GetMapping("/api/click/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getClicks(Principal principal) {


        return ResponseEntity.ok(clickResponse);
    }

    @PostMapping("/api/click/click")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> click(@RequestBody ClickRequest request) {



        return ResponseEntity.ok(clickResponse);
    }

    @PostMapping("/api/click/reset")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reset(@RequestBody ClickRequest request) {


        return ResponseEntity.ok(clickResponse);
    }
}
