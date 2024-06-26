package net.crimsoncube.compatibility.api.v1;

import com.google.gson.Gson;
import net.crimsoncube.compatibility.api.v1.response.DynamicContentResponse;
import net.crimsoncube.compatibility.api.v1.response.exposed.DynamicComponent;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {


    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public String getUserContent(Principal principal) {
        // This needs to be dynamic content for the current user.
        // A list of descriptions of current questions.
        return "Questions for you, " + principal.getName();
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String getModeratorContent() {
        return "Moderator content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminContent() {
        return "Admin content";
    }


    @GetMapping("/dynamic")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getDynamicContent() {

        DynamicContentResponse resp = new DynamicContentResponse();
        resp.setContent(DynamicComponent.stub());
        return ResponseEntity.ok(resp);
    }
}
