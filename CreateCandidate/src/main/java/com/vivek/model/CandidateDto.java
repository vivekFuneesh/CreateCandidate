package com.vivek.model;


/*MIT License

Copyright (c) 2020 Vivek Mangla

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/


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
