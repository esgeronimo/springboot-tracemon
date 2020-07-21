package esgeronimo.github.springboottracemon;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import esgeronimo.github.springboottracemon.client.AccountEngineClient;
import esgeronimo.github.springboottracemon.client.NotifClient;
import esgeronimo.github.springboottracemon.datasource.AccountDataSource;
import esgeronimo.github.springboottracemon.infra.push.PushNotifClient;
import esgeronimo.github.springboottracemon.infra.repository.AccountRepository;
import esgeronimo.github.springboottracemon.infra.repository.JpaAccountDataSource;
import esgeronimo.github.springboottracemon.infra.trace.TraceConfig;

@Configuration
@Import(value = {
    TraceConfig.class
})
public class AppConfig {
    
    @Bean
    RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    AccountEngineClient accountEngineClient(final RestTemplate restTemplate) {
        return new AccountEngineClient(restTemplate);
    }

    @Bean
    NotifClient notifClient(final RestTemplate restTemplate) {
        return new PushNotifClient(restTemplate);
    }

    @Bean
    AccountDataSource accountDataSource(final AccountRepository accountRepository) {
        return new JpaAccountDataSource(accountRepository);
    } 
}