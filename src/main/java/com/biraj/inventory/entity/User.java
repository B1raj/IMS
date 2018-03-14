package com.biraj.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author birajmishra
 * User Entity
 */
@Entity
public class User  {


	@Id
	private String userid;

	private String name;

	private String password;

	private int outletId;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getOutletId() {
		return outletId;
	}

	public void setOutletId(int outletId) {
		this.outletId = outletId;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", password=" + password + ", outletId=" + outletId + "]";
	}

}