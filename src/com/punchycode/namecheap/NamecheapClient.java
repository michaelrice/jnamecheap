package com.punchycode.namecheap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author errr
 */
public class NamecheapClient extends Namecheap
{

    private Client myClient;
    private WebResource.Builder myWebResource;

    /**
     *
     * @param user
     * @param key
     * @param userName
     * @param ip
     */
    public NamecheapClient( String user , String key , String userName , String ip )
    {
        super( user , key , userName , ip );
        this.myClient = Client.create();

    }

    /**
     *
     * @param url
     * @return
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public Document executeRequest( String url ) throws DocumentException, MalformedURLException
    {
        URL link = new URL(url);
        Document document;
        SAXReader reader = new SAXReader();
        document = reader.read( link );
        return document;
    }
    public void parseDocument(Document document, String elementName) throws DocumentException
    {
        Element root = document.getRootElement();

        // iterate through child elements of root
        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            // do something
            System.out.println("Element Name: " + element.getName());
        }

        // iterate through child elements of root with element name "foo"
        for ( Iterator i = root.elementIterator( elementName ); i.hasNext(); ) {
            Element foo = (Element) i.next();
            System.out.println("Elements named " + elementName + ": " + foo.getName());
            // do something
        }

        // iterate through attributes of root
        for ( Iterator i = root.attributeIterator(); i.hasNext(); ) {
            Attribute attribute = (Attribute) i.next();
            System.out.println("attribute name: " + attribute.getName());
            // do something
        }
     }
}