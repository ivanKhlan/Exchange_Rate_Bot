package telegramBot.handler;

import jdk.dynalink.linker.LinkerServices;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.notifications.Notifications;
import telegramBot.users.UserSettings;
import telegramBot.users.UsersData;
import telegramBot.currency.CurrencyManager;

import java.util.List;


public class Handler {
    private MessageSender messageSender = new MessageSender();
    private UsersData usersData = new UsersData();
    private Notifications notifications = new Notifications();
    private CurrencyManager currencyManager = new CurrencyManager();


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
                usersData.addUser(chatId);
                break;
            case "Get information":
                messageSender.sendInfoMessage(chatId, usersData);
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
                messageSender.sendCurrencyOptions(chatId, currencyManager.getCurrenciesWithBackButton());
                break;
            case "Notification Time":
                notifications.createNotificationMenu(chatId);
                break;
            case "Back":
                messageSender.sendSettingsMessage(chatId);
                break;
            case "Back To Main Menu":
                messageSender.sendStartMessage(chatId);
                break;
            case "09:00":
                handleNotification(chatId, 9, "You will receive notification at 09:00");
                break;
            case "10:00":
                handleNotification(chatId, 10, "You will receive notification at 10:00");
                break;
            case "11:00":
                handleNotification(chatId, 11, "You will receive notification at 11:00");
                break;
            case "12:00":
                handleNotification(chatId, 12, "You will receive notification at 12:00");
                break;
            case "13:00":
                handleNotification(chatId, 13, "You will receive notification at 13:00");
                break;
            case "14:00":
                handleNotification(chatId, 14, "You will receive notification at 14:00");
                break;
            case "15:00":
                handleNotification(chatId, 15, "You will receive notification at 15:00");
                break;
            case "16:00":
                handleNotification(chatId, 16, "You will receive notification at 16:00");
                break;
            case "17:00":
                handleNotification(chatId, 17, "You will receive notification at 17:00");
                break;
            case "18:00":
                handleNotification(chatId, 18, "You will receive notification at 18:00");
                break;
            case "Don`t receive notifications":
                handleNotification(chatId, 0, "You will not receive notifications");
                break;
            default:

                if (messageText.startsWith("Select currency:")) {
                    String currencyCode = messageText.substring(messageText.indexOf(":") + 2);
                    if (currencyCode.equals("Back")) {
                        messageSender.sendSettingsMessage(chatId);
                    } else {
                        currencyManager.toggleCurrencySelection(currencyCode);
                        messageSender.sendCurrencyOptions(chatId, currencyManager.getCurrenciesWithBackButton());
                    }
                } else {
                    messageSender.sendResponse(chatId, "Invalid setting selection.");
                }
                break;
        }
    }

    private void handleNotification(long chatId, int time, String response){
        usersData.setNotificationTimeByUserId(chatId, time);
        notifications.applyNotification(chatId, usersData);
        messageSender.sendResponse(chatId, response);
    }
}
