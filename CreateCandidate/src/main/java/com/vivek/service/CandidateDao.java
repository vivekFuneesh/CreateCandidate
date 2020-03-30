package com.vivek.service;

import com.vivek.model.Candidate;
import com.vivek.model.CandidateDto;


/**
 * Data to be accessed from database in this form, through this interface
 */
public interface CandidateDao {
	//by-default public

	void addCandidate(Candidate candidate);
	void updateCandidate(CandidateDto details);

}
