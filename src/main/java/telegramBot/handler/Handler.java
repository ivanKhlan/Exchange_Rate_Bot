package telegramBot.handler;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.banks.Banks;
import telegramBot.decimalPlaces.DecimalPlaces;
import telegramBot.notifications.Notifications;
import telegramBot.users.UsersData;
import telegramBot.currency.CurrencyManager;


public class Handler {
    private MessageSender messageSender = new MessageSender();
    private UsersData usersData = new UsersData();
    private Notifications notifications = new Notifications();
    private Banks banks = new Banks();
    private CurrencyManager currencyManager = new CurrencyManager();
    private DecimalPlaces decimalPlaces = new DecimalPlaces();


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
            case "Get information " + "\uD83D\uDDC8":
                messageSender.sendInfoMessage(chatId, usersData);
                break;
            case "Settings " + "\u2699\ufe0f":
                messageSender.sendSettingsMessage(chatId);
                break;
            case "\u0023\uFE0F\u20E3" + " Decimal Places":
                decimalPlaces.createDecimalPlacesMenu(chatId);
                break;
            case "\uD83C\uDFE6" + " Bank":
                banks.createBanksMenu(chatId);
                break;
            case "NBU":
                banks.changeBank("NBU", usersData, chatId);
                break;
            case "PrivatBank":
                banks.changeBank("PrivatBank", usersData, chatId);
                break;
            case "Monobank":
                banks.changeBank("Monobank", usersData, chatId);
                break;
            case "\uD83D\uDCB2" + " Currencies":
                messageSender.sendCurrencyOptions(chatId, currencyManager.getCurrenciesWithBackButton());
                break;
            case "\u23F0" + " Notification Time":
                notifications.createNotificationMenu(chatId);
                break;
            case "\u2B05\uFE0F" + " Back":
                messageSender.sendSettingsMessage(chatId);
                break;
            case "\uD83C\uDFE0" + " Back To Main Menu":
                messageSender.sendStartMessage(chatId);
                break;
            case "09:00":
                handleNotification(chatId, 9, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 09:00"));
                break;
            case "10:00":
                handleNotification(chatId, 10, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 10:00"));
                break;
            case "11:00":
                handleNotification(chatId, 11, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 11:00"));
                break;
            case "12:00":
                handleNotification(chatId, 12, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 12:00"));
                break;
            case "13:00":
                handleNotification(chatId, 13, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 13:00"));
                break;
            case "14:00":
                handleNotification(chatId, 14, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 14:00"));
                break;
            case "15:00":
                handleNotification(chatId, 15, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 15:00"));
                break;
            case "16:00":
                handleNotification(chatId, 16, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 16:00"));
                break;
            case "17:00":
                handleNotification(chatId, 17, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 17:00"));
                break;
            case "18:00":
                handleNotification(chatId, 18, EmojiParser.parseToUnicode("\u2705" + " You will receive notification at 18:00"));
                break;
            case "\u274C" + " Don`t receive notifications":
                handleNotification(chatId, 0, EmojiParser.parseToUnicode("\u274C" + " You will not receive notifications"));
                break;
            case "1":
                decimalPlaces.changeDecimalPlace(1,usersData,chatId);
                break;
            case "2":
                decimalPlaces.changeDecimalPlace(2,usersData,chatId);
                break;
            case "3":
                decimalPlaces.changeDecimalPlace(3,usersData,chatId);
                break;
            case "4":
                decimalPlaces.changeDecimalPlace(4,usersData,chatId);
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
