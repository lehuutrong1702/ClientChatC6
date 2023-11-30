package com.teamc6.chatsystem.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamc6.chatsystem.model.Page;
import com.teamc6.chatsystem.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class Request {

    private static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    private HttpRequest httpRequest;
    private HttpResponse<String> httpResponse;
    private HttpRequest.Builder builder;

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public Request(String uri){
        builder = HttpRequest.newBuilder().uri(URI.create(uri))
                .timeout(Duration.ofMinutes(2))
                .header("Content-type","application/json");
    }
    public void authorization(String username, String password){
         builder.header("Authorization",getBasicAuthenticationHeader(username,password));
    }
    public void GET(){
        builder.GET();
    }

    public void build(){
     httpRequest =  builder.build();
    }
    public void send(){
        try {
            httpResponse= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Object getResBody(Class T) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         Object  t= objectMapper.readValue(httpResponse.body(), T);
         return T.cast(t);
    }
}
