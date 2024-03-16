import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;

//import static TelegramBot.Client.PrivetClient.getDefaultCurrency;
import static Response.MonoBankResponse.*;

public class AppLauncher {

    public static void main(String[] args) throws TelegramApiException {
//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        botsApi.registerBot(new MyBot());
/*
         Метод getMonoBankCurrencyExchange приймає два аргумента:
            1. Валюту - "USD" або "EUR"
            2. Кількість знаків після коми - 2, 3, 4
 */
        System.out.println(getMonoBankCurrencyExchange("USD", 3));


    }
}
