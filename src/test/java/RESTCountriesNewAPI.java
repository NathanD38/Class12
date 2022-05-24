import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class RESTCountriesNewAPI {
    private static OkHttpClient client;
    private static Scanner input;
    private static String message;
    private static String url;

    public static void main(String[] args) throws IOException {

        client = new OkHttpClient();
        input = new Scanner(System.in);
        message = "Enter a country name or type exit:";
        url = "https://restcountries.com/v3.1/name/";

        System.out.println(message);
        String answer = input.nextLine();
        getDataFromAPI(answer);
    }

    private static void getDataFromAPI(String answer) throws IOException {
        Request request = new Request.Builder()
                .url(url + answer)
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() == 404) {
            System.out.println("No country by that name was found in our database!");
            reRun();
        } else {
            String countriesData = response.body().string();

            JSONArray mainJsonArray = new JSONArray(countriesData);
            JSONObject mainJsonObject = (JSONObject) mainJsonArray.get(0);
            String region = mainJsonObject.getString("region");
            JSONArray borders = mainJsonObject.getJSONArray("borders");

            JSONObject currencies = mainJsonObject.getJSONObject("currencies");
            String currency = String.valueOf(currencies.toMap().values()).replace("}]","").replace("[{",""); //mapping the inner object
            //extracts the currency symbol with the name - no other way around it that I could find.
            String symbol = currency.substring(9);


            System.out.println(answer + "'s region is " + region + ", its borders are " + borders + ", and its currency symbol is " + symbol + ".");
            reRun();
        }
    }



    private static void reRun() throws IOException {
        System.out.println(message);
        String answer = input.nextLine();
        if (!answer.equalsIgnoreCase("exit")) {
            getDataFromAPI(answer);
        } else {
            System.exit(0);
        }
    }

}
