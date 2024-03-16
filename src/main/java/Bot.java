
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static final String BEGIN_MESSAGE = "\u041B\u0430\u0441\u043A\u0430\u0432\u043E \u043F\u0440\u043E\u0441\u0438\u043C\u043E. \u0426\u0435\u0439 \u0431\u043E\u0442 \u0434\u043E\u043F\u043E\u043C\u043E\u0436\u0435 \u0432\u0456\u0434\u0441\u043B\u0456\u0434\u043A\u043E\u0432\u0443\u0432\u0430\u0442\u0438 \u0430\u043A\u0442\u0443\u0430\u043B\u044C\u043D\u0456 \u043A\u0443\u0440\u0441\u0438 \u0432\u0430\u043B\u044E\u0442";
    public static final String GET_INFO = "\u041E\u0442\u0440\u0438\u043C\u0430\u0442\u0438 \u0406\u043D\u0444\u043E";
    public static final String SETTINGS = "\u041D\u0430\u043B\u0430\u0448\u0442\u0443\u0432\u0430\u043D\u043D\u044F";

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
            message.setText("Get settings");
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
