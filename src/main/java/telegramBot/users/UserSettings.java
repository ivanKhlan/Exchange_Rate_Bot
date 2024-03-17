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
        numOfCharacters = 1;
        banks = List.of("mono", "privat", "nbu");
        currencies = List.of("usd", "eur", "uah");
        notificationTime = 10;
    }

}
