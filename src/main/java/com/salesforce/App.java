package com.salesforce;

import com.salesforce.util.Util;

import java.rmi.server.ExportException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
            Util.getAccessToken();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
