package dtl.DtlApp;

import com.google.api.services.sheets.v4.model.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DtlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DtlAppApplication.class, args);
		Request request = new Request();
	}

}
