package com.punchycode.namecheap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
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
    private String xml = null;

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
    }

    public void printXmlResult()
    {
        if( !xml.isEmpty() ) {
            System.out.println(xml);
        }
    }

    /**
     *
     * @param url
     * @return
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public Document executeRequest( String url ) throws DocumentException , MalformedURLException
    {
        URL link = new URL( url );
        Document document;
        SAXReader reader = new SAXReader();
        document = reader.read( link );
        //document = reader.read( "/home/errr/page_1_list.xml");
        xml = document.asXML();
        return document;
    }

    /**
     * printDocument Helper method used to debug results
     *
     * @param document
     * @throws DocumentException
     */
    public void printDocument( Document document ) throws DocumentException
    {
        Element root = document.getRootElement();
        Iterator<Element> elementIterator = root.elementIterator();
        while ( elementIterator.hasNext() ) {
            Element element = ( Element ) elementIterator.next();
            System.out.println( "Element name: " + element.getName().trim() );
            System.out.println("Element size: " + element.elements().size() );
            if( element.attributes().size() > 0 ) {
                printAttributes( element );
            }
            // if the element has children..
            if( element.elements().size() > 0 ) {
                printChild(element);
            }
        }
    }

    /**
     * Helper method used to debug results
     * @param e
     */
    private void printChild(Element e)
    {
        Iterator<Element> elementIterator = e.elementIterator();
        while ( elementIterator.hasNext() ) {
            Element element = ( Element ) elementIterator.next();
            System.out.println( "Element name: " + element.getName().trim() );
            System.out.println("Element size: " + element.elements().size() );
            if( element.attributes().size() > 0 ) {
                printAttributes( element );
            }
            // check if the element has children.. if so recursion!
            if( element.elements().size() > 0 ) {
                printChild(element);
            }
        }
    }

    /**
     * helper method to debug results
     *
     * @param e
     */
    private void printAttributes(Element e)
    {
        List<Attribute> attributes = e.attributes();
        Iterator<Attribute> iterator = attributes.iterator();
        while( iterator.hasNext()) {
            Attribute next = iterator.next();
            System.out.println( "Attr name: " + next.getName() + " value: " + next.getValue().trim() );
        }
    }
}