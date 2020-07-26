package com.inercio.application.internal;

import com.inercio.application.AccountJournalingService;
import com.inercio.domain.model.account.Account;
import com.inercio.domain.model.account.AccountNotFoundException;
import com.inercio.domain.model.account.AccountRepository;
import com.inercio.domain.model.accountingEntry.AccountingEntry;
import com.inercio.domain.model.accountingEntry.AccountingEntryRepository;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class DefaultAccountJournalingService implements AccountJournalingService {

    @Inject
    private AccountingEntryRepository accountingEntryRepository;

    @Inject
    private AccountRepository accountRepository;

    public Account createAccount(BigDecimal accountNumber, String accountName) {
        Account account = new Account(accountNumber, accountName);
        accountRepository.store(account);
        return account;
    }

    public void debitAccount(BigDecimal accountNumber, String description, BigDecimal amount, Date date) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            AccountingEntry debitEntry = account.debit(description, amount, date);
            accountingEntryRepository.store(debitEntry);
            accountRepository.store(account);
        } else {
            throw new AccountNotFoundException("Debit failed. Account with number: " + accountNumber + " was not found.");
        }
    }

    public void creditAccount(BigDecimal accountNumber, String description, BigDecimal amount, Date date) throws AccountNotFoundException {
        Validate.notNull(accountNumber, "Account number is required");
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            AccountingEntry creditEntry = account.credit(description, amount, date);
            accountingEntryRepository.store(creditEntry);
            accountRepository.store(account);
        } else {
            throw new AccountNotFoundException("Credit failed. Account with number: " + accountNumber + " was not found.");
        }
    }

    @Override
    public void postEntry(String description, BigDecimal amount, BigDecimal fromAccountNumber, BigDecimal toAccountNumber, Date date) throws AccountNotFoundException {
        debitAccount(fromAccountNumber, description, amount, date);
        creditAccount(toAccountNumber, description, amount, date);
    }

    @Override
    public List<AccountingEntry> listAllEntries() {
        return accountingEntryRepository.findAll();
    }

    @Override
    public AccountingEntry getEntryDetails(long id) {
        Validate.notNull(id, "Entry ID is required");
        return accountingEntryRepository.findByEntryId(id);
    }

    @Override
    public AccountingEntry findEntryByDescription(String description) {
        Validate.notNull(description, "Description is required");
        return accountingEntryRepository.findByDescription(description);
    }

    @Override
    public List<AccountingEntry> listAllEntriesbyCreationDate(Date date) {
        Validate.notNull(date, "Date is required");
        return accountingEntryRepository.findAllByCreationDate(date);
    }

    @Override
    public List<AccountingEntry> listAllEntriesCreatedBetweenDates(Date startDate, Date endDate) {
        Validate.notNull(startDate, "Start date is required");
        Validate.notNull(endDate, "End date is required");
        Validate.isTrue(!startDate.after(endDate), "Start date cannot be greater than end date");

        return accountingEntryRepository.findAllBetweenDates(startDate, endDate);
    }

    @Override
    public List<Account> listAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> listAssetAccounts() {
        List<Account> assetAccountList = new ArrayList<>();
        listAllAccounts().forEach(account -> {
            if (account.getCurrentBalance().compareTo(BigDecimal.valueOf(0)) >= 0) {
                assetAccountList.add(account);
            }
        });

        return assetAccountList;
    }

    @Override
    public List<Account> listLiabilityAccounts() {
        List<Account> liabilityAccountList = new ArrayList<>();
        listAllAccounts().forEach(account -> {
            if (account.getCurrentBalance().compareTo(BigDecimal.valueOf(0)) < 0) {
                liabilityAccountList.add(account);
            }
        });

        return liabilityAccountList;
    }

    @Override
    public List<AccountingEntry> listPostsMadeToAccount(BigDecimal accountNumber) {
        Validate.notNull(accountNumber, "Account number is required");
        return accountingEntryRepository.findAllByAccountNumber(accountNumber);
    }

    @Override
    public BigDecimal getAccoutCurrentBalance(BigDecimal accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return account.getCurrentBalance();
        } else {
            throw new AccountNotFoundException("Account with number: " + accountNumber + " was not found.");
        }
    }

    @Override
    public BigDecimal getEquityValue() {
        return getAssetsValue().add(getLiabilitiesValue());
    }

    @Override
    public BigDecimal getAssetsValue() {
        BigDecimal assetsValue = BigDecimal.ZERO;
        for (Account account : listAssetAccounts()) {
            assetsValue = assetsValue.add(account.getCurrentBalance());
        }

        return assetsValue;
    }

    @Override
    public BigDecimal getLiabilitiesValue() {
        BigDecimal liabilityValue = BigDecimal.ZERO;
        for (Account account : listLiabilityAccounts()) {
            liabilityValue = liabilityValue.add(account.getCurrentBalance());
        }

        return liabilityValue;
    }
}
