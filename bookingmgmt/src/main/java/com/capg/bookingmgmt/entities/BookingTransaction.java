package com.capg.bookingmgmt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookingTransaction {
	@Id
	@GeneratedValue
	private int transactionId;
	private String transactionMethod;
	private double transactionAmount;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionMethod() {
		return transactionMethod;
	}
	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(transactionAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + transactionId;
		result = prime * result + ((transactionMethod == null) ? 0 : transactionMethod.hashCode());
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
		BookingTransaction other = (BookingTransaction) obj;
		if (Double.doubleToLongBits(transactionAmount) != Double.doubleToLongBits(other.transactionAmount))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionMethod == null) {
			if (other.transactionMethod != null)
				return false;
		} else if (!transactionMethod.equals(other.transactionMethod))
			return false;
		return true;
	}
	
	
}
