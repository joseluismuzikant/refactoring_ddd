package com.veritran.banking.domain.model;

import com.veritran.banking.domain.model.exception.AmountNegativeException;
import com.veritran.banking.domain.model.exception.BalanceNegativeException;

public final class Balance {
	private int value;
	
	private Balance() {
		
	}

	public  Balance(int value) {
		this.value = value;
		this.validate();
	}

	private void validate() {
		if(value<0) {
			throw new BalanceNegativeException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Balance other = (Balance) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public int getValue() {
		return value;
	}

	public Balance sum(int amount) {
		if(amount<0) {
			throw new AmountNegativeException();
		}
		return new Balance(this.value + amount);
	}

	public Balance minus(int amount) {
		if(amount<0) {
			throw new AmountNegativeException();
		}
		return new Balance(this.value - amount);
	}

	@Override
	public String toString() {
		return "Balance [value=" + value + "]";
	}
	
}
