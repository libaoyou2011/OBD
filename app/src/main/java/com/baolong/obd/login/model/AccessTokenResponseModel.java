package com.baolong.obd.login.model;

public class AccessTokenResponseModel {
    private String expires_in;
    private String token;
    private String token_type;

    public String getExpires_in() {
        return this.expires_in;
    }

    public String getToken() {
        return this.token;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setExpires_in(String paramString) {
        this.expires_in = paramString;
    }

    public void setToken(String paramString) {
        this.token = paramString;
    }

    public void setToken_type(String paramString) {
        this.token_type = paramString;
    }
}