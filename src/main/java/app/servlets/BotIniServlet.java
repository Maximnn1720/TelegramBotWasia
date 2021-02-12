package app.servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.model.Bot.InitBot;
import static app.model.Bot.IsBotIni;

public class BotIniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!IsBotIni()) {
                InitBot();
            }
        } catch (Exception e) {
        }

//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
//        requestDispatcher.forward(req, resp);
    }
}

