package net.crimsoncube.compatibility.api.v1.response.exposed;

import net.crimsoncube.compatibility.entity.Click;

public class ClickDto {

    private Integer numClicks;
    private Long clickId;

    public Integer getNumClicks() {
        return numClicks;
    }

    public void setNumClicks(Integer numClicks) {
        this.numClicks = numClicks;
    }

    public Long getClickId() {
        return clickId;
    }

    public void setClickId(Long clickId) {
        this.clickId = clickId;
    }

    public static ClickDto fromClick(Click click) {
        ClickDto dto = new ClickDto();
        dto.setClickId(click.getId());
        dto.setNumClicks(click.getClicks());
        return dto;
    }
}
