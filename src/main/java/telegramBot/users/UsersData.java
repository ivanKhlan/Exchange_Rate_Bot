package telegramBot.users;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public boolean containsUser(long chatId){
        return users.containsKey(chatId);
    }

    public Optional<UserSettings> getUserById(long chatId){
        if (users.containsKey(chatId)){
            return Optional.of(users.get(chatId));
        }
        return Optional.empty();
    }

    public void setNotificationTimeByUserId(long chatId, int time){
        getUserById(chatId).get().setNotificationTime(time);
    }
}
