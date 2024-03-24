package telegramBot.users;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class UsersData {
    private Map<Long, UserSettings> users;

    public UsersData(){
        users = new HashMap<>();
    }

    public void addUser(long chatId){
        if(!containsUser(chatId)) {
            users.put(chatId, new UserSettings());
            log.error("New user added, chatId: " + chatId + ", " + users.get(chatId).toString());
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
