
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static final String BEGIN_MESSAGE = "Welcome. This bot will help you track current exchange rates.";
    public static final String GET_INFO = "Get info";
    public static final String SETTINGS = "Settings";

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        String chatId = update.getMessage().getChatId().toString();
        message.setChatId(chatId);

        if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/start")){
            message.setText(BEGIN_MESSAGE);
            message.setReplyMarkup(startButtons());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase(GET_INFO)){
            message.setText("Get exchange rates");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase(SETTINGS)){
            message.setText("Get info");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
    private ReplyKeyboard startButtons() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        KeyboardRow getInfo = new KeyboardRow();
        KeyboardRow setings = new KeyboardRow();
        getInfo.add(GET_INFO);
        setings.add(SETTINGS);
        keyboard.setKeyboard(List.of(getInfo,setings));
        return keyboard;
    }
 }
