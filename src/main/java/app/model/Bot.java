package app.model;


import app.Commands.HelpCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



public class Bot extends TelegramLongPollingCommandBot {

    private Bot(){
        this.RegisterCommand();
    }
    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    //@Override
//    public void onUpdateReceived(Update update) {
//        String message = update.getMessage().getText();
//        sendMsg(update.getMessage().getChatId().toString(), message, update.getMessage().getMessageId());
//    }

    @Override
    public void processNonCommandUpdate(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message, update.getMessage().getMessageId());
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
        try {
            execute(sendMessage);
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
                Bot.setBotToken(BotToken);

                instance = new Bot();
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(instance);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void RegisterCommand(){
        HelpCommand helpCommand = new HelpCommand();
        register(helpCommand);
    }

    public static boolean IsBotIni() {
        return (instance != null);
    }
}
