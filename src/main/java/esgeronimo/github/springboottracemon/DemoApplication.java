package esgeronimo.github.springboottracemon;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import esgeronimo.github.springboottracemon.client.AccountEngineClient;
import esgeronimo.github.springboottracemon.client.NotifClient;
import esgeronimo.github.springboottracemon.datasource.AccountDataSource;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequiredArgsConstructor
	@Controller
	@RequestMapping("/accounts")
	static class AccountsController {

		private final AccountEngineClient accountEngineClient;
		private final NotifClient notifClient;
		private final AccountDataSource accountDataSource;

		@PostMapping
		public ResponseEntity<Void> post() {
			Account account = new Account(UUID.randomUUID().toString());
			accountEngineClient.save(account);
			accountDataSource.save(account);
			notifClient.push("Account Created");
			return ResponseEntity.ok().build();
		}
	}
}
