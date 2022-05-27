import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RestAPIConverter {

    private static OkHttpClient client;
    private static Scanner input;
    private static String intro;
    private static String request;
    private static String error;
    private static String question;
    private static String outro;
    private static String url;

    public static void main(String[] args) {
        client = new OkHttpClient();
        input = new Scanner(System.in);
        intro = "Welcome to currency converter:";
        request = "Please enter an amount of Dollars to convert:";
        error = "Wrong input";
        question = "Would you like to start again? (type y or n)";
        outro = "Thanks for using our currency converter.";
        url = "http://api.exchangeratesapi.io/v1/latest?access_key=cff0d0360b396d00d0116a6c05d9bd18&symbols=USD,ILS";

        introScreen();
        requestScreen();
    }

    private static void getDataFromAPI(double amount) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String currencyData = response.body().string();

        JSONObject mainJsonObject = new JSONObject(currencyData);

        JSONObject resultsJSON = mainJsonObject.getJSONObject("rates");
        System.out.println(resultsJSON);
        double val = resultsJSON.getDouble("ILS");
        double calc = val * amount;
        System.out.println(amount + " USD is " + calc + " ILS");
    }

    private static void introScreen() {
        System.out.println(intro);
    }

    private static void requestScreen() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        double amount;
        while (true) {
            System.out.println(request);
            try {
                amount = Double.parseDouble(sc.next());
                if (!(amount <= 0)) {
                    getDataFromAPI(amount);
                    questionScreen();
                } else {
                    System.out.println(error);
                    requestScreen();
                }
                break; // will only get to here if input was a double

            } catch (InputMismatchException | IOException | NumberFormatException e) {
                System.out.println(error);
            }
        }
    }

    private static void questionScreen() throws InputMismatchException {
        System.out.println(question);
        String answer;
        answer = input.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            requestScreen();
        } else if (answer.equalsIgnoreCase("n")) {
            outroScreen();
        } else {
            System.out.println(error);
            questionScreen();
        }
    }

    private static void outroScreen() {
        System.out.println(outro);
        System.exit(0);
        }


}