package com.emis.EMIS.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class Exchanger {
    JsonMapper mapper = new JsonMapper();
    RestTemplate restTemplate = new RestTemplate();

    public JsonNode postRequest(String url,Object object){
        HttpHeaders headers = new HttpHeaders();

        try{
            String data = mapper.writeValueAsString(object);
            HttpEntity<String> request = new HttpEntity<>(data,headers);
            return restTemplate.exchange(url, HttpMethod.POST, request, JsonNode.class).getBody();
        }catch (Exception e){
            log.warn(e.getLocalizedMessage());
            return null;
        }
    }
}
