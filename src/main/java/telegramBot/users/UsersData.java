package telegramBot.users;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class UsersData {
    private Map<Long, UserSettings> users;

    public UsersData(){
        users = new HashMap<>();
    }

    public void addUser(long chatId){
        if(!containsUser(chatId)) {
            users.put(chatId, new UserSettings());
            System.out.println("New user added, chatId: " + chatId + ", " + users.get(chatId).toString());
        }
    }

    private boolean containsUser(long chatId){
        return users.containsKey(chatId);
    }
}
