package com.priceminister.account.implementation;

import com.priceminister.account.*;

public class CustomerAccount implements Account {

	private Double balance;

	public CustomerAccount() {
		balance = 0d;
	}
	
	public void add(Double addedAmount) {
		if(!isAmountValid(addedAmount)) {
			return;
		}
		
		balance += addedAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {

		if(!isAmountValid(withdrawnAmount)) {
			return balance;
		}
		
		Double tempBalance = balance - withdrawnAmount;
		
		if (!rule.withdrawPermitted(tempBalance)) {
			throw new IllegalBalanceException(tempBalance);
		}
		balance = tempBalance;

		return balance;
	}

	/**
	 * Check if the amount is valid
	 * @param amount
	 * @return the amount validity
	 */
	private boolean isAmountValid(Double amount) {
		return amount != null && amount >= 0;
	}
}
