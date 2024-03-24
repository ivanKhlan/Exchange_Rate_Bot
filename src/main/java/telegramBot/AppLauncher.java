package telegramBot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegramBot.extension.ExchangeRateBot;

@Slf4j
public class AppLauncher {


    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ExchangeRateBot());
            log.info("The Bot successfully started");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}
