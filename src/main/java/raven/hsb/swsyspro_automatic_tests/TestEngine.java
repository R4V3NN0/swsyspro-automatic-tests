package raven.hsb.swsyspro_automatic_tests;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestEngine  {

    private final List<TestCase> testCases;

    public GesamtErgenis execute(String controlToken) throws NoSuchAlgorithmException {
        List<Testergebnis> results = testCases.stream()
            .map(test -> new Testergebnis(test.isCorrect(), test.getName()))
            .toList();
        long bestandeneTests = results.stream()
            .filter(Testergebnis::isBestanden)
            .count();


        LocalDateTime time = LocalDateTime.now();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((controlToken+time.toString()).getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);

        return GesamtErgenis.builder()
            .gesamtZahlTests(testCases.size())
            .bestandeneTests(bestandeneTests)
            .controlToken(controlToken)
            .hashedControlToken(encoded)
            .details(results)
            .timestamp(time)
            .build();
    }
}
