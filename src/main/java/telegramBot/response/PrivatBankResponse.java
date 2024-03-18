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

public class PrivatBankResponse {
    private static List<PrivatBank> currencyExchange;

    public static String getPrivatBankCurrencyExchange(String userCurrency, int numberCharCurrency) {
        Gson gsonMapper = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PRIVAT_API))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            currencyExchange = gsonMapper.fromJson(response.body(), new TypeToken<List<PrivatBank>>() {}.getType());

            return displayCurrency(userCurrency, numberCharCurrency); // Викликати метод displayCurrency з отриманим кодом валюти та форматом числа
        } catch (IOException | InterruptedException e) {
            System.out.println("Error sending GET request: " + e.getMessage());
        }
        return "Error retrieving currency data.";
    }

    private static String displayCurrency(String userCurrency, int numberCharCurrency) {
        DecimalFormat decimalFormat = chooseDecimalFormat(numberCharCurrency);
        if (currencyExchange != null) {
            for (PrivatBank privatBank : currencyExchange) {
                if (userCurrency.equals(privatBank.getCcy())) {
                    return userCurrency + ": buy: " + decimalFormat.format(Double.parseDouble(privatBank.getBuy())) + " sell: " + decimalFormat.format(Double.parseDouble(privatBank.getSale()));
                }
            }
        }
        return "Currency with code " + userCurrency + " not found.";
    }

    // Метод для відображення кількості знаків після коми
    private static DecimalFormat chooseDecimalFormat(int numberCharCurrency) {
        return switch (numberCharCurrency) {
            case 3 -> new DecimalFormat(PATTERN_DECIMAL_FORMAT_3);
            case 4 -> new DecimalFormat(PATTERN_DECIMAL_FORMAT_4);
            default -> new DecimalFormat(PATTERN_DECIMAL_FORMAT_2);
        };
    }
}
