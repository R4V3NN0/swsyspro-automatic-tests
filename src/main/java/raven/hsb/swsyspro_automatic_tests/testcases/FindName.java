package raven.hsb.swsyspro_automatic_tests.testcases;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import raven.hsb.swsyspro_automatic_tests.TestCase;

import java.lang.reflect.Type;
import java.util.*;

@Component
public class FindName implements TestCase {

    @Override
    public boolean isCorrect() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestMovie requestMovie = new RequestMovie("RandomName");
        String requestbody = new Gson().toJson(requestMovie);

        HttpEntity<String> entity = new HttpEntity<>(requestbody, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://server:8080/movies", HttpMethod.POST, entity, String.class);
        ResponseMovie body = new Gson().fromJson(exchange.getBody(), ResponseMovie.class);
        String name = body.getName();
        ResponseEntity<String> exchange2 = restTemplate.exchange("http://server:8080/movies/name/"+name, HttpMethod.GET, null, String.class);


        Type listOfMyClassObject = new TypeToken<ArrayList<ResponseMovie>>() {}.getType();

        List<ResponseMovie> body2 = new Gson().fromJson(exchange2.getBody(), listOfMyClassObject);

        ResponseMovie responseMovie = body2.get(0);

        return !responseMovie.getName().isEmpty() && exchange2.getStatusCode().isSameCodeAs(HttpStatus.OK) && responseMovie.getName().equals("RandomName");
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


