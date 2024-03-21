package telegramBot.handler;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegramBot.banks.BankHandler;
import telegramBot.currency.CurrencyHandler;
import telegramBot.decimalPlaces.DecimalPlaces;
import telegramBot.notifications.Notifications;
import telegramBot.users.UsersData;


public class Handler {
    private MessageSender messageSender = new MessageSender();
    private UsersData usersData = new UsersData();
    private Notifications notifications = new Notifications();
    private DecimalPlaces decimalPlaces = new DecimalPlaces();
    private CurrencyHandler currencyHandler = new CurrencyHandler();
    private BankHandler bankHandler = new BankHandler();


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
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            case "\uD83D\uDCB2" + " Currencies":
                currencyHandler.createCurrencyMenu(chatId, usersData);
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
            case "USD":
                usersData.getUserById(chatId).get().getCurrencies().add("USD");
                currencyHandler.createCurrencyMenu(chatId, usersData);
                break;
            case "EUR":
                usersData.getUserById(chatId).get().getCurrencies().add("EUR");
                currencyHandler.createCurrencyMenu(chatId, usersData);
                break;
            case "\u2705" + " USD":
                usersData.getUserById(chatId).get().getCurrencies().remove("USD");
                currencyHandler.createCurrencyMenu(chatId, usersData);
                break;
            case "\u2705" + " EUR":
                usersData.getUserById(chatId).get().getCurrencies().remove("EUR");
                currencyHandler.createCurrencyMenu(chatId, usersData);
                break;
            case "NBU":
                usersData.getUserById(chatId).get().getBanks().add("NBU");
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            case "PrivatBank":
                usersData.getUserById(chatId).get().getBanks().add("PrivatBank");
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            case "Monobank":
                usersData.getUserById(chatId).get().getBanks().add("Monobank");
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            case "\u2705" + " NBU":
                usersData.getUserById(chatId).get().getBanks().remove("NBU");
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            case "\u2705" + " PrivatBank":
                usersData.getUserById(chatId).get().getBanks().remove("PrivatBank");
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            case "\u2705" + " Monobank":
                usersData.getUserById(chatId).get().getBanks().remove("Monobank");
                bankHandler.createBanksMenu(chatId, usersData);
                break;
            default:
                messageSender.sendResponse(chatId, "Sorry, your command is incorrect");
        }
    }

    private void handleNotification(long chatId, int time, String response){
        usersData.setNotificationTimeByUserId(chatId, time);
        notifications.applyNotification(chatId, usersData);
        messageSender.sendResponse(chatId, response);
    }
}
