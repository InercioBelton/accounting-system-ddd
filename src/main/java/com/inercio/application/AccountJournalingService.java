package com.inercio.application;

import com.inercio.domain.model.account.Account;
import com.inercio.domain.model.accountingEntry.AccountingEntry;
import com.inercio.domain.model.account.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountJournalingService {

    Account createAccount(BigDecimal accountNumber, String accountName);

    void debitAccount(BigDecimal accountNumber, String description, BigDecimal amount) throws AccountNotFoundException;

    void creditAccount(BigDecimal accountNumber, String description, BigDecimal amount) throws AccountNotFoundException;

    void postEntry(String description, BigDecimal amount, BigDecimal fromAccountNumber, BigDecimal toAccountNumber) throws AccountNotFoundException;

    List<AccountingEntry> listAllEntries();

    AccountingEntry getEntryDetails(long id);

    AccountingEntry findEntryByDescription(String description);

    List<AccountingEntry> listAllEntriesbyCreationDate(Date date);

    List<AccountingEntry> listAllEntriesCreatedBetweenDates(Date startDate, Date endDate);

    List<Account> listAllAccounts();

    List<Account> listAssetAccounts();

    List<Account> listLiabilityAccounts();

    List<AccountingEntry> listPostsMadeToAccount(BigDecimal accountNumber);

    BigDecimal getAccoutCurrentBalance(BigDecimal accountNumber) throws AccountNotFoundException;

    BigDecimal getEquityValue();

    BigDecimal getAssetsValue();

    BigDecimal getLiabilitiesValue();

}
