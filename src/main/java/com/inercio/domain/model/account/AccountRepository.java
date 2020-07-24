package com.inercio.domain.model.account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository {

    Account store (Account account);

    Account findByAccountNumber(BigDecimal accountNumber);

    List<Account> findAll();

}
