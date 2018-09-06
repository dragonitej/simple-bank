package com.priceminister.account.implementation;

import com.priceminister.account.*;

public class CustomerAccount implements Account {

	private Double balance;

	public CustomerAccount() {
		balance = 0d;
	}
	
	public void add(Double addedAmount) {
		if(addedAmount == null || addedAmount < 0d) {
			return; 
		}
		balance += addedAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {

		if(withdrawnAmount == null || withdrawnAmount < 0) {
			return balance;
		}
		Double tempBalance = balance - withdrawnAmount;
		
		if (!rule.withdrawPermitted(tempBalance)) {
			throw new IllegalBalanceException(tempBalance);
		}
		balance = tempBalance;

		return balance;
	}

}
