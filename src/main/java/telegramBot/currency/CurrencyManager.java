package telegramBot.currency;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CurrencyManager {
    private List<Currency> currencies;

    public CurrencyManager() {

        currencies = new ArrayList<>();
        currencies.add(new Currency("USD", false));
        currencies.add(new Currency("EUR", false));
        currencies.add(new Currency("UAH", false));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Currency> getCurrenciesWithBackButton() {
        List<Currency> currenciesWithBack = new ArrayList<>(currencies);
        currenciesWithBack.add(new Currency("Back", false));
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
