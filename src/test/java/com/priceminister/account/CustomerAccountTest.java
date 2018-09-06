package com.priceminister.account;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.Mockito;

import com.priceminister.account.implementation.*;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
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
		rule = Mockito.mock(AccountRule.class);
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
		final Double addedAmount = 10.00d;
		customerAccount.add(addedAmount);

		assertEquals(addedAmount, customerAccount.getBalance());
	}

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the logic
	 * contained in CustomerAccountRule; feel free to refactor the existing code.
	 * 
	 * @throws IllegalBalanceException
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
		final Double balance = 10.00d;
		final Double withdrawnAmount = 50.00d;
		final Double exptectedBalance = balance - withdrawnAmount;
		Mockito.when(rule.withdrawPermitted(exptectedBalance)).thenReturn(false);

		customerAccount.add(balance);
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
	}

	// Also implement missing unit tests for the above functionalities.

	/**
	 * withdraw an amount and check the new balance is as expected.
	 * 
	 * @throws IllegalBalanceException
	 */
	@Test
	public void testWithdrawAndReportBalanceWithSuccess() throws IllegalBalanceException {
		final Double beginningBalance = 50.00d;
		final Double withdrawnAmount = 20.00d;
		final Double exptectedBalance = beginningBalance - withdrawnAmount;
		Mockito.when(rule.withdrawPermitted(exptectedBalance)).thenReturn(true);

		customerAccount.add(beginningBalance);
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);

		assertEquals(exptectedBalance, customerAccount.getBalance());
	}
	
	/**
	 * withdraw an invalid amount. Check if the result balance is conserved!
	 */
	@Test
	public void testWithdrawAndReportBalanceIllegalBalanceWithBalanceConservation() {
		final Double balance = 10.00d;
		final Double withdrawnAmount = 50.00d;

		customerAccount.add(balance);
		try {
			customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
		} catch (IllegalBalanceException e) {
			// do nothing
		}
		assertEquals(balance, customerAccount.getBalance());
	}
	
	/**
	 * add an negative amount. Check if the result balance is as expected!
	 */
	@Test
	public void testAddNegativeAmount() {
		final Double addedAmount = -5.0d;
		final Double beginningBalance = customerAccount.getBalance();
		customerAccount.add(addedAmount);
		// TODO: check the technical spec for this case (e.g throw Exception?)
		assertEquals(beginningBalance, customerAccount.getBalance());
	}
	
	/**
	 * add a null value. Check if the result balance is as expected
	 */
	@Test 
	public void testAddNullValue() {
		final Double addedAmount = null;
		final Double beginningBalance = customerAccount.getBalance();
		customerAccount.add(addedAmount);
		// TODO: check the technical spec for this case (e.g throw Exception?)
		assertEquals(beginningBalance, customerAccount.getBalance());
	}
	
	/**
	 * withdraw a negative amount. Check if the result balance is as expected.
	 */
	@Test
	public void testWithdrawAndReportBalanceNegativeAmount() {
		final Double balance = 10d;
		customerAccount.add(balance);
		final Double withdrawnAmount = -5d;
		Mockito.when(rule.withdrawPermitted(15d)).thenReturn(true);
		
		try {
			customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
		} catch (IllegalBalanceException e) {
		}
		
		// TODO: check the technical spec for this case (e.g throw Exception?)
		assertEquals(balance, customerAccount.getBalance());
	}
	
	/**
	 * withdraw a null value. Check if the result balance is as expected.
	 */
	@Test
	public void testWithdrawAndReportBalanceNullValue() {
		final Double balance = 10d;
		customerAccount.add(balance);
		final Double withdrawnAmount = null;
		Mockito.when(rule.withdrawPermitted(10d)).thenReturn(true);
		
		try {
			customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
		} catch (IllegalBalanceException e) {
		}
		
		// TODO: check the technical spec for this case (e.g throw Exception?)
		assertEquals(balance, customerAccount.getBalance());
	}
}
