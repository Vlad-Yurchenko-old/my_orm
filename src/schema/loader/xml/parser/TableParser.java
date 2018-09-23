package schema.loader.xml.parser;

import model.DBTable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TableParser implements Parser<DBTable> {

    private FieldParser fieldParser = new FieldParser();


    @Override
    public DBTable parse(Element tableElement) {
        DBTable table = new DBTable();
        table.setName(tableElement.getAttribute("name"));
        table.setEntityName(tableElement.getAttribute("entity_name"));

        NodeList fields = tableElement.getChildNodes();
        for (int j = 0; j < fields.getLength(); j++) {
            if (fields.item(j).getNodeType() == Node.ELEMENT_NODE) {
                table.getFields().add(fieldParser.parse((Element) fields.item(j)));
            }
        }

        return table;
    }

}
