package com.teamc6.chatSystem.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class Request {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    private HttpRequest httpRequest;
    private HttpResponse<String> httpResponse;
    private final HttpRequest.Builder builder;

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

    public void POST(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String jsonObject = objectMapper.writeValueAsString(object);
            builder.POST(HttpRequest.BodyPublishers.ofString(jsonObject));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public void DELETE(Object object){
        builder.DELETE();
    }
    public void PUT(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            String jsonObject = objectMapper.writeValueAsString(object);
            builder.PUT(HttpRequest.BodyPublishers.ofString(jsonObject));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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
    public Object getResBody(TypeReference T) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(httpResponse.statusCode() == 200)
        {
            Object  t= objectMapper.readValue(httpResponse.body(), T);
            return (t);
        }
        else{
            return null;
        }
    }
}
