package com.inercio.domain.model.accountingEntry;

import com.inercio.domain.model.account.Account;
import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "AccountingEntry")
@Table(name = "accounting_entry")
@NamedQuery(name = "AccountingEntry.listAll", query = "Select a from AccountingEntry a")
@NamedQuery(name = "AccountingEntry.findById", query = "Select a from AccountingEntry a where a.entryId = :id")
@NamedQuery(name = "AccountingEntry.findByDescription", query = "Select a from AccountingEntry a where a.description = :description")
@NamedQuery(name = "AccountingEntry.findAllByCreationDate", query = "Select a from AccountingEntry a where a.creationDate = :creationDate")
@NamedQuery(name = "AccountingEntry.findAllBetweenDates", query = "Select a from AccountingEntry a where a.creationTime between :startDate and :endDate")
@NamedQuery(name = "AccountingEntry.findAllByAccount", query = "Select a from AccountingEntry a where a.account.accountNumber = :accountNumber")
public class AccountingEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long entryId;

    @Column(name = "description", unique = true)
    @NotNull
    private String description;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "accountSide")
    @NotNull
    private AccountSide accountSide;

    @Column(name = "creation_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "creation_time")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @ManyToOne
    private Account account;

    public AccountingEntry() {
    }

    public AccountingEntry(String description, BigDecimal amount, Account account, AccountSide accountSide) {
        Validate.notNull(amount, "Amount is required");
        Validate.notNull(account, "Account is required");
        Validate.notNull(accountSide, "Account side is required");
        Validate.notNull(description, "Description is required");

        this.amount = amount;
        this.description = description;
        this.account = account;
        this.accountSide = accountSide;
        this.creationDate = new Date();
        this.creationTime = new Date();
    }

    public String getDescription() {
        return description;
    }

    public AccountSide getAccountSide() {
        return accountSide;
    }

    public Long getEntryId() {
        return entryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountingEntry entry = (AccountingEntry) o;
        return Objects.equals(entryId, entry.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId);
    }

    @Override
    public String toString() {
        return "AccountingEntry{" +
                "entryId=" + entryId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", accountSide=" + accountSide +
                ", creationDate=" + creationDate +
                ", creationTime=" + creationTime +
                ", account=" + account +
                '}';
    }
}
