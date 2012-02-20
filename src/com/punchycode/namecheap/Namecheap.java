package com.punchycode.namecheap;

/**
 *
 * @author errr
 */
public class Namecheap
{

    public String apiUser = null;
    public String apiKey = null;
    public String userName = null;
    public String clientIp = null;
    public String command = null;
    public String baseUrl = "https://api.namecheap.com/xml.response";

    public Namecheap()
    {
    }

    /**
     * Namecheap constructor that takes params
     * @param user String namecheap api username
     * @param key String namecheap api key
     * @param usrName String namecheap username this is normally the same as api username
     * @param ip  String ip you set in the namecheap admin interface to allow api access with
     */
    public Namecheap( String user , String key , String usrName , String ip )
    {
        this.apiUser = user;
        this.apiKey = key;
        this.userName = usrName;
        this.clientIp = ip;
        this.baseUrl += "?";
        this.baseUrl += "&ApiUser=" + user;
        this.baseUrl += "&ApiKey=" + key;
        this.baseUrl += "&UserName=" + usrName;
        this.baseUrl += "&ClientIp=" + ip;
    }

    /**
     *
     * @return
     */
    public String getApiKey()
    {
        return apiKey;
    }

    /**
     *
     * @return
     */
    public String getApiUser()
    {
        return apiUser;
    }

    /**
     *
     * @return
     */
    public String getBaseUrl()
    {
        return baseUrl;
    }

    /**
     *
     * @return
     */
    public String getClientIp()
    {
        return clientIp;
    }

    /**
     *
     * @return
     */
    public String getCommand()
    {
        return command;
    }

    /**
     *
     * @return
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     *
     * @param apiKey
     */
    public void setApiKey( String apiKey )
    {
        this.apiKey = apiKey;
    }

    /**
     *
     * @param apiUser
     */
    public void setApiUser( String apiUser )
    {
        this.apiUser = apiUser;
    }

    /**
     *
     * @param baseUrl
     */
    public void setBaseUrl( String baseUrl )
    {
        this.baseUrl = baseUrl;
    }

    /**
     *
     * @param clientIp
     */
    public void setClientIp( String clientIp )
    {
        this.clientIp = clientIp;
    }

    /**
     * Set command to be executed
     * @param command
     */
    public void setCommand( String command )
    {
        this.command = command;
    }

    /**
     * Set username
     * @param userName
     */
    public void setUserName( String userName )
    {
        this.userName = userName;
    }
}