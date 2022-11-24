//package com.cs440.group1;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

public final class apitest {
    private apitest() {
    }
    public static void main(String[] args) throws Exception {
        // Host url
        String host = "https://data.cityofchicago.org/resource/bbyy-e7gq.json";
        ArrayList<String> someData = new ArrayList(); // was going to use this to populate with data
        // String charset = "UTF-8";
        // Headers for a request
        // String x_api_key = "eclh63zq2ouwkxw6z0co98bs5";
        String x_App_Token = "QdqzeCrstFeHWJrQY6vEfyX6L"; // app token on City of Chicago site
        // Params
        // Format query for preventing encoding problems
        //   String query = String.format("s=%s",
        //    URLEncoder.encode(s, charset));

        HttpResponse <JsonNode> response = Unirest.get(host)
                .header("X-App-Token", x_App_Token)
                .header("accept", "application/json")//.queryString("docks_in_service", "9")
                .asJson();
        System.out.println(response.getStatus());
        //System.out.println(response.getHeaders());
        //System.out.println(response.getBody());
        //System.out.println(response.getBody().getArray());

        JSONArray responseBody = response.getBody().getArray();
        System.out.println(responseBody.getJSONObject(0));
        System.out.println(responseBody.getJSONObject(0).get("docks_in_service"));
        String responseType = (String) responseBody.getJSONObject(0).get("docks_in_service");

        for(int i = 0; i < responseBody.length(); i++) {
            System.out.println(responseBody.getJSONObject(i).get("docks_in_service"));

            someData.add((String) responseBody.getJSONObject(i).get("docks_in_service"));
        }

        System.out.println(someData);

    }
}