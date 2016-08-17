package com.salesforce;

import com.salesforce.util.Util;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.logging.Logger;
/**
 * Created by Rajdeep Dua on 14/8/16.
 */
public class SObject {
    public static Logger logger = Logger.getLogger(SObject.class.getName());
    private String sObjectName;
    private String baseUrl;
    private String host;
    static HttpClient client = HttpClientBuilder.create().build();

    public SObject(String sObjectName){
        this.sObjectName = sObjectName;
    }
    public SObject(String sObjectName, String baseUrl){
        this.sObjectName = sObjectName;
        this.baseUrl = baseUrl;
        Util.loadProperties();
        this.host = Util.getHost();
        this.baseUrl = Util.getBaseUrl();
    }


    public String getSObjectDetails(String id) throws java.io.IOException{
        String access_token = Util.getAccessToken();
        String url = host + baseUrl + sObjectName + "/" + id;
        HttpGet request = new HttpGet(url);
        request.addHeader("Authorization", "Bearer " + access_token);
        request.addHeader("Content-type", "application/json");
        HttpResponse response = client.execute(request);
        BasicResponseHandler handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);
        return body;
    }

    public HttpResponse createSObject(String jsonData) throws java.io.IOException  {
        Util.loadProperties();
        String host = Util.getHost();
        String baseUrl = Util.getBaseUrl();

        String access_token = Util.getAccessToken();
        //println(access_token)
        String url = host + baseUrl + sObjectName;
        HttpPost post = new HttpPost(url);

        // set the Content-type
        post.addHeader("Authorization", "Bearer " + access_token);
        post.setHeader("Content-type", "application/json");

        // add the JSON as a StringEntity
        post.setEntity(new StringEntity(jsonData));
        HttpResponse response = client.execute(post);

        System.out.println(response.toString());
        return response;
    }
}
