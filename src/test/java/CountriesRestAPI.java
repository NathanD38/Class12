import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class CountriesRestAPI {
    public static void main(String[] args) throws IOException, JSONException {
        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://restcountries.com/v2/name/israel")
                .build();
        System.out.println(request);
        Response response = client.newCall(request).execute();
        System.out.println(response);
        String jsonData = Objects.requireNonNull(response.body()).toString();
        System.out.println(jsonData);
        // parse JSON
        JSONObject mainJsonObject = new JSONObject(jsonData);
        System.out.println(mainJsonObject);
//        // get Json object
//        JSONObject resultsJson = mainJsonObject.getJSONObject("rates");
//        System.out.println(resultsJson);
//        // get value
//        double val = resultsJson.getDouble("ILS");
//        System.out.println(val);
    }
}