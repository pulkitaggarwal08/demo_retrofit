package com.demo_retorfit.com.eventbusmodels;

import com.google.gson.annotations.Expose;
/**
 * Created by pulkit on 11/3/18.
 */
public class TokenResponse {

    @Expose
    public String refresh_token;
    @Expose
    public String authorization;
}
