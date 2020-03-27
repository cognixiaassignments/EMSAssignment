package com.collabera.jump.ems.model;

import java.util.Set;

public class Manager extends Employee {
	
	public Manager() {
		super();
		//super.clone();
	//
		
	}
	
	private Set<Employee> team; 

	public Set<Employee> getTeam() {
		return team;
	}

	public void setTeam(Set<Employee> team) {
		this.team = team;
	}
}
