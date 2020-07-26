package com.inercio.util;

import com.inercio.domain.model.account.Account;
import com.inercio.domain.model.accountingEntry.AccountSide;
import com.inercio.domain.model.accountingEntry.AccountingEntry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockData {

    public static List<Account> accountList = new ArrayList<>();
    public static List<AccountingEntry> accountingEntryList = new ArrayList<>();

    public static void init() {
        initAccountList();
        initAccountingEntryList();
    }

    private static void initAccountList() {
        for (long index = 0; index < 10; index++) {
            Account account = new Account(BigDecimal.valueOf(12324234 + index), "Test Account" + 1);
            account.setId(index);
            account.setBalance(BigDecimal.valueOf(5000));
            accountList.add(account);
        }
        accountList.get(4).setBalance(BigDecimal.ZERO);
        accountList.get(5).setBalance(BigDecimal.valueOf(2000).negate());
    }

    private static void initAccountingEntryList() {

        accountingEntryList.add(new AccountingEntry("New debit entry test", BigDecimal.valueOf(1000), accountList.get(4), AccountSide.DEBIT, new Date()));
        accountingEntryList.add(new AccountingEntry("New credit entry test", BigDecimal.valueOf(1000), accountList.get(5), AccountSide.CREDIT, new Date()));
        accountingEntryList.add(new AccountingEntry("New debit entry test 2", BigDecimal.valueOf(1000), accountList.get(4), AccountSide.DEBIT, new Date()));

    }


}
