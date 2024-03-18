package telegramBot.extension;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.handler.Handler;
import telegramBot.utils.BotConfig;

public class ExchangeRateBot extends TelegramLongPollingBot {
    private Handler handler = new Handler();

    @Override
    public void onUpdateReceived(Update update) {
        handler.messageHandler(update);
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
