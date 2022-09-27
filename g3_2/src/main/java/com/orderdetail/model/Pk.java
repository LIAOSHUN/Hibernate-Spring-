package com.orderdetail.model;

import java.io.Serializable;
import java.util.Objects;

public class Pk implements Serializable{
	
	public Integer ordNo;
	public Integer pdID;
	
	
	@Override
	public boolean equals(Object o) {
	if (this == o) {
	return true;
	}
	if (o == null || getClass() != o.getClass()) {
	return false;
	}
	Pk pk = (Pk) o;
	return Objects.equals(ordNo, pk.ordNo)
	&& Objects.equals(pdID, pk.pdID);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ordNo, pdID);
	}

}
