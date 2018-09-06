package com.priceminister.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.CustomerAccountRule;

public class CustomerAccountRuleTest {

	CustomerAccountRule rule;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rule = new CustomerAccountRule();
	}
	
	/**
	 * check a permitted balance with rule
	 */
	@Test
	public void testWithdrawPermitted() {
		Double balance = 10.00d;
		assertTrue(rule.withdrawPermitted(balance));
	}
	
	/**
	 * check a not permitted balance with rule
	 */
	@Test
	public void testWithdrawNotPermitted() {
		Double balance = -10.00d;
		assertFalse(rule.withdrawPermitted(balance));
	}
}
