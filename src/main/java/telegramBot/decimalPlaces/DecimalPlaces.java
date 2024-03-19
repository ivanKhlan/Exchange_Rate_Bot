package telegramBot.decimalPlaces;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.handler.MessageSender;
import telegramBot.users.UsersData;
import telegramBot.utils.BotSender;

import java.util.ArrayList;
import java.util.List;

public class DecimalPlaces {
    private BotSender botSender = new BotSender();
    private MessageSender messageSender = new MessageSender();
    public void createDecimalPlacesMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Select the number of decimal places");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("1");
        row1.add("2");
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add("3");
        row2.add("4");
        keyboard.add(row2);

        KeyboardRow row3 = new KeyboardRow();
        row3.add("Back");
        keyboard.add(row3);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void changeDecimalPlace (int place, UsersData usersData , long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("You set decimal place " + place);
        System.out.println(usersData.getUserById(chatId).get().getNumOfCharacters());
        usersData.getUserById(chatId).get().setNumOfCharacters(place);
        System.out.println(usersData.getUserById(chatId).get().getNumOfCharacters());
        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}
