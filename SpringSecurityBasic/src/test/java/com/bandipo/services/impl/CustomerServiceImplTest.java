package com.bandipo.services.impl;

import com.bandipo.exceptions.CustomerException;
import com.bandipo.model.Account;
import com.bandipo.model.AccountTransactions;
import com.bandipo.model.Customer;
import com.bandipo.model.Loan;
import com.bandipo.repository.AccountRepository;
import com.bandipo.repository.AccountTransactionsRepository;
import com.bandipo.repository.CustomerRepository;
import com.bandipo.repository.LoanRepository;
import com.bandipo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Disabled
class CustomerServiceImplTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    private LoanRepository loanRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    void shouldSaveCustomer() {

        Customer admin = new Customer("admin@gmail.com", "$2a$12$L2VIZOe5lwCj4GeNUUL6tOUPSXEGUZTEMmE9Y9THho6F/n7gRiwpe", "admin");




           customerRepository.save(admin);


    }


    @Test
    @Disabled
    void shouldSaveAccount(){

        Account account = new Account();

        Customer customer = testEntityManager.find(Customer.class, 6L);

        account.setCustomer(customer);
        account.setAccountType("Savings");
        account.setAccountNumber(186576453434L);
        account.setBranchAddress("123 Main Street, New York");

        accountRepository.save(account);

    }

    @Test
    @Disabled
    @Transactional
    void shouldGetTransaction(){


        AccountTransactions accountTransactions = accountTransactionsRepository.findById(1L).orElseThrow(
                () -> new CustomerException("not found")
        );

        accountTransactions.setTransactionDt(new Date());
        accountTransactions.setCreateDt(new Date());
    }

    @Test
    @Disabled
    void shouldCreateLoan(){


        Customer customer = new Customer(6L);

        Loan loan = new Loan();

        loan.setCustomer(customer);
        loan.setLoanType("Home");
        loan.setTotalLoan(200000);
        loan.setAmountPaid(50000);
        loan.setOutstandingAmount(150000);


        loanRepository.save(loan);


    }
}