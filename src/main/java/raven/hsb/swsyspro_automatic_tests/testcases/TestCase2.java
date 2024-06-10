package raven.hsb.swsyspro_automatic_tests.testcases;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import raven.hsb.swsyspro_automatic_tests.TestCase;

@Component
public class TestCase2 implements TestCase {
    @Override
    public boolean isCorrect() {
        RestTemplate restTemplate=new RestTemplate();

        HttpHeaders header= new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Accept", "application/json");

        RequestAnfrage requestBody = new RequestAnfrage("lagos");
        Gson gson = new Gson();
        HttpEntity<String> requeteHttp =new HttpEntity(gson.toJson(requestBody), header);

        ResponseEntity<String> reponse = restTemplate.postForEntity("https://countriesnow.space/api/v0.1/countries/population/cities", requeteHttp , String.class);
        System.out.println(reponse.getBody());

        return true;

    }

    @Override
    public String getName() {
        return "TestCase2";
    }
}
