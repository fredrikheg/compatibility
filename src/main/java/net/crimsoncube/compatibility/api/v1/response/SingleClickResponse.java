package net.crimsoncube.compatibility.api.v1.response;

public class SingleClickResponse {

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
}
