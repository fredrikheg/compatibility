package net.crimsoncube.compatibility.api.v1.response.exposed;

import java.util.ArrayList;
import java.util.List;

public class DynamicComponent {

    private String type;
    private String content;
    private String meta;
    private Long id;

    public DynamicComponent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // DEV ----------------------------------------------------
    public static List<DynamicComponent> stub() {

        List<DynamicComponent> result = new ArrayList<>();

        DynamicComponent text1 = DynamicComponent.text();
        text1.setContent("This is the first text");
        text1.setMeta("meta-text-1");
        text1.setId(1L);
        result.add(text1);

        DynamicComponent box1 = DynamicComponent.box();
        box1.setContent("This is the first box");
        box1.setMeta("meta-box-1");
        box1.setId(2L);
        result.add(box1);

        DynamicComponent text2 = DynamicComponent.text();
        text2.setContent("Second text component 2");
        text2.setMeta("meta-text-2");
        text2.setId(3L);
        result.add(text2);

        DynamicComponent text3 = DynamicComponent.text();
        text3.setContent("Third text component comping up.");
        text3.setMeta("meta-text-3");
        text3.setId(4L);
        result.add(text3);

        return result;
    }

    public static DynamicComponent box() {
        return new DynamicComponent("box");
    }

    public static DynamicComponent text() {
        return new DynamicComponent("text");
    }
}
