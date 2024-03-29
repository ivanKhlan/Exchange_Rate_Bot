package telegramBot.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.List;

import static telegramBot.response.BankConstants.*;

public class MonoBankResponse {


    private static List<MonoBank> currencyExchange;
    public static String getMonoBank(String api, String bank, List<String> currencies, int numberCharCurrency) {
        MonoBankResponse.requestBank(api);
        String message = "\uD83D\uDD34" + " Exchange rate in the " + bank + ":\n\n";

        for(String currency: currencies) {
            message += displayCurrency(currency, numberCharCurrency);
        }
        return message;
    }

    private static List<MonoBank> requestBank(String api) {
        Gson gsonMapper = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            currencyExchange = gsonMapper.fromJson(response.body(), new TypeToken<List<MonoBank>>() {
            }.getType());
        }catch (IOException | InterruptedException e) {
            System.out.println("Error sending GET request: " + e.getMessage());
        }
        return currencyExchange;
    }


    public static String displayCurrency(String currency, int numOfCharacters) {
        DecimalFormat decimalFormat = chooseDecimalFormat(numOfCharacters);
        if (currencyExchange != null) {
            for (MonoBank monoBank : currencyExchange) {
                if (monoBank.getCurrencyCodeA() == getMonoBankCurrencyCode(currency)) {
                    String currencyName = getCurrencyName(getMonoBankCurrencyCode(currency));
                    return "\uD83D\uDCB1" + currencyName + "/UAH \n" + "\uD83D\uDD3A" + " buy: " + decimalFormat.format(monoBank.getRateSell()) + "\n" + "\uD83D\uDD3B" + " sell: " + decimalFormat.format(monoBank.getRateBuy()) + "\n\n";
                }
            }
        }
        return "Currency with code " + currency + " not found.";
    }

    private static DecimalFormat chooseDecimalFormat(int numberCharCurrency) {
        switch (numberCharCurrency) {
            case 1:
                return new DecimalFormat(PATTERN_DECIMAL_FORMAT_1);
            case 3:
                return new DecimalFormat(PATTERN_DECIMAL_FORMAT_3);
            case 4:
                return new DecimalFormat(PATTERN_DECIMAL_FORMAT_4);
            default:
                return new DecimalFormat(PATTERN_DECIMAL_FORMAT_2);
        }
    }

    private static String getCurrencyName(int currencyCode) {
        switch (currencyCode) {
            case USD_CODE:
                return "USD";
            case EUR_CODE:
                return "EUR";
            default:
                return "Unknown currency";
        }
    }

    private static int getMonoBankCurrencyCode(String currency) {
        switch (currency.toLowerCase()) {
            case "usd":
                return USD_CODE;
            case "eur":
                return EUR_CODE;
            default:
                return -1;
        }
    }
}