package telegramBot.banks;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.handler.MessageSender;
import telegramBot.users.UsersData;
import telegramBot.utils.BotSender;

import java.util.ArrayList;
import java.util.List;

public class Banks {

    private BotSender botSender = new BotSender();
    private MessageSender messageSender = new MessageSender();
    public void createBanksMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Select the bank");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("NBU");
        row1.add("PrivatBank");
        row1.add("Monobank");
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
    public void changeBank (String bank, UsersData usersData, long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("You set " + bank);
        List<String> banks = usersData.getUserById(chatId).get().getBanks();
        if (banks.contains(bank)) {
            usersData.getUserById(chatId).get().removeBank(bank);
            messageSender.sendResponse(chatId, "Bank " + bank + " has been removed.");
        } else {
            usersData.getUserById(chatId).get().addBank(bank);
            messageSender.sendResponse(chatId, "Bank " + bank + " has been added.");
        }
        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


}






