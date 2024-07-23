package codexstester.engine.security;

import codexstester.engine.dto.Oauth2RequestCheckTokenDto;
import codexstester.engine.dto.Oauth2RequestTokenDto;
import codexstester.setup.security.SecuritySourceTests;

public abstract class SecurityTests extends SecuritySourceTests {

    /**
     * DO NOT REMOVE THIS METHOD
     * Change this method function before use it
     * */

    public static Oauth2RequestTokenDto codexsTesterSecurityOAuth2Token(String env) {
        return oauth2Token(env);
    }

    public static Oauth2RequestCheckTokenDto codexsTesterSecurityOAuth2CheckToken(String env, String token) {
        return oauth2CheckToken(env, token);
    }

}
