package schema.loader.xml.parser;

import org.w3c.dom.Element;

public interface Parser<T> {

    T parse(Element element);

}
