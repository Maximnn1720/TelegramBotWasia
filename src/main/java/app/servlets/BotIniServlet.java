package app.servlets;


import app.model.Bot;
import jdk.internal.org.xml.sax.InputSource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;

import static app.model.Bot.InitBot;
import static app.model.Bot.IsBotIni;

public class BotIniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
//
//            //root elements
//            Document doc = docBuilder.newDocument();

            if (!IsBotIni()) {
                InitBot();
            }

//
//            Element rootElement = doc.createElement("configuration");
//            doc.appendChild(rootElement);
//
//            //BotUsername elements
//            Element BotUserName = doc.createElement("BotUsername");
//            rootElement.appendChild(BotUserName);
//            BotUserName.appendChild(doc.createTextNode("qwerty"));
//
//            //staff elements
//            Element BotToken = doc.createElement("BotToken");
//            rootElement.appendChild(BotToken);
//            BotToken.appendChild(doc.createTextNode("qwerty"));
//
//            //write the content into xml file
//            TransformerFactory transformerFactory =  TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
////            C:\Users\maxim\IdeaProjects\TelegramBotWasia\
//            StreamResult result =  new StreamResult(new File("config.xml"));
//            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
//            transformer.transform(source, result);

        } catch (Exception e) {

        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}

