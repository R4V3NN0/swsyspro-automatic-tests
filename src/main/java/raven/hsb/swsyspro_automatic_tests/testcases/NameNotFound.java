package raven.hsb.swsyspro_automatic_tests.testcases;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raven.hsb.swsyspro_automatic_tests.TestCase;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class NameNotFound implements TestCase {

    @Override
    public boolean isCorrect() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String name = "Nottobefound";
        ResponseEntity<String> exchange2 = restTemplate.exchange("http://server:8080/movies/name/"+name, HttpMethod.GET, entity, String.class);


        Type listOfMyClassObject = new TypeToken<ArrayList<FindName.ResponseMovie>>() {}.getType();

        List<FindName.ResponseMovie> body2 = new Gson().fromJson(exchange2.getBody(), listOfMyClassObject);


        return body2.isEmpty();
    }

    @RequiredArgsConstructor
    public class RequestMovie {
        private final String name;
    }

    @RequiredArgsConstructor
    @Getter
    public class ResponseMovie {
        private final String id;
        private final String name;
    }
}


