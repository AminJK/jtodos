package com.niufennan.jtodos.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    public static String get(String url){
        HttpClient client =  new DefaultHttpClient();
        HttpUriRequest request=new HttpGet(url);
        request.setHeader("Content-type","application/json;charset=utf-8");
        HttpResponse response= null;
        String result = null;
        try {
            response = client.execute(request);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
