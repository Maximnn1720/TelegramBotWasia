package app.Commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StrartCommand extends BotCommand {
    public StrartCommand(String commandIdentifier, String description) {
        super("/start", "start command description");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        if (ICommandRegistry.class.isInstance(absSender)) {
            ICommandRegistry registry = (ICommandRegistry) absSender;

            //mCommandRegistry.getRegisteredCommands().forEach(cmd -> helpMessageBuilder.append(cmd.toString()).append("\n"));

            SendMessage helpMessage = new SendMessage();
            helpMessage.setChatId(chat.getId().toString());
            helpMessage.enableHtml(true);
            helpMessage.setText("Help! Help! Help!");

//            execute(absSender, helpMessage, user);
        }
    }
}
