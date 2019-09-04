package mitras.test.teppi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeppiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeppiApplication.class, args);
	}
}
