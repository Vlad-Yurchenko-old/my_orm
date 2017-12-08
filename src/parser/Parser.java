package parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import parser.models.Database;
import parser.models.Field;
import parser.models.Table;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Parser {

    private File dbPropertyFile;

    public Parser(File dbPropertyFile) {
        this.dbPropertyFile = dbPropertyFile;
    }

    public Database parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(dbPropertyFile);

        Element dataBaseElement = (Element) document.getElementsByTagName("database").item(0);

        Database database = Database.getInstance();

        database.setName(dataBaseElement.getAttribute("name"));
        database.setUserName(dataBaseElement.getAttribute("user"));
        database.setUserPass(dataBaseElement.getAttribute("pass"));
        database.setUrl(dataBaseElement.getAttribute("url"));
        database.setDriver(dataBaseElement.getAttribute("driver"));

        NodeList tables = document.getElementsByTagName("table");

        for (int i = 0; i < tables.getLength(); i++) {
            if (tables.item(i).getNodeType() == Node.ELEMENT_NODE) {
                /*
                * Получаем текущий тэг <table>
                * */
                Element tableElement = (Element) tables.item(i);
                Table table = new Table();
                table.setName(tableElement.getAttribute("name"));
                table.setEntityName(tableElement.getAttribute("entity_name"));
                /*
                * Получаем список дочерних тэгов (<field>) тэга <table>
                * */
                NodeList fields = tableElement.getChildNodes();
                for (int j = 0; j < fields.getLength(); j++) {
                    if (fields.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        /*
                        * Получаем текущий тэг <field>
                        * */
                        Element fieldElement = (Element) fields.item(j);
                        Field field = new Field();
                        /*
                        * Свойства текущего тэга <field>
                        * */
                        NamedNodeMap properties = fieldElement.getAttributes();
                        for (int k = 0; k < properties.getLength(); k++) {
                            /*
                            * В зависимости от свойств тэга <field> и устанавлиаев соотв. поля
                            * */
                            switch (properties.item(k).getNodeName()) {
                                case "name":
                                    field.setName(properties.item(k).getTextContent());
                                    break;
                                case "entity_field":
                                    field.setEntityField(properties.item(k).getTextContent());
                                    break;
                                case "type":
                                    field.setType(properties.item(k).getTextContent());
                                    break;
                            }
                        }
                        /*
                        * Если текущее рассматриваемое поля является идентификатором,
                        * то устанавливаем идентификатор для таблицы,
                        * иначе добавляем в множество fields
                        * */
                        if (field.getName().compareTo(tableElement.getAttribute("primary-key")) == 0) {
                            table.setPK(field);
                        } else {
                            table.addField(field);
                        }
                    }
                }
                database.addTable(table);
            }
        }

        System.out.println(database);
        return database;
    }

}
