package com.priceminister.account.implementation;

import com.priceminister.account.*;

public class CustomerAccount implements Account {

	private Double balance;

	public CustomerAccount() {
		balance = 0d;
	}
	
	public void add(Double addedAmount) {
		// TODO: check the spec for adding a negative amount use case!
		balance += addedAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		// TODO: check the spec for withdrawing a negative amount use case!

		balance -= withdrawnAmount;
		if (!rule.withdrawPermitted(balance)) {
			throw new IllegalBalanceException(balance);
		}

		return balance;
	}

}
