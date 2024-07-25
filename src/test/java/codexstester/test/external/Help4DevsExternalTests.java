package codexstester.test.external;

import codexstester.engine.dto.Oauth2RequestTokenDto;
import codexstester.engine.dto.Oauth2ResponseTokenDto;
import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static codexstester.engine.security.SecurityTests.codexsTesterSecurityOAuth2Token;

public class Help4DevsExternalTests extends Help4DevsBridgeTests {

    public String oauth2Token(String env) {
        if (env == null || env.isEmpty()) env = externalProps.getProperty("external.tests.environment");
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token(env);
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        if (response.getBody() != null) return response.getBody().getAccess_token();
        return null;
    }

    @Test
    public void propsTest() {
        System.out.println(externalProps);
    }

}
