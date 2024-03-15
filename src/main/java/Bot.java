
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        String chatId = update.getMessage().getChatId().toString();
        message.setChatId(chatId);

        if (update.hasMessage() && update.getMessage().getText().equalsIgnoreCase("/start")){
            message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }
    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
 }
