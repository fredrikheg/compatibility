package net.crimsoncube.compatibility.api.v1.response;

import net.crimsoncube.compatibility.api.v1.response.exposed.ClickDto;

import java.util.HashSet;
import java.util.Set;

public class ClicksResponse {

    private Set<ClickDto> clicks;

    public Set<ClickDto> getClicks() {
        if(clicks == null) {
            clicks = new HashSet<>();
        }
        return clicks;
    }

    public void setClicks(Set<ClickDto> clicks) {
        this.clicks = clicks;
    }
}
