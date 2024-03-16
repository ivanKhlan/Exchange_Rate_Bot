package Response;

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

public class MonoBankResponse {
    private static final String MONO_API = "https://api.monobank.ua/bank/currency";
    private static final int MONOBANK_USD_CODE = 840;
    private static final int MONOBANK_EUR_CODE = 978;
    private static final String PATTERN_DECIMAL_FORMAT_2 = "#0.00";
    private static final String PATTERN_DECIMAL_FORMAT_3 = "#0.000";
    private static final String PATTERN_DECIMAL_FORMAT_4 = "#0.0000";
    public static List<MonoBank> currencyExchange;

    public static String getMonoBankCurrencyExchange(String currency, int numberCharCurrency) {
        Gson gsonMapper = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MONO_API))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            currencyExchange = gsonMapper.fromJson(response.body(), new TypeToken<List<MonoBank>>() {}.getType());

            int currencyCode = getMonoBankCurrencyCode(currency); // Отримати код валюти з рядка currency
            return displayCurrency(currencyCode, numberCharCurrency); // Викликати метод displayCurrency з отриманим кодом валюти та форматом числа
        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending GET request: " + e.getMessage());
        }
        return "Error retrieving currency data.";
    }

    public static String displayCurrency(int currencyCode, int numberCharCurrency) {
        DecimalFormat decimalFormat = chooseDecimalFormat(numberCharCurrency);
        if (currencyExchange != null) {
            for (MonoBank monoBank : currencyExchange) {
                if (monoBank.getCurrencyCodeA() == currencyCode) {
                    String currencyName = getCurrencyName(currencyCode);
                    return currencyName + ": buy: " + decimalFormat.format(monoBank.getRateSell()) + " sell: " + decimalFormat.format(monoBank.getRateBuy());
                }
            }
        }
        return "Currency with code " + currencyCode + " not found.";
    }

    private static DecimalFormat chooseDecimalFormat(int numberCharCurrency) {
        switch (numberCharCurrency) {
            case 2:
                return new DecimalFormat(PATTERN_DECIMAL_FORMAT_2);
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
            case MONOBANK_USD_CODE:
                return "USD";
            case MONOBANK_EUR_CODE:
                return "EUR";
            default:
                return "Unknown currency";
        }
    }

    private static int getMonoBankCurrencyCode(String currency) {
        switch (currency) {
            case "USD":
                return MONOBANK_USD_CODE;
            case "EUR":
                return MONOBANK_EUR_CODE;
            default:
                return -1;
        }
    }
}