package esgeronimo.github.springboottracemon.client;

import org.springframework.web.client.RestTemplate;

import esgeronimo.github.springboottracemon.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class AccountEngineClient {

    private final RestTemplate restTemplate;

    public void save(Account account) {
        restTemplate.postForObject("https://run.mocky.io/v3/452102e9-37e5-4c97-9a4b-147195bceff0", account, SaveAccountResponse.class);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    private static class SaveAccountResponse {
        private String accountID;
    }
}