package com.punchycode.namecheap.domains;

import com.punchycode.namecheap.NamecheapClient;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;

/**
 *
 * @author errr
 */
public class Domains extends NamecheapClient
{

    public Domains(String user, String key, String userName, String ip)
    {
        super(user,key,userName,ip);
    }

    /**
     * Get List by type.
     * Supported types are ALL, EXPIRING, and EXPIRED
     *
     * @param type String either ALL, EXPIRING, or EXPIRED
     * @return
     */
    public Document getList(String type)
    {
        Document dom = null;
        // ALL/EXPIRING/EXPIRED
        if (( type.compareToIgnoreCase( "all") == 0 ) ||
            ( type.compareToIgnoreCase( "expiring") == 0 ) ||
            ( type.compareToIgnoreCase( "expired") == 0 )) {
            return getList(type,"","","100","");
        }
        return dom;
    }

    /**
     * getList returns an org.dom4j.Document from the Namecheap API
     *
     * @param type
     * @param searchTerm
     * @param pageNum
     * @param resultsPerPage
     * @param sortBy
     * @return
     */
    public Document getList(String type, String searchTerm, String pageNum, String resultsPerPage, String sortBy)
    {
        Document dom = null;
        type = type.toUpperCase();
        int rpp = Integer.parseInt( resultsPerPage);
        if ( !((rpp >= 10) && (rpp <= 100)) ) {
            resultsPerPage = 100 + "";
        }
        // max length is 10
        if( type.length() > 10 ) {
            // Im picking EXPIRING here since its the longest
            // word form the valid options
            type = "EXPIRING";
        }
        if( !searchTerm.isEmpty() && searchTerm.length() > 70 ) {
            // 70 is the max length and since its a search it
            // should still work with a partial word
            searchTerm = searchTerm.substring( 0, 69);
        }
        String cmd = "&Command=namecheap.domains.getList";
        cmd += "&ListType=" + type;
        cmd += "&SearchTerm=" + searchTerm;
        cmd += "&Page=" + pageNum;
        cmd += "&PageSize=" + resultsPerPage;
        cmd += "&SortBy=" + sortBy;
        setCommand( cmd);
        try {
            dom = ( Document ) executeRequest( getBaseUrl() + getCommand());
            return dom;

        }
        catch ( DocumentException ex ) {
            Logger.getLogger( Domains.class.getName() ).log( Level.SEVERE , null , ex );
        }
        catch ( MalformedURLException ex ) {
            Logger.getLogger( Domains.class.getName() ).log( Level.SEVERE , null , ex );
        }
        return dom;
    }

    /**
     *  GetList using default parameters
     * @return
     */
    public Document getList()
    {
        return getList( "ALL", "", "1","100","NAME" );
    }

    /**
     * Get Tld List. This should be cached to cut down on calls
     * since its output will rarely change.
     *
     * @return
     */
    public Document getTldList()
    {
        Document dom = null;
        String cmd = "&Command=namecheap.domains.gettldlist";
        this.setCommand( cmd);
        try {
            dom = this.executeRequest( getBaseUrl() + getCommand() );
        }
        catch ( DocumentException ex ) {
            Logger.getLogger( Domains.class.getName() ).log( Level.SEVERE , null , ex );
        }
        catch ( MalformedURLException ex ) {
            Logger.getLogger( Domains.class.getName() ).log( Level.SEVERE , null , ex );
        }
        return dom;
    }
}