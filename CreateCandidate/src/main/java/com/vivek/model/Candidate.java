package com.vivek.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
All data to be inserted through this object.. or in this object form..<br>
1.       Name - Mandatory <br>

2.       Email-Mandatory <br>

3.       PhoneNumber - Mandatory <br>

4.       OfferDate - Mandatory <br>

5.       JoiningDate - Mandatory <br>

6.       Experience - Optional <br>

7.       PreviousSalary – Optional <br>

8.       OfferedSalary- Optional <br>

9.       CreationDateTime – Hide this from user i.e. do not send in response <br>

10.   LastModifiedDateTime – Hide this from user i.e. do not send in response <br>

11.   FinallyJoined - Boolean (To be used in DTO, read full task first) <br>

12.   OfferAcceptedDate – If Finally Joined is false (To be used in DTO, read full task first) <br>

13.   OfferDeclinedDate – If Finally Joined is false (To be used in DTO, read full task first) <br>

14.   CandidateId (PrimaryKey) <br>

@author: Vivek <br>
 */

//@Component
@Document
public class Candidate {
	@Id
	private String _id;

	/**
	 * An important point to be noted is:: when client's connection is closed,
	 * upon re-establishing connection, duplicate candidateId can be inserted<br>
	 * ??????????WHY??????????????<br>
	 * if insert during connection again a previously inserted candidateId during this connection::
	 * Now, uniqueness is checked.<br>
	 * Seems like it's validity remains till runTime only.
	 * */
	@NotBlank
	@Indexed(unique = true)
	private String candidateId;
	@NotBlank
	private String name;

	@NotBlank
	private String email;
	@NotBlank
	private String phone;

	@NotNull///It will fail test of "" or "   ".... i am just lazy here to check this ...
	@DateTimeFormat(style="YYYY-MM-DD")
	private Date offerDate;

	@NotNull
	@DateTimeFormat(style="YYYY-MM-DD")
	private Date joiningDate;

	private String experience;
	private String previousSalary;//optional, how to do
	private String offeredSalary;//optional. how to do

	//hidden
	private AdminData adminData;// bla bla

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getPreviousSalary() {
		return previousSalary;
	}

	public void setPreviousSalary(String previousSalary) {
		this.previousSalary = previousSalary;
	}

	public String getOfferedSalary() {
		return offeredSalary;
	}

	public void setOfferedSalary(String offeredSalary) {
		this.offeredSalary = offeredSalary;
	}

	public AdminData getAdminData() {
		return adminData;
	}

	public void setAdminData(AdminData adminData) {
		this.adminData = adminData;
	}


}
