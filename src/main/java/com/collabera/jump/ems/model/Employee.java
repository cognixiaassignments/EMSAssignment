package com.collabera.jump.ems.model;

import java.io.Serializable;

public class Employee extends Person implements Serializable, Cloneable, Comparable<Employee> {

	
	private String empId;

	private int ssn;

	private JOBTITLE title;

	private String email;

	private Address workAddress;

	private Manager reportsTo;

	private DEPARTMENT department;

	public DEPARTMENT getDepartment() {
		return department;
	}

	public void setDepartment(DEPARTMENT department) {
		this.department = department;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public JOBTITLE getTitle() {
		return title;
	}

	public void setTitle(JOBTITLE title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public Manager getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Manager reportsTo) {
		this.reportsTo = reportsTo;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", ssn=" + ssn + ", title=" + title + ", email=" + email + ", workAddress="
				+ workAddress + ", reportsTo=" + reportsTo + ", department=" + department + "]" + super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee && this.getSsn() == ((Employee) obj).getSsn()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getSsn();
	}

	@Override
	protected Employee clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Employee) super.clone();
	}

	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return this.getSsn() - o.getSsn();
	}

}
