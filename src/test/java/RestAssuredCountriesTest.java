import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.get;

public class RestAssuredCountriesTest {

    private static String url;
    private static String value;
    private static JSONArray countryName;
    private static String borders;
    private static String symbol;


    @BeforeClass
    public static void runOnceBeforeClass() {
        url = "https://restcountries.com/v3.1/name/israel";
    }


    @Test
    public void test01_getBody() {
        ArrayList<String> region = get(url).then().extract().path("region");
        System.out.println(region);
        ArrayList<String> borders = get(url).then().extract().path("borders");
        System.out.println(borders);
        ArrayList<String> symbol = get(url).then().extract().path("currencies.ILS.symbol");
        System.out.println(symbol);
        String modifiedRegion = region.toString().replace("[","").replace("]", "");
        String modifiedBorders = borders.toString().replace("[[","").replace("]]", "");
        String modifiedSymbol = symbol.toString().replace("[","").replace("]", "");;
        System.out.println("Israel's region is " + modifiedRegion + ", its borders are " + modifiedBorders + ", and its currency symbol is " + modifiedSymbol + ".");
    }
}
