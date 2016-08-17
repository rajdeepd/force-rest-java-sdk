package com.salesforce.account;

import com.salesforce.SObject;
import com.salesforce.util.Util;
import com.google.gson.Gson;
import java.io.IOException;

/**
 * Created by Rajdeep Dua on 15/8/16.
 */
public class GetAccountDetails {
    public static void main(String[] args) throws IOException{
        String baseUrl = Util.getBaseUrl();
        SObject sObject = new SObject("Account", baseUrl);
        String id = "0012800000EvvnNAAR";//0012800000EvvnNAAR
        String body = sObject.getSObjectDetails(id);
        System.out.println(body);
        Gson gson = new Gson();
        //val jsonObject = gson.fromJson(body, classOf[Account])
        //println(jsonObject)
    }
}
