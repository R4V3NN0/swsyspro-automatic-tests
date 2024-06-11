package raven.hsb.swsyspro_automatic_tests.testcases;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raven.hsb.swsyspro_automatic_tests.TestCase;

import java.util.Collections;
import java.util.Random;

@Component
public class DeleteIDNotFound implements TestCase {

    @Override
    public boolean isCorrect() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        int id = new Random().nextInt();
        try {
            ResponseEntity<String> exchange2 = restTemplate.exchange("http://server:8080/movies/id/"+id, HttpMethod.DELETE, null, String.class);
            return false;
        } catch (HttpClientErrorException e) {
            return e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND);
        }



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


