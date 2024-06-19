package net.crimsoncube.compatibility.api.v1.response;

import net.crimsoncube.compatibility.api.v1.response.exposed.DynamicComponent;

import java.util.ArrayList;
import java.util.List;

public class DynamicContentResponse {

    private List<DynamicComponent> content;

    public List<DynamicComponent> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return content;
    }

    public void setContent(List<DynamicComponent> content) {
        this.content = content;
    }
}
