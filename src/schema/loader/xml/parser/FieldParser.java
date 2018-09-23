package schema.loader.xml.parser;

import model.DBField;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class FieldParser implements Parser<DBField> {

    @Override
    public DBField parse(Element fieldElement) {
        DBField field = new DBField();

        NamedNodeMap properties = fieldElement.getAttributes();

        for (int k = 0; k < properties.getLength(); k++) {
            switch (properties.item(k).getNodeName()) {
                case "name":
                    field.setName(properties.item(k).getTextContent());
                    break;
                case "entity_field":
                    field.setEntityField(properties.item(k).getTextContent());
                    break;
                case "foreign-key-entity":

                    break;
                case "primary-key":
                    field.setPK(true);
                    if (properties.getNamedItem("auto-increment") != null) {
                        field.setAI(true);
                    }
                    break;
                case "unique":
                    field.setUQ(true);
                    break;
                default:
                    break;
            }
        }

        if (field.getEntityField().equals("")) {
            field.setEntityField(properties.getNamedItem("name").getTextContent());
        }

        //TODO: додумать создание внешних ключей

        return field;
    }

}
