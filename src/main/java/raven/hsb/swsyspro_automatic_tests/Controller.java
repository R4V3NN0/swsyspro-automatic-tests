package raven.hsb.swsyspro_automatic_tests;

import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
@RestController
@RequiredArgsConstructor
public class Controller {

    private final TestEngine testEngine;

    @GetMapping("/result")
    public ResponseEntity<GesamtErgenis> testResult() throws NoSuchAlgorithmException {
        String controlToken = "oaL8tSeGSrJqDlvR0vFv4rpru314ORvcHB0chahRFDtKEKGVHJ0iMH8vxiDweVpx";
        var result = testEngine.execute(controlToken);
        return ResponseEntity.ok(result);
    }
}
