package esgeronimo.github.springboottracemon.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esgeronimo.github.springboottracemon.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {}