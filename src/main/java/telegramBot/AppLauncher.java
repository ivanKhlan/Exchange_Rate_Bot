package telegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegramBot.extension.ExchangeRateBot;

public class AppLauncher {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ExchangeRateBot());
            System.out.println("The bot has been successfully launched. It is ready to receive messages");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
