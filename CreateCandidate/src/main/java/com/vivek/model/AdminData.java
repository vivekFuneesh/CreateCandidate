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
