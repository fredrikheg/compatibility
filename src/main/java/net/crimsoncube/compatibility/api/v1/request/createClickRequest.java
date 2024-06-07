package net.crimsoncube.compatibility.api.v1.request;

public class createClickRequest {

    private Long userId;

    public createClickRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
