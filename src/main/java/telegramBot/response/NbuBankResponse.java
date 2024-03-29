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
public class NbuBankResponse {
    private static List<NbuBank> currencyExchange;

    public static String getNbuBank(String api, String bank, List<String> currencies, int numberCharCurrency) {
        NbuBankResponse.requestBank(api);
        String message = "\uD83D\uDD34" + " Exchange rate in the " + bank + ":\n\n";
        for(String currency: currencies) {
            message += displayCurrency(currency, numberCharCurrency);
        }
        return message;
    }

    private static List<NbuBank> requestBank(String api) {
        Gson gsonMapper = new GsonBuilder().setPrettyPrinting().create();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            currencyExchange = gsonMapper.fromJson(response.body(), new TypeToken<List<NbuBank>>() {
            }.getType());
        }catch (IOException | InterruptedException e) {
            System.out.println("Error sending GET request: " + e.getMessage());
        }
        return currencyExchange;
    }

    private static String displayCurrency(String userCurrency, int numberCharCurrency) {
        DecimalFormat decimalFormat = chooseDecimalFormat(numberCharCurrency);
        if (currencyExchange != null) {
            for (NbuBank nbuBank : currencyExchange) {
                if (userCurrency.equalsIgnoreCase(nbuBank.getCc())) {
                    return "\uD83D\uDCB1" + userCurrency.toUpperCase() + "/UAH \n" + "\uD83D\uDD3A" + " buy: " + decimalFormat.format(nbuBank.getRate()) + "\n" + "\uD83D\uDD3B" + " sell: "  + decimalFormat.format(nbuBank.getRate()) + "\n\n";
                }
            }
        }
        return "Currency with code " + userCurrency + " not found.";
    }

    // Метод для відображення кількості знаків після коми
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

}
