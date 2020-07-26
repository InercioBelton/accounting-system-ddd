package com.inercio.domain.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inercio.domain.model.accountingEntry.AccountSide;
import com.inercio.domain.model.accountingEntry.AccountingEntry;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

@Entity(name = "Account")
@Table(name = "account")
@NamedQuery(name = "Account.listAll", query = "Select a from Account a")
@NamedQuery(name = "Account.findByAccountNumber", query = "Select a from Account a where a.accountNumber = :accountNumber")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "account_number", unique = true)
    @NotNull
    private BigDecimal accountNumber;

    @Column(name = "account_name")
    @NotNull
    private String accountName;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<AccountingEntry> entries = new HashSet<>();

    public Account() {
    }

    public Account(BigDecimal accountNumber, String accountName) {
        Validate.notNull(accountNumber, "Account number is required");
        Validate.notNull(accountName, "Account name is required");
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = BigDecimal.valueOf(0);
    }

    public AccountingEntry debit(String description, BigDecimal amount, Date date) {
        Validate.notNull(description, "Description is required");
        Validate.notNull(amount, "Amount is required");

        AccountingEntry debitEntry = new AccountingEntry(description + " - " + AccountSide.DEBIT, amount.negate(), this, AccountSide.DEBIT, date);
        entries.add(debitEntry);
        balance = balance.add(amount.negate());
        return debitEntry;
    }

    public AccountingEntry credit(String description, BigDecimal amount, Date date) {
        Validate.notNull(description, "Description is required");
        Validate.notNull(amount, "Amount is required");

        AccountingEntry creditEntry = new AccountingEntry(description + " - " + AccountSide.CREDIT, amount, this, AccountSide.CREDIT, date);
        entries.add(creditEntry);
        balance = balance.add(amount);
        return creditEntry;
    }

    public Collection<AccountingEntry> getEntries() {
        return entries;
    }

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getCurrentBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                ", entries=" + entries +
                '}';
    }
}
