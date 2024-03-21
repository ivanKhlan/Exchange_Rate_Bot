package telegramBot.currency;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.users.UsersData;

import java.util.ArrayList;
import java.util.List;

public class CurrencyManager {
    private List<Currency> currencies;

    public CurrencyManager() {

        currencies = new ArrayList<>();
        currencies.add(new Currency("USD", false));
        currencies.add(new Currency("EUR", false));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Currency> getCurrenciesWithBackButton() {
        List<Currency> currenciesWithBack = new ArrayList<>(currencies);
        currenciesWithBack.add(new Currency(EmojiParser.parseToUnicode("\u2B05\uFE0F" + " Back"), false));
        return currenciesWithBack;
    }

    public void toggleCurrencySelection(String currencyCode) {
        for (Currency currency : currencies) {
            if (currency.getCode().equals(currencyCode)) {
                currency.setSelected(!currency.isSelected());
            }
        }
    }

}
