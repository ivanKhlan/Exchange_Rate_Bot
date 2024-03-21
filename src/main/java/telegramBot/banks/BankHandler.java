package telegramBot.banks;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.users.UsersData;
import telegramBot.utils.BotSender;

import java.util.ArrayList;
import java.util.List;
public class BankHandler {
    private BotSender botSender = new BotSender();
    public void createBanksMenu(long chatId, UsersData usersData){
        List<String> currencies = usersData.getUserById(chatId).get().getBanks();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Please choose a bank");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        if (currencies.contains("NBU")){
            row1.add(EmojiParser.parseToUnicode("\u2705" + " NBU"));
        }
        else {
            row1.add("NBU");
        }

        if (currencies.contains("PrivatBank")){
            row1.add(EmojiParser.parseToUnicode("\u2705" + " PrivatBank"));
        }
        else {
            row1.add("PrivatBank");
        }

        if (currencies.contains("Monobank")){
            row1.add(EmojiParser.parseToUnicode("\u2705" + " Monobank"));
        }
        else {
            row1.add("Monobank");
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