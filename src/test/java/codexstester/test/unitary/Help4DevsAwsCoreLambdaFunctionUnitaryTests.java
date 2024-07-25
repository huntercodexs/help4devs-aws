package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.huntercodexs.demo.lambda.function.Help4DevsAwsCoreLambdaFunction;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

public class Help4DevsAwsCoreLambdaFunctionUnitaryTests extends Help4DevsBridgeTests {

    Help4DevsAwsCoreLambdaFunction handler;

    @Test
    public void handleRequestContextNullTest() {
        handler = new Help4DevsAwsCoreLambdaFunction();
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("POST");
        request.setHeaders(new HttpHeaders().toSingleValueMap());
        request.setBody("{\"test\": true}");
        request.setPath("/api/test");
        APIGatewayProxyResponseEvent result = handler.handleRequest(request, null);
        codexsTesterAssertText("{\"result\": false}",  result.getBody());
    }

}
