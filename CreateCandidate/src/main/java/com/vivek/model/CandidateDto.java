package com.vivek.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * update the candidate record.<br>

1.       CandidateId<br>

2.       FinallyJoined<br>

3.       OfferAcceptedDate<br>

4.       OfferDeclinedDate<br>
 * */
//@Component
public class CandidateDto {

	@NotBlank
	private String candidateId;

	private Boolean finallyJoined;

	@DateTimeFormat(style="YYYY-MM-DD")
	private Date offerAcceptedDate;

	@DateTimeFormat(style="YYYY-MM-DD")
	private Date offerDeclinedDate;



	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
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
