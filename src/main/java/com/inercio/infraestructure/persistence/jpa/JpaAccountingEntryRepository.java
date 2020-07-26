package com.inercio.infraestructure.persistence.jpa;

import com.inercio.application.util.DateUtil;
import com.inercio.domain.model.accountingEntry.AccountingEntry;
import com.inercio.domain.model.accountingEntry.AccountingEntryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class JpaAccountingEntryRepository implements AccountingEntryRepository, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(JpaAccountRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public AccountingEntry store(AccountingEntry accountingEntry) {
        entityManager.persist(accountingEntry);
        logger.log(Level.INFO, "Posted new entry to accounting journal with description: {0}", accountingEntry.getDescription());
        return accountingEntry;
    }

    @Override
    public List<AccountingEntry> findAll() {
        return entityManager.createNamedQuery("AccountingEntry.listAll", AccountingEntry.class)
                .getResultList();
    }

    @Override
    public AccountingEntry findByEntryId(long id) {
        return entityManager.createNamedQuery("AccountingEntry.findById",
                AccountingEntry.class).setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public AccountingEntry findByDescription(String description) {
        return entityManager.createNamedQuery("AccountingEntry.findByDescription",
                AccountingEntry.class).setParameter("description", description)
                .getSingleResult();
    }

    @Override
    public List<AccountingEntry> findAllByCreationDate(Date date) {
        return entityManager.createNamedQuery("AccountingEntry.findAllBetweenDates", AccountingEntry.class)
                .setParameter("startDate", DateUtil.toDate(DateUtil.convertToLocalDate(date).atStartOfDay()), TemporalType.TIMESTAMP)
                .setParameter("endDate", DateUtil.toDate(DateUtil.convertToLocalDate(date).atTime(23, 59, 59)), TemporalType.TIMESTAMP)
                .getResultList();
    }

    @Override
    public List<AccountingEntry> findAllBetweenDates(Date startDate, Date endDate) {
        return entityManager.createNamedQuery("AccountingEntry.findAllBetweenDates", AccountingEntry.class)
                .setParameter("startDate", startDate, TemporalType.TIMESTAMP)
                .setParameter("endDate", endDate, TemporalType.TIMESTAMP)
                .getResultList();
    }

    @Override
    public List<AccountingEntry> findAllByAccountNumber(BigDecimal accountNumber) {
        return entityManager.createNamedQuery("AccountingEntry.findAllByAccount", AccountingEntry.class)
                .setParameter("accountNumber", accountNumber)
                .getResultList();
    }
}
