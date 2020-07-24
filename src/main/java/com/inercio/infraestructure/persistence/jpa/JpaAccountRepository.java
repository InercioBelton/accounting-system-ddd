package com.inercio.infraestructure.persistence.jpa;

import com.inercio.domain.model.account.Account;
import com.inercio.domain.model.account.AccountRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class JpaAccountRepository implements AccountRepository, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(JpaAccountRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account store(Account account) {
        entityManager.persist(account);
        logger.log(Level.INFO, "Created new account with number: {0}", account.getAccountNumber());

        return account;
    }

    @Override
    public Account findByAccountNumber(BigDecimal accountNumber) {
        return entityManager.createNamedQuery("Account.findByAccountNumber",
                Account.class).setParameter("accountNumber", accountNumber)
                .getSingleResult();
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createNamedQuery("Account.listAll", Account.class)
                .getResultList();
    }
}
