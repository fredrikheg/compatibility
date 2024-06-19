package net.crimsoncube.compatibility.api.v1.response;

import net.crimsoncube.compatibility.api.v1.response.exposed.ClickDto;

import java.util.*;

public class ClicksResponse {

    private List<ClickDto> clicks;

    public List<ClickDto> getClicks() {
        if(clicks == null) {
            clicks = new ArrayList<>();
        }
        return clicks;
    }

    public void setClicks(List<ClickDto> clicks) {
        this.clicks = clicks;
    }
}
