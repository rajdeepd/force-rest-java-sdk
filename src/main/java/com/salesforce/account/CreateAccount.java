package com.salesforce.account;

import com.salesforce.util.Util;
import com.salesforce.SObject;
import org.apache.http.HttpResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rajdeep Dua on 17/8/16.
 */
public class CreateAccount {
    static Logger logger = Logger.getLogger(CreateAccount.class.getName());
    public static void main(String[] args){
        try {
            String baseUrl = Util.getBaseUrl();
            SObject sObject = new SObject("Account");
            String json = "{\"name\":\"test_1\"}";
            HttpResponse response = sObject.createSObject(json);
            logger.info("Response:" + response);
        }catch(java.io.IOException e){
            logger.log(Level.SEVERE, e.toString(), e);
        }
    }
}
