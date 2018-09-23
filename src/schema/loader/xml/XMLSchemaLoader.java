package schema.loader.xml;

import model.Database;
import org.w3c.dom.*;
import schema.loader.SchemaLoader;
import schema.loader.exception.LoadSchemaException;
import schema.loader.xml.parser.DatabaseParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLSchemaLoader implements SchemaLoader {

    private File dbPropertyFile;
    private DatabaseParser databaseParser;


    private XMLSchemaLoader() {
        databaseParser = new DatabaseParser();
    }

    public XMLSchemaLoader(File dbPropertyFile) {
        this();
        this.dbPropertyFile = dbPropertyFile;
    }


    public Database load() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            document = builder.parse(dbPropertyFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoadSchemaException();
        }

        Database database = databaseParser.parse((Element) document.getElementsByTagName("database").item(0));

        return database;
    }

}
