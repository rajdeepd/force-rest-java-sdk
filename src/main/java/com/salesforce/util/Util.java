package com.salesforce.util;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.salesforce.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

/**
 * Created by Rajdeep Dua on 12/8/16.
 */
public class Util {
    public static Logger logger = Logger.getLogger(Util.class.getName());
    static String userName;
    static String password;
    public static String loginUrl = "";
    public static String host = "";
    static String grantService ;
    static String clientID;
    static String clientSecret;
    static String login = "https://login.salesforce.com/services/oauth2/token";
    static String baseUrl;
    static String queryUrl;
    static String waveBaseUrl;

    public static void loadProperties() {
        Properties prop = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream("application.properties");
        //InputStream input = null;
        try {
            prop.load(input);

            // get the property value and print it out
            userName = prop.getProperty("UserName");
            password = prop.getProperty("PassWord");
            host = prop.getProperty("Host");
            //LoginURL
            loginUrl = prop.getProperty( "LoginURL");
            grantService = prop.getProperty( "GrantService");
            clientID = prop.getProperty( "ClientID");
            clientSecret = prop.getProperty( "ClientSecret");
            baseUrl = prop.getProperty( "BaseUrl");
            queryUrl = prop.getProperty( "QueryUrl");
            waveBaseUrl = prop.getProperty( "WaveBaseUrl");
            logger.info(userName);

        } catch (IOException ex) {
            logger.log(Level.SEVERE,ex.toString(),ex);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE,e.toString(),e);
                }
            }
        }
    }

    public static String getAccessToken() throws java.io.IOException {
        loadProperties();

        String localloginURL = Util.loginUrl +
                    grantService +
                    "&client_id=" + clientID +
                    "&client_secret=" + clientSecret +
                    "&username=" + userName +
                    "&password=" + password;

        logger.info(localloginURL);
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(localloginURL);
        BasicResponseHandler handler = new BasicResponseHandler();
        HttpResponse  response = client.execute(post);

        String body = handler.handleResponse(response);
        System.out.println(body);

        //com.google.gson.JsonParser jsonParser = new JSONParser();
        Gson gson = new Gson();
        Token jsonObject = (Token)gson.fromJson(body, Token.class);
        String accessToken = jsonObject.access_token;
        return accessToken;
    }

    public static String getHost() {
        return host;
    }

    public static String getBaseUrl()  {
        return baseUrl;
    }

    public static String getQueryUrl()  {
        return queryUrl;
    }

    public static String getWaveBaseUrl()  {
        return waveBaseUrl;
    }
}
