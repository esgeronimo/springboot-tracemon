package esgeronimo.github.springboottracemon.infra.push;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import esgeronimo.github.springboottracemon.client.NotifClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PushNotifClient implements NotifClient {

    private final RestTemplate restTemplate;

    @Override
    public void push(String message) {
        restTemplate.postForObject("https://run.mocky.io/v3/452102e9-37e5-4c97-9a4b-147195bceff0", message, Void.class);
    }
}