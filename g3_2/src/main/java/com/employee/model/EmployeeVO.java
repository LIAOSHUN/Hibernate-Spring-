package com.employee.model;

import java.sql.Date;

public class EmployeeVO {
	private Integer empID;
	private String empName;
	private String empPhone;
	private byte[] empAvatar;
	private String empAccount;
	private String empPassWord;
	private Date empHireDate;
	private Integer empStatus;

	public Integer getEmpID() {
		return empID;
	}

	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public byte[] getEmpAvatar() {
		return empAvatar;
	}

	public void setEmpAvatar(byte[] empAvatar) {
		this.empAvatar = empAvatar;
	}

	public String getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(String empAccount) {
		this.empAccount = empAccount;
	}

	public String getEmpPassWord() {
		return empPassWord;
	}

	public void setEmpPassWord(String empPassWord) {
		this.empPassWord = empPassWord;
	}

	public Date getEmpHireDate() {
		return empHireDate;
	}

	public void setEmpHireDate(Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}
}
