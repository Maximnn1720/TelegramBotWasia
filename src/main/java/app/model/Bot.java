package app.model;


import app.Commands.HelpCommand;
import app.Utils.GeoLocation;
import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Log4j
public class Bot extends TelegramLongPollingCommandBot {

    private Bot() {
        this.RegisterCommand();
    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
//    @Override
//    public void onUpdateReceived(Update update) {
//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChatId().toString(), message, update.getMessage().getMessageId());
//    }
    @Override
    public void processNonCommandUpdate(Update update) {
        String message = update.getMessage().getText();

        if (update.getMessage().hasLocation()) {
            System.out.println("I've got Location!!!!");
            Double petroLatitude = 59.950157;
            Double petroLongitude = 30.315352;
            Double userLatitude = update.getMessage().getLocation().getLatitude();
            Double userLongitude = update.getMessage().getLocation().getLongitude();
            message = GeoLocation.distanceCalculation(userLatitude,userLongitude, petroLatitude, petroLongitude);
            sendMsg(update.getMessage().getChatId().toString(), message, update.getMessage().getMessageId());

        } else {
            if (message.intern() == "Maksik") {
                sendMsg(update.getMessage().getChatId().toString(), "Maks - C# master EEE! ", update.getMessage().getMessageId());
                log.debug("Maksik krut!");
            } else if (message.intern() == "gde") {
                sendLoc(update.getMessage().getChatId().toString());
                sendMsg(update.getMessage().getChatId().toString(), "Здеся!", update.getMessage().getMessageId());
            } else {
                sendMsg(update.getMessage().getChatId().toString(), message, update.getMessage().getMessageId());
                log.debug("Bot answered the message.");
                System.out.println("MESSSSSAGE!");
            }
        }


    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param s      Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s, Integer ID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyToMessageId(ID);
        sendMessage.setText(s);
        log.debug("Bot prepared message to send.");
        try {
            execute(sendMessage);
            log.debug("Bot send message.");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            log.debug("Something goes wrongggggg.");
        }
    }

    public synchronized void sendLoc(String chatId) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setLatitude(59.950157);
        sendLocation.setLongitude(30.315352);
        sendLocation.setChatId(chatId);
        try {
            execute(sendLocation);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    //@Override
    public String getBotUsername() {
        return _BotUsername;
    }

    private static String _BotUsername = "";

    public static void setBotUsername(String BotUsername) {
        _BotUsername = BotUsername;
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    //@Override
    public String getBotToken() {
        return _BotToken;
    }

    private static String _BotToken = "";

    public static void setBotToken(String BotToken) {
        _BotToken = BotToken;
    }


    private static Bot instance = null;

    public static void InitBot() {
        if (instance == null) {
            try {

                String BotUsername = Configure.GetSetting("BotUsername");
                String BotToken = Configure.GetSetting("BotToken");

                Bot.setBotUsername(BotUsername);
                log.debug("Bot set Username.");
                Bot.setBotToken(BotToken);
                log.debug("Bot set Token.");

                instance = new Bot();
                log.debug("Bot set instance.");
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(instance);
                log.debug("Bot register instance.");
            } catch (Exception e) {
                e.printStackTrace();
                log.debug("Something goes wrong! By register.");
            }
        }
    }

    public void RegisterCommand() {
        HelpCommand helpCommand = new HelpCommand();
        register(helpCommand);
        log.debug("Help command registered.");
    }

    public static boolean IsBotIni() {
        return (instance != null);
    }
}
