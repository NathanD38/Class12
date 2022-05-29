import io.restassured.RestAssured;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

public class ResAssuredConverterTest {
    private static String url;
    private static String apiKey;
    private static String base;
    private static float value;
    private static float calc;
    private static String intro;
    private static String outro;

    @BeforeClass
    public static void runOnceBeforeClass() {
        url = "https://api.apilayer.com/exchangerates_data/latest?apikey=TkSh053jfBBxFBWom9Tggy58wPhOXdN7&base=USD";
//        apiKey = "TkSh053jfBBxFBWom9Tggy58wPhOXdN7";
//        base = "USD";
        intro = "Welcome to currency converter:";
        outro = "Thanks for using our currency converter.";
    }


    @Test
    public void test01_getBody() {
        value = get(url).body().path("rates.ILS");
        System.out.println("1 USD is " + value + " ILS");
    }

    @Test
    public void test02_validateResponse() {
        get(url).then().assertThat()
                .statusCode(HttpStatus.SC_OK). //make sure response is OK
        body("rates.ILS", equalTo(3.34467f)); //validate value
    }

    @Test
    public void test03_convertResponse() {
        System.out.println(intro);
        float amount = 100f;
        calc = value*amount;
        System.out.println(amount + " USD is " + calc + " ILS");
        System.out.println(outro);
    }
}
