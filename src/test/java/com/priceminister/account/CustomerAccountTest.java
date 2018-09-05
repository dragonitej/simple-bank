package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;


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
        assertEquals(Double.valueOf("0"), customerAccount.getBalance());
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double addedAmount = 10.00d;
        customerAccount.add(addedAmount);
        
        assertEquals(addedAmount, customerAccount.getBalance());
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     * @throws IllegalBalanceException 
     */
    @Test(expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
        Double balance = 10.00d;
        Double withdrawnAmount = 50.00d;
        
        customerAccount.add(balance);
        customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }
    
    // Also implement missing unit tests for the above functionalities.

    /**
     * withdraw an amount and check the new balance is as expected.
     * @throws IllegalBalanceException
     */
    @Test
    public void testWithdrawAndReportBalanceWithSuccess() throws IllegalBalanceException {
    	Double beginningBalance = 50.00d;
    	Double withdrawnAmount = 20.00d;
    	Double exptectedBalance = beginningBalance - withdrawnAmount;
    	
    	customerAccount.add(beginningBalance);
    	customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    	
    	assertEquals(exptectedBalance, customerAccount.getBalance());
    }
}
