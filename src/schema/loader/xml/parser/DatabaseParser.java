package schema.loader.xml.parser;

import model.Database;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DatabaseParser implements Parser<Database> {

    private TableParser tableParser = new TableParser();


    @Override
    public Database parse(Element databaseElement) {
        Database database = new Database();

        database.setName(databaseElement.getAttribute("name"));
        database.setUserName(databaseElement.getAttribute("user"));
        database.setUserPass(databaseElement.getAttribute("pass"));
        database.setUrl(databaseElement.getAttribute("url"));
        database.setDriver(databaseElement.getAttribute("driver"));

        NodeList tableNodes = databaseElement.getElementsByTagName("table");
        for (int i = 0; i < tableNodes.getLength(); i++) {
            if (tableNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                database.addTable(tableParser.parse((Element) tableNodes.item(i)));
            }
        }

        return database;
    }

}
