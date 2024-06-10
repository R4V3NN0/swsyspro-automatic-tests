package raven.hsb.swsyspro_automatic_tests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Testergebnis {
    private final boolean bestanden;
    private final String testname;
}
