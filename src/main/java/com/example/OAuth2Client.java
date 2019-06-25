package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class OAuth2Client {

    private static OAuth2RestTemplate oauth2RestTemplate = null;

    public static void main(String[] args) {

        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri("https://XXXX.caspio.com/oauth/token");

        // -- set the clients info
        resourceDetails.setClientId("XXX");
        resourceDetails.setClientSecret("XXXX");
        resourceDetails.setGrantType("client_credentials");

        List<String> scopes = new ArrayList<>();
        scopes.add("read");
        scopes.add("write");
        scopes.add("trust");

        resourceDetails.setScope(scopes);
        
        
        oauth2RestTemplate  = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
       
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
 
        messageConverters.add(converter);
        oauth2RestTemplate.setMessageConverters(messageConverters);             
                
        System.out.println("Caspio Access Token : " + oauth2RestTemplate.getAccessToken().toString());

    }

}
