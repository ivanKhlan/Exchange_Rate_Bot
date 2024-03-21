package telegramBot.handler;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.response.MonoBankResponse;
import telegramBot.response.NbuBankResponse;
import telegramBot.response.PrivatBankResponse;
import telegramBot.users.UsersData;
import telegramBot.utils.BotSender;
import telegramBot.currency.Currency;

import java.util.ArrayList;
import java.util.List;

public class MessageSender {
    private BotSender botSender = new BotSender();


    public void sendStartMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(EmojiParser.parseToUnicode("Welcome to the Exchange Rate Bot!" + "\uD83D\uDC4B" + "\n\n" + "You can use the following commands" + "\uD83D\uDC47"));

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(EmojiParser.parseToUnicode("Get information " + "\uD83D\uDDC8"));
        row1.add(EmojiParser.parseToUnicode("Settings " + "\u2699\ufe0f"));
        keyboard.add(row1);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendInfoMessage(long chatId, UsersData usersData) {

        List<String> currencies = usersData.getUserById(chatId).get().getCurrencies();
        List<String> banks = usersData.getUserById(chatId).get().getBanks();
        int numOfCharacters = usersData.getUserById(chatId).get().getNumOfCharacters();


        StringBuilder prettyText = new StringBuilder();

        for (int i = 0; i < banks.size(); i++) {

            if (banks.get(i).equals("mono")) {
                for (int j = 0; j < currencies.size(); j++) {
                    prettyText.append(MonoBankResponse.getMonoBankCurrencyExchange(currencies.get(j), numOfCharacters));
                }

            } else if (banks.get(i).equals("privat")) {
                for (int j = 0; j < currencies.size(); j++) {
                    prettyText.append(PrivatBankResponse.getPrivatBankCurrencyExchange(currencies.get(j), numOfCharacters));
                }

            } else if (banks.get(i).equals("nbu")) {
                for (int j = 0; j < currencies.size(); j++) {
                    prettyText.append(NbuBankResponse.getNbuBankCurrencyExchange(currencies.get(j), numOfCharacters));
                }
            }
        }

        SendMessage responseMessage = new SendMessage();
        responseMessage.setText(String.valueOf(prettyText));
        responseMessage.setChatId(Long.toString(chatId));
        try {

            botSender.execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
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
        row1.add(EmojiParser.parseToUnicode("\u0023\uFE0F\u20E3" + " Decimal Places"));
        row1.add(EmojiParser.parseToUnicode("\uD83C\uDFE6" + " Bank"));
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(EmojiParser.parseToUnicode("\uD83D\uDCB2" + " Currencies"));
        row2.add(EmojiParser.parseToUnicode("\u23F0" + " Notification Time"));
        keyboard.add(row2);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(EmojiParser.parseToUnicode("\uD83C\uDFE0" + " Back To Main Menu"));
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

    public void sendCurrencyOptions(long chatId, List<Currency> currencies) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Please select currencies:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (Currency currency : currencies) {
            KeyboardRow row = new KeyboardRow();
            if (currency.isSelected()) {
                row.add("✔" + currency.getCode());
            } else {
                row.add(currency.getCode());
            }
            keyboard.add(row);
        }

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            botSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
