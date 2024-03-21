package telegramBot.users;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSettings {
    private int numOfCharacters;
    private List<String> banks;
    private List<String> currencies;
    private int notificationTime;

    public UserSettings(){
        numOfCharacters = 2;
        banks = new ArrayList<>();
        banks.add("privat");
        currencies = new ArrayList<>();
        currencies.add("USD");
        notificationTime = 9;
    }

}
