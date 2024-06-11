package raven.hsb.swsyspro_automatic_tests.testcases;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import raven.hsb.swsyspro_automatic_tests.TestCase;

import java.util.Collections;
import java.util.Objects;

@Component
public class AddMovie implements TestCase {

    @Override
    public boolean isCorrect() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestMovie requestMovie = new RequestMovie("Prey");
        String requestbody = new Gson().toJson(requestMovie);

        HttpEntity<String> entity = new HttpEntity<>(requestbody, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://server:8080/movies", HttpMethod.POST, entity, String.class);

        ResponseMovie body = new Gson().fromJson(exchange.getBody(), ResponseMovie.class);

        return Objects.nonNull(body.id) && !body.id.isEmpty() && exchange.getStatusCode().isSameCodeAs(HttpStatus.CREATED) && body.name.equals("Prey");
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


