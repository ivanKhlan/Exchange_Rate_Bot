package TelegramStart.settings;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class SettingsHandler {

    public static void handleSettings(Update update, long chatId) {
        String messageText = update.getMessage().getText();

        switch (messageText) {
            case "Decimal Places":
                sendResponse(chatId, "You selected Decimal Places setting.");
                break;
            case "Bank":
                sendResponse(chatId, "You selected Bank setting.");
                break;
            case "Currencies":
                sendResponse(chatId, "You selected Currencies setting.");
                break;
            case "Notification Time":
                sendResponse(chatId, "You selected Notification Time setting.");
                break;
            case "Back to Menu":
                sendStartMessage(chatId);
                break;
            default:
                sendResponse(chatId, "Invalid setting selection.");
                break;
        }
    }

    private static void sendResponse(long chatId, String response) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response);
    }

    private static void sendStartMessage(long chatId) {
    }
}