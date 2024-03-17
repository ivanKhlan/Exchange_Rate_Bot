package telegramBot.handler;

import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.handler.MessageSender;

public class Handler {
    private MessageSender messageSender = new MessageSender();

    public void messageHandler(Update update){
        if (update.hasMessage() && update.getMessage().hasText()) {
            textMessageHandler(update);
        }
    }


    private void textMessageHandler(Update update){
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        switch (messageText) {
            case "/start":
                messageSender.sendStartMessage(chatId);
                break;
            case "Get information":
                messageSender.sendInfoMessage(chatId);
                break;
            case "Settings":
                messageSender.sendSettingsMessage(chatId);
                break;
            case "Decimal Places":
                messageSender.sendResponse(chatId, "You selected Decimal Places setting.");
                break;
            case "Bank":
                messageSender.sendResponse(chatId, "You selected Bank setting.");
                break;
            case "Currencies":
                messageSender.sendResponse(chatId, "You selected Currencies setting.");
                break;
            case "Notification Time":
                messageSender.sendResponse(chatId, "You selected Notification Time setting.");
                break;
            case "Back":
                messageSender.sendStartMessage(chatId);
                break;
            default:
                messageSender.sendResponse(chatId, "Invalid setting selection.");
                break;
        }

    }


}
