package esgeronimo.github.springboottracemon.datasource;

import esgeronimo.github.springboottracemon.Account;

public interface AccountDataSource {
    void save(Account account);
}