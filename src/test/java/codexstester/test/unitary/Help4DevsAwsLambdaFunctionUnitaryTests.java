package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.lambda.function.Help4DevsAwsLambdaFunction;
import org.junit.Test;

public class Help4DevsAwsLambdaFunctionUnitaryTests extends Help4DevsBridgeTests {

    Help4DevsAwsLambdaFunction function;

    @Test
    public void reverseSimpleTest() {
        function = new Help4DevsAwsLambdaFunction();
        System.out.println(function.reverseSimple("abc"));
    }

}
