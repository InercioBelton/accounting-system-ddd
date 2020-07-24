package com.inercio.domain.model.accountingEntry;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountingEntryRepository {

    AccountingEntry store(AccountingEntry accountingEntry);

    List<AccountingEntry> findAll();

    AccountingEntry findByEntryId(long id);

    AccountingEntry findByDescription(String description);

    List<AccountingEntry> findAllByCreationDate(Date date);

    List<AccountingEntry> findAllBetweenDates(Date startDate, Date endDate);

    List<AccountingEntry> findAllByAccountNumber(BigDecimal accountNumber);

}
