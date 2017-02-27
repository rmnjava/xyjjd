package com.redstar.jjd.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * extends UsernamePasswordToken for captcha
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    // private String captcha;

    // public String getCaptcha() {
    //
    // return captcha;
    //
    // }
    //
    // public void setCaptcha(String captcha) {
    //
    // this.captcha = captcha;
    //
    // }

    public UsernamePasswordCaptchaToken() {

        super();

    }

    public UsernamePasswordCaptchaToken(String username, char[] password,
            boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public UsernamePasswordCaptchaToken(String username, String password,
            boolean rememberMe, String host) {
        this(username, password == null ? null : password.toCharArray(),
                rememberMe, host);
    }

}
