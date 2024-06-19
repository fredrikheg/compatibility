package net.crimsoncube.compatibility.api.v1;

import net.crimsoncube.compatibility.api.v1.request.ClickRequest;
import net.crimsoncube.compatibility.api.v1.response.ClicksResponse;
import net.crimsoncube.compatibility.api.v1.response.MessageResponse;
import net.crimsoncube.compatibility.api.v1.response.exposed.ClickDto;
import net.crimsoncube.compatibility.service.ClickService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClickController {

    private static final Log log = LogFactory.getLog(ClickController.class);
    private final ClickService clickService;

    @Autowired
    public ClickController(ClickService clickService) {
        this.clickService = clickService;
    }

    @PutMapping("/api/click/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createClick(Principal principal) {

        if( clickService.createClickForUser(principal.getName()) ) {
            MessageResponse response = new MessageResponse("OK");
            return ResponseEntity.ok(response);
        } else {
            MessageResponse response = new MessageResponse("OK");
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/api/click/list")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getClicks(Principal principal) {

        ClicksResponse clickResponse = new ClicksResponse();
        clickResponse.setClicks( clickService.getClicks(principal.getName()));
        return ResponseEntity.ok(clickResponse);
    }

    @PostMapping("/api/click/click")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> click(Principal principal, @RequestBody ClickRequest request) {

        ClicksResponse clickResponse = new ClicksResponse();
        clickResponse.setClicks( clickService.increaseAndReturnClicks(principal.getName(), request.getId()) );

        return ResponseEntity.ok(clickResponse);
    }

    @PostMapping("/api/click/reset")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> reset(Principal principal, @RequestBody ClickRequest request) {

        ClicksResponse clickResponse = new ClicksResponse();

        clickResponse.setClicks(clickService.resetClicks(principal.getName(), request.getId()));

        return ResponseEntity.ok(clickResponse);
    }
}
