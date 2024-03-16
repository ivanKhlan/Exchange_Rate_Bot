package TelegramStart;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            botsApi.registerBot(new MyTelegramBot());
            System.out.println("The bot has been successfully launched. It is ready to receive messages");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}