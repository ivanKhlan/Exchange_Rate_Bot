package telegramBot.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.utils.BotSender;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private BotSender botSender = new BotSender();
    public void sendStartMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Welcome to the Exchange Rate Bot!\n\nYou can use the following commands:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Get information");
        row1.add("Settings");
        keyboard.add(row1);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInfoMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("The functionality is still under development.");
        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendSettingsMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Please select a setting:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Decimal Places");
        row1.add("Bank");
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Currencies");
        row2.add("Notification Time");
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

    public void sendResponse(long chatId, String response) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response);
        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
