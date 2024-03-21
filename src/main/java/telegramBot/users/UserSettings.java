package telegramBot.users;

import lombok.Data;

import java.util.List;

@Data
public class UserSettings {
    private int numOfCharacters;
    private List<String> banks;
    private List<String> currencies;
    private int notificationTime;

    public UserSettings(){
        numOfCharacters = 2;
        banks = List.of("privat");
        currencies = List.of("usd");
        notificationTime = 9;
    }

    public void removeBank(String bankName){
        banks.remove(bankName);
    }

    public void addBank(String bankName){
        banks.add(bankName);
    }

    public void removeCurrency(String currencyName){
        currencies.remove(currencyName);
    }

    public void addCurrency(String currencyName){
        currencies.add(currencyName);
    }

}
