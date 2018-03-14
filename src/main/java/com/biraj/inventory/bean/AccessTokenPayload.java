package com.biraj.inventory.bean;
/**
 * @author birajmishra
 * Basic entries to be present in JWT, as per JWT standard. Can have more entries if required
 */
import java.util.Date;

public class AccessTokenPayload {

	private String issuer;
	private Date issuedDate;
	private String audience;
	private String partyId;
	private int outletId;
	

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public int getOutletId() {
		return outletId;
	}

	public void setOutletId(int outletId) {
		this.outletId = outletId;
	}

	@Override
	public String toString() {
		return "AccessTokenPayload [issuer=" + issuer + ", issuedDate=" + issuedDate + ", audience=" + audience
				+ ", partyId=" + partyId + "]";
	}

}
