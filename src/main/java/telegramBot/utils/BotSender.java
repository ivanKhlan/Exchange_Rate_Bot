package telegramBot.utils;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class BotSender extends DefaultAbsSender {

    public BotSender() {
        super(new DefaultBotOptions());
    }


    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
