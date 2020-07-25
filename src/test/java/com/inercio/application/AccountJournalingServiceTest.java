package com.inercio.application;

import com.inercio.application.internal.DefaultAccountJournalingService;
import com.inercio.domain.model.account.Account;
import com.inercio.domain.model.account.AccountNotFoundException;
import com.inercio.domain.model.accountingEntry.AccountSide;
import com.inercio.domain.model.accountingEntry.AccountingEntry;
import com.inercio.infraestructure.persistence.jpa.JpaAccountRepository;
import com.inercio.infraestructure.persistence.jpa.JpaAccountingEntryRepository;
import com.inercio.util.MockData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class AccountJournalingServiceTest {

    @Mock
    private JpaAccountRepository accountRepository;
    @Mock
    private JpaAccountingEntryRepository accountingEntryRepository;
    @InjectMocks
    private DefaultAccountJournalingService accountJournalingService;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MockData.init();
        when(accountRepository.store(any(Account.class))).thenReturn(MockData.accountList.get(0));
        when(accountRepository.findByAccountNumber(BigDecimal.valueOf(23423423))).thenReturn(MockData.accountList.get(2));
        when(accountRepository.findByAccountNumber(BigDecimal.valueOf(33322112))).thenReturn(MockData.accountList.get(3));
        when(accountRepository.findByAccountNumber(BigDecimal.valueOf(324233432))).thenReturn(MockData.accountList.get(4));
        when(accountRepository.findByAccountNumber(MockData.accountList.get(6).getAccountNumber())).thenReturn(MockData.accountList.get(6));
        when(accountRepository.findByAccountNumber(MockData.accountList.get(7).getAccountNumber())).thenReturn(MockData.accountList.get(7));
        when(accountRepository.findAll()).thenReturn(MockData.accountList);
        when(accountingEntryRepository.store(any(AccountingEntry.class))).thenReturn(MockData.accountingEntryList.get(0));
        when(accountingEntryRepository.findAll()).thenReturn(MockData.accountingEntryList);
        when(accountingEntryRepository.findByEntryId(anyLong())).thenReturn(MockData.accountingEntryList.get(0));
        when(accountingEntryRepository.findByDescription("New debit entry test")).thenReturn(MockData.accountingEntryList.get(0));
        when(accountingEntryRepository.findAllByCreationDate(any())).thenReturn(MockData.accountingEntryList);
        when(accountingEntryRepository.findAllBetweenDates(any(), any())).thenReturn(MockData.accountingEntryList);
        when(accountingEntryRepository.findAllByAccountNumber(any())).thenReturn(MockData.accountingEntryList);
    }

    @Test
    public void createAccountTest() {
        Account account = accountJournalingService.createAccount(BigDecimal.valueOf(121231231), "Test account");
        Assert.assertNotNull(account);
        Assert.assertEquals(BigDecimal.valueOf(121231231), account.getAccountNumber());
    }

    @Test
    public void debitAccountTest() throws AccountNotFoundException {
        accountJournalingService.debitAccount(BigDecimal.valueOf(23423423), "debit test", BigDecimal.valueOf(1000));
        Account account = accountRepository.findByAccountNumber(BigDecimal.valueOf(23423423));
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getEntries());
        AccountingEntry entry = account.getEntries().stream().findFirst().get();
        Assert.assertTrue(entry.getAccountSide().equals(AccountSide.DEBIT));
    }

    @Test
    public void creditAccountTest() throws AccountNotFoundException {
        accountJournalingService.creditAccount(BigDecimal.valueOf(33322112), "credit test", BigDecimal.valueOf(1000));
        Account account = accountRepository.findByAccountNumber(BigDecimal.valueOf(33322112));
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getEntries());
        AccountingEntry entry = account.getEntries().stream().findFirst().get();
        Assert.assertTrue(entry.getAccountSide().equals(AccountSide.CREDIT));
    }

    @Test
    public void postEntryTest() throws AccountNotFoundException {
        Account fromAccount = MockData.accountList.get(6);
        Account toAccount = MockData.accountList.get(7);
        accountJournalingService.postEntry("New Entry description test", BigDecimal.valueOf(4000), fromAccount.getAccountNumber(), toAccount.getAccountNumber());
        Assert.assertTrue(!fromAccount.getEntries().isEmpty());
        Assert.assertTrue(!toAccount.getEntries().isEmpty());
        Assert.assertTrue(fromAccount.getEntries().stream().findFirst().get().getAccountSide().equals(AccountSide.DEBIT));
        Assert.assertTrue(toAccount.getEntries().stream().findFirst().get().getAccountSide().equals(AccountSide.CREDIT));
    }

    @Test
    public void listEntriesTest() {
        List<AccountingEntry> entries = accountJournalingService.listAllEntries();
        Assert.assertNotNull(entries);
        Assert.assertTrue(!entries.isEmpty());
    }

    @Test
    public void getEntryDetailsTest() {
        AccountingEntry entryDetails = accountJournalingService.getEntryDetails(1);
        Assert.assertNotNull(entryDetails);
    }

    @Test
    public void findEntryByDescriptionTest() {
        AccountingEntry entryDetails = accountJournalingService.findEntryByDescription("New debit entry test");
        Assert.assertNotNull(entryDetails);
    }

    @Test
    public void listAllEntriesbyCreationDateTest() {
        List<AccountingEntry> entryList = accountJournalingService.listAllEntriesbyCreationDate(new Date());
        Assert.assertNotNull(entryList);
        Assert.assertTrue(!entryList.isEmpty());
    }

    @Test
    public void listAllEntriesCreatedBetweenDatesTest() {
        List<AccountingEntry> entryList = accountJournalingService.listAllEntriesCreatedBetweenDates(new Date("2020/07/20"), new Date());
        Assert.assertNotNull(entryList);
        Assert.assertTrue(!entryList.isEmpty());
    }

    @Test
    public void listAllAccountsTest() {
        List<Account> accountList = accountJournalingService.listAllAccounts();
        Assert.assertNotNull(accountList);
        Assert.assertTrue(!accountList.isEmpty());
    }

    @Test
    public void listPostsMadeToAccountTest() {
        List<AccountingEntry> entries = accountJournalingService.listPostsMadeToAccount(BigDecimal.valueOf(213123));
        Assert.assertNotNull(entries);
        Assert.assertTrue(!entries.isEmpty());
    }

    @Test
    public void getAccoutCurrentBalanceTest() throws AccountNotFoundException {
        BigDecimal balance = accountJournalingService.getAccoutCurrentBalance(BigDecimal.valueOf(324233432));
        Assert.assertEquals(BigDecimal.valueOf(0), balance);
    }

    @Test
    public void listAssetAccountsTest(){
        List<Account> assetsAccouts = accountJournalingService.listAssetAccounts();
        Assert.assertNotNull(assetsAccouts);
        Assert.assertTrue(!assetsAccouts.isEmpty());
    }

    @Test
    public void listLiabilityAccountsTest(){
        List<Account> liabilityAccounts = accountJournalingService.listLiabilityAccounts();
        Assert.assertNotNull(liabilityAccounts);
        Assert.assertTrue(!liabilityAccounts.isEmpty());
    }

    @Test
    public void getAssetsValueTest(){
        BigDecimal assetsValue = accountJournalingService.getAssetsValue();
        Assert.assertNotNull(assetsValue);
        Assert.assertTrue(assetsValue.compareTo(BigDecimal.valueOf(0)) >= 0);
    }

    @Test
    public void getLiabilitiesValueTest(){
        BigDecimal liabilitiesValue = accountJournalingService.getLiabilitiesValue();
        Assert.assertNotNull(liabilitiesValue);
        Assert.assertTrue(liabilitiesValue.compareTo(BigDecimal.valueOf(0)) < 0);
    }

    @Test
    public void getEquityValueTest(){
        BigDecimal equityValue = accountJournalingService.getEquityValue();
        Assert.assertNotNull(equityValue);
        Assert.assertTrue(equityValue.compareTo(BigDecimal.valueOf(0)) >= 0);
    }




}
