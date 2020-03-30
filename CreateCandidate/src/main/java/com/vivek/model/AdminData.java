package com.vivek.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

//@Component
public class AdminData {

	@DateTimeFormat(style="YYYY-MM-DD")
	private Date creationDateTime;

	@DateTimeFormat(style="YYYY-MM-DD")
	private Date lastModifiedDateTime;

	private Boolean finallyJoined;

	@DateTimeFormat(style="YYYY-MM-DD")
	private Date offerAcceptedDate;

	@DateTimeFormat(style="YYYY-MM-DD")
	private Date offerDeclinedDate;





	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public Boolean getFinallyJoined() {
		return finallyJoined;
	}

	public void setFinallyJoined(Boolean finallyJoined) {
		this.finallyJoined = finallyJoined;
	}

	public Date getOfferAcceptedDate() {
		return offerAcceptedDate;
	}

	public void setOfferAcceptedDate(Date offerAcceptedDate) {
		this.offerAcceptedDate = offerAcceptedDate;
	}

	public Date getOfferDeclinedDate() {
		return offerDeclinedDate;
	}

	public void setOfferDeclinedDate(Date offerDeclinedDate) {
		this.offerDeclinedDate = offerDeclinedDate;
	}



}
