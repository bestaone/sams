package cn.webestar.sams.basic.common.session;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SessionContext implements Serializable {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime expire;
    private Authentication auth;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }

    public Authentication getAuth() {
        return auth;
    }

    public void setAuth(Authentication auth) {
        this.auth = auth;
    }

}
