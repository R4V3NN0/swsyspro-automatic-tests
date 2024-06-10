package raven.hsb.swsyspro_automatic_tests;


import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GesamtErgenis {
    private final long bestandeneTests;
    private final long gesamtZahlTests;
    private final LocalDateTime timestamp;
    private final String controlToken;
    private final String hashedControlToken;
}
