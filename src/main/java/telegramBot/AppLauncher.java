package telegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegramBot.extension.ExchangeRateBot;

import static telegramBot.response.MonoBankResponse.*;
import static telegramBot.response.NbuBankResponse.getNbuBankCurrencyExchange;
import static telegramBot.response.PrivatBankResponse.getPrivatBankCurrencyExchange;

public class AppLauncher {

    public static void main(String[] args) {
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new ExchangeRateBot());
//            System.out.println("The bot has been successfully launched. It is ready to receive messages");
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
        System.out.println(getMonoBankCurrencyExchange("eur", 3));
        System.out.println(getPrivatBankCurrencyExchange("uSd", 4));
        System.out.println(getNbuBankCurrencyExchange("uSd", 4));

    }
}
