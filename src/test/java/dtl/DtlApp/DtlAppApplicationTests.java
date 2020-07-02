package dtl.DtlApp;

import dtl.DtlApp.GCPAuth.GoogleSheetsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
class DtlAppApplicationTests {

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @Test
    void contextLoads() throws IOException, GeneralSecurityException {
        googleSheetsService.someDeals();
    }

}
