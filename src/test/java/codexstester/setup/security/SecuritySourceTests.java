package codexstester.setup.security;

import codexstester.engine.dto.Oauth2RequestCheckTokenDto;
import codexstester.engine.dto.Oauth2RequestTokenDto;

public class SecuritySourceTests {

    /**
     * WARNING: DO NOT REMOVE THIS ATTRIBUTES
     * OAUTH2 SETTINGS
     * Change it as needed
     **/

    protected static Oauth2RequestTokenDto oauth2Token(String env) {

        Oauth2RequestTokenDto oauth2RequestTokenDto = new Oauth2RequestTokenDto();

        switch (env) {
            case "local":
                oauth2RequestTokenDto.setUrl("http://localhost:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("1234567890");
                return oauth2RequestTokenDto;
            case "dev":
                oauth2RequestTokenDto.setUrl("http://192.168.0.174:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("123123123");
                return oauth2RequestTokenDto;
            case "test":
            case "homolog":
                oauth2RequestTokenDto.setUrl("http://homolog.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("123456");
                return oauth2RequestTokenDto;
            case "prod":
                oauth2RequestTokenDto.setUrl("http://api.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("REST_USER");
                oauth2RequestTokenDto.setPass(null);
                return oauth2RequestTokenDto;
        }
        return null;
    }

    protected static Oauth2RequestCheckTokenDto oauth2CheckToken(String env, String token) {

        Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto = new Oauth2RequestCheckTokenDto();

        switch (env) {
            case "local":
                oauth2RequestCheckTokenDto.setUrl("http://localhost:32943/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                return oauth2RequestCheckTokenDto;
            case "dev":
                oauth2RequestCheckTokenDto.setUrl("http://192.168.0.174:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                return oauth2RequestCheckTokenDto;
            case "test":
            case "homolog":
                oauth2RequestCheckTokenDto.setUrl("http://homolog.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                return oauth2RequestCheckTokenDto;
            case "prod":
                oauth2RequestCheckTokenDto.setUrl("http://api.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                return oauth2RequestCheckTokenDto;
        }

        return null;
    }

}
