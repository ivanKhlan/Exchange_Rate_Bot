package telegramBot.currency;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.users.UsersData;
import telegramBot.utils.BotSender;

import java.util.ArrayList;
import java.util.List;

public class CurrencyHandler {
    private BotSender botSender = new BotSender();
    public void createCurrencyMenu(long chatId, UsersData usersData){
        List<String> currencies = usersData.getUserById(chatId).get().getCurrencies();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Please choose a currency");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        if (currencies.contains("USD")){
            row1.add(EmojiParser.parseToUnicode("\u2705" + " USD"));
        }
        else {
            row1.add("USD");
        }

        if (currencies.contains("EUR")){
            row1.add(EmojiParser.parseToUnicode("\u2705" + " EUR"));
        }
        else {
            row1.add("EUR");
        }

        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(EmojiParser.parseToUnicode("\u2B05\uFE0F" + " Back"));
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
