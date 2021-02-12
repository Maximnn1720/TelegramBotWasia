package app.model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class Configure {
    private Configure() {

    }

    private static Configure _instance = null;
    private static NodeListWrapper _Settings = null;
    private static String _path = "config.xml";

    private static void setSettings() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            FileInputStream is = new FileInputStream(_path);
            Document doc = docBuilder.parse(is);
            NodeList _nodelist = doc.getDocumentElement().getElementsByTagName("set");
            _Settings = new NodeListWrapper(_nodelist);

        } catch (Exception ex) {

        }
    }

    public static void IniConfigure() {
        if (_instance == null) {
            try {
                _instance = new Configure();
                setSettings();
            } catch (Exception e) {
                _instance = null;
            }
        }
    }

    public static String GetSetting(String name) {
        for (Node _node : _Settings
        ) {
            if (_node.getAttributes().getNamedItem("name").getNodeValue().equals(name)) {
                String _Setting = _node.getFirstChild().getNodeValue();
                if (_Setting != null && !_Setting.equals("")) {
                    return _Setting;
                }
            }
        }
        return null;
    }

    private static final class NodeListWrapper extends AbstractList<Node>
            implements RandomAccess {
        private final NodeList list;

        NodeListWrapper(NodeList l) {
            list = l;
        }

        public Node get(int index) {
            return list.item(index);
        }

        public int size() {
            return list.getLength();
        }
    }
}
