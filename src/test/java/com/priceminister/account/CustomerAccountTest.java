package com.priceminister.account;


import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        assertEquals(0.0, customerAccount.getBalance(), 0);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double expectedBalance = 12345.123;
        customerAccount.add(expectedBalance);
        assertEquals(expectedBalance, customerAccount.getBalance());
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test(expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
        customerAccount.withdrawAndReportBalance(10000.0, rule);
    }
    
    // Also implement missing unit tests for the above functionalities.


    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeAmount() {
        customerAccount.add(-12345.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullAmount() {
        customerAccount.add(null);
    }

    @Test
    public void testWithdrawPositiveAmount() throws IllegalBalanceException {
        customerAccount.add(10000.0);
        Double balanceAfterWithdraw = customerAccount.withdrawAndReportBalance(2000.0, rule);
        Double balance = customerAccount.getBalance();
        assertEquals(balanceAfterWithdraw, balance);
        assertEquals(8000.0, balance, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNegativeAmount() throws IllegalBalanceException {
        customerAccount.withdrawAndReportBalance(-123456.0, rule);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNullAmount() throws IllegalBalanceException {
        customerAccount.withdrawAndReportBalance(null, rule);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNullAccountRule() throws IllegalBalanceException {
        customerAccount.withdrawAndReportBalance(10.0, null);
    }
}
