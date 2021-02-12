package app.model;

import javax.servlet.ServletContextEvent;

import static app.model.Bot.IsBotIni;

public class StartListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (!IsBotIni()) {
                Configure.IniConfigure();
                Bot.InitBot();

                String BotUsername = Configure.GetSetting("BotUsername");
                String BotToken = Configure.GetSetting("BotToken");

                System.out.println("BotUsername: ");
                System.out.println(BotUsername);

                System.out.println("BotToken: ");
                System.out.println(BotToken);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
