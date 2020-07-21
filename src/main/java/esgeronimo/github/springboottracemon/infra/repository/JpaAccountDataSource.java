package esgeronimo.github.springboottracemon.infra.repository;

import esgeronimo.github.springboottracemon.Account;
import esgeronimo.github.springboottracemon.datasource.AccountDataSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaAccountDataSource implements AccountDataSource {

    private final AccountRepository accountRepository;

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}