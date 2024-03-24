package telegramBot.notifications;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramBot.handler.MessageSender;
import telegramBot.users.UserSettings;
import telegramBot.users.UsersData;
import telegramBot.utils.BotSender;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Notifications {
    private MessageSender messageSender = new MessageSender();
    private ScheduledExecutorService executor;
    public void createNotificationMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Please choose a time to receive notifications");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("09:00");
        row1.add("10:00");
        row1.add("11:00");
        row1.add("12:00");
        row1.add("13:00");
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add("14:00");
        row2.add("15:00");
        row2.add("16:00");
        row2.add("17:00");
        row2.add("18:00");
        keyboard.add(row2);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(EmojiParser.parseToUnicode("\u274C" + " Don`t receive notifications"));
        row3.add(EmojiParser.parseToUnicode("\u2B05\uFE0F" + " Back"));
        keyboard.add(row3);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            BotSender.getInstance().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void applyNotification(long chatId, UsersData usersData) {
        UserSettings user = usersData.getUserById(chatId).get();

        if (user.getNotificationTime() != 0) {
            shutDownPrevious();

            executor = Executors.newSingleThreadScheduledExecutor();

            LocalTime notificationTime = LocalTime.of(user.getNotificationTime(), 0);
            long initialDelay = computeInitialDelay(notificationTime);
            long period = 24 * 60 * 60 * 1000;

            executor.scheduleAtFixedRate(() -> messageSender.sendInfoMessage(chatId, usersData), initialDelay, period, TimeUnit.MILLISECONDS);
        } else {
            shutDownPrevious();
        }
    }
    private void shutDownPrevious() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }


    private long computeInitialDelay(LocalTime notificationTime) {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(notificationTime)) {
            return Duration.between(currentTime, LocalTime.MAX).toMillis() + Duration.between(LocalTime.MIN, notificationTime).toMillis();
        } else {
            return Duration.between(currentTime, notificationTime).toMillis();
        }
    }

}
