package com.biraj.inventory.bean;

/**
 * @author birajmishra
 *
 */
public class UserInfo {
	
	public UserInfo(){
		
	}

	public UserInfo(String internalUserId, int outletId) {
		this.internalUserId = internalUserId;
		this.outletId = outletId;
	}

	private String internalUserId;
	private int outletId;

	public String getInternalUserId() {
		return internalUserId;
	}

	public void setInternalUserId(String internalUserId) {
		this.internalUserId = internalUserId;
	}

	public int getOutletId() {
		return outletId;
	}

	public void setOutletId(int outletId) {
		this.outletId = outletId;
	}

	@Override
	public String toString() {
		return "UserInfo [internalUserId=" + internalUserId + ", outletId=" + outletId + "]";
	}

}
