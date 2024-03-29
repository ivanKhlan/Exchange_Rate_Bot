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

import java.util.ArrayList;
import java.util.List;

import static telegramBot.response.BankConstants.*;

public class MessageSender {

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
            BotSender.getInstance().execute(message);
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

            createRowWithSelectedBanks(banks, i, prettyText, currencies, numOfCharacters);

        }

        SendMessage responseMessage = new SendMessage();
        checkSelectedBanksAndCurrencies(responseMessage, banks, currencies, chatId, prettyText);

        try {

            BotSender.getInstance().execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void createRowWithSelectedBanks(List<String> banks, int i,
                                           StringBuilder prettyText, List<String> currencies,
                                           int numOfCharacters) {
        switch (banks.get(i)) {
            case "Monobank" ->
                    prettyText.append(MonoBankResponse.getMonoBank(MONO_API, "Monobank", currencies, numOfCharacters));
            case "PrivatBank" ->
                    prettyText.append(PrivatBankResponse.getPrivatBank(PRIVAT_API, "PrivatBank", currencies, numOfCharacters));
            case "NBU" -> prettyText.append(NbuBankResponse.getNbuBank(NBU_API, "NBU", currencies, numOfCharacters));
        }
        
    }
    
    public void checkSelectedBanksAndCurrencies(SendMessage responseMessage,
                                               List<String> banks, List<String> currencies,
                                               long chatId, StringBuilder prettyText) {
        if (banks.isEmpty()) {
            responseMessage.setText("\uD83E\uDD37\u200D\u2642\uFE0F" + "You haven't selected any bank");
            responseMessage.setChatId(Long.toString(chatId));
        } else if (currencies.isEmpty()) {
            responseMessage.setText("\uD83E\uDD37\u200D\u2642\uFE0F" + "You haven`t selected any currency");
            responseMessage.setChatId(Long.toString(chatId));
        } else {
            responseMessage.setText(String.valueOf(prettyText));
            responseMessage.setChatId(Long.toString(chatId));
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
            BotSender.getInstance().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(long chatId, String response) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response);
        try {
            BotSender.getInstance().execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
