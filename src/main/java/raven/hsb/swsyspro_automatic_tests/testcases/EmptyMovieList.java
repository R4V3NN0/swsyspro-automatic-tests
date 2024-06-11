package raven.hsb.swsyspro_automatic_tests.testcases;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import raven.hsb.swsyspro_automatic_tests.TestCase;

import java.util.Collection;
import java.util.Collections;

@Component
@Order(1)
public class EmptyMovieList implements TestCase {
    @Override
    public boolean isCorrect() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://server:8080/movies", HttpMethod.GET, entity, String.class);

        Collection body = new Gson().fromJson(exchange.getBody(), Collection.class);
        return body.isEmpty() && exchange.getStatusCode().isSameCodeAs(HttpStatus.OK);
    }


    @RequiredArgsConstructor
    @Getter
    public class Movie {
        private final String name;
    }
}


