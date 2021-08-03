package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class SimpleXMLImpl implements SimpleXML {

    /**
     * С помощью DOM API в Java-коде создать XML документ вида "&lt;tagName&gt;textNode&lt;/tagName&gt;".<br/>
     * В частности, для вызова createXML("root","&lt;R&amp;D&gt;") должно вернуться &lt;root&gt;&amp;lt;R&amp;amp;D&amp;gt;&lt;/root&gt;.<br/>
     * Требования:<br/>
     * - Результат должен быть корректным (well-formed) XML документом.<br/>
     * - Никаких переводов строк или других дополнительных символов не должно быть добавлено в textNode.<br/>
     * Правильный подход к решению:<br/>
     * - Использовать именно DOM, а не писать логику обработки спецсимволов вручную.<br/>
     * - С целью удаления в документе декларации "&lt;?xml...?&gt;" следует использовать метод
     * {@link Transformer#setOutputProperty(String, String)} для свойства OMIT_XML_DECLARATION.
     *
     * @param tagName  Имя тега элемента
     * @param textNode Текстовое содержимое тега.
     * @return Корректный XML документ без декларации "&lt;?xml...?&gt;"
     */
    @Override
    public String createXML(String tagName, String textNode) {
        if (tagName == null || tagName.equals("")) {
            return null;
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement(tagName);
            rootElement.appendChild(doc.createTextNode(textNode));
            doc.appendChild(rootElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(doc);
            StringWriter stringWriter = new StringWriter();
            StreamResult file = new StreamResult(new File("/Users/mikhail/Downloads/myXML.xml"));             //
            transformer.transform(source, file);                                                                        //
            transformer.transform(source, new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * С помощью SAX API проверить, что во входящем потоке содержится корректный (well-formed) XML-документ.<br/>
     * В качестве результата вернуть имя корневого элемента документа,
     * а в случае ошибки (если документ не well-formed) бросить {@link SAXException}.<br/>
     * Требование: Потребляемая память не должна зависеть от размера входящего документа.<br/>
     * Примечание: Не следует требовать от документа корректности пространства имен
     * (в имени элемента может использоваться namespace, но без объявления).
     *
     * @param xmlStream Поток с XML документом
     * @return Имя корневого элемента.
     */
    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            final String[] rootElement = {null};
            SAXParser parser = factory.newSAXParser();
            DefaultHandler XMLHandler = new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    rootElement[0] = qName;
                }
            };
            parser.parse(xmlStream, XMLHandler);
            return rootElement[0];
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
            throw new SAXException();
        }
    }
}
