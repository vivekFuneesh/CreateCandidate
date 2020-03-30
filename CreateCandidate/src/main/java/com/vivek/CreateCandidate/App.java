package com.vivek.CreateCandidate;

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


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.vivek.model.AdminData;
import com.vivek.model.Candidate;
import com.vivek.model.CandidateDto;
import com.vivek.service.CandidateDao;
import com.vivek.service.CandidateDaoImpl;
import com.vivek.service.ModelServiceConfig;


/**
 * This api provides 2 services through com.vivek.service package<br>
 * CandidateDao is interface, CandidateDaoImpl is it's implementation.<br>
 * ModelServiceConfig is applicationContext's Configuration file.<br>
 * URIInitializer provides a means to set host, port, userName, password but 
 * currently userName and password mechanism has not been provided.<br>
 * 2 methods are provided for insert and update.<br>
 * addCandidate will take candidate object<br>
 * updateCandidate will take candidateDto object.
 * */
//@Component
public class App {

	@Autowired
	private ApplicationContext factory;

	/**
	 * Note that this will be overwritten everytime addCandidate is called,
	 * so it need not to be reinitialized on each request.<br>
	 * But this might lead to single-processing enviornment.<br>
	 * So, better to allocate new candidate everytime... and hence this has been taken
	 * care at controller side itself.
	 * */
	Candidate candy;
	/**
	 * Service object for stated purposes.
	 * */
	CandidateDaoImpl cdi;
	/**
	 * Note that this will be overwritten everytime addCandidate is called,
	 * so it need not to be reinitialized on each request.
	 * */
	CandidateDto cdto;

	public boolean addCandidate(HttpServletRequest request) {
		try {
			//To avoid database misManipulation, might be I am wrong here, lack of knowledge.<br>
			//This synchronization is for the case, if any multi-threading concept is 
			//introduced before this point....<br> i think server takes care of that
			//so might be no need to use this synch.
			synchronized(this) {
				candy=new Candidate();//factory.getBean(Candidate.class);
				Candidate candy=getCandidateObject(request);
				cdi=new CandidateDaoImpl();//factory.getBean(CandidateDaoImpl.class);
				cdi.addCandidate(candy);
			}
		}catch(Exception er) {
			er.printStackTrace();
			return false;
		}finally{
                        candy=null;
                        cdi=null;
                }
		return true;
	}

	private Candidate getCandidateObject(HttpServletRequest request) {
		candy.setCandidateId(request.getParameter("candidateId"));
		candy.setEmail(request.getParameter("email"));
		candy.setExperience(request.getParameter("experience"));
		candy.setJoiningDate(getDate(request.getParameter("joiningDate")));
		candy.setName(request.getParameter("name"));
		candy.setOfferDate(getDate(request.getParameter("offerDate")));
		candy.setOfferedSalary(request.getParameter("offeredSalary"));
		candy.setPhone(request.getParameter("phone"));
		candy.setPreviousSalary(request.getParameter("previousSalary"));
		return candy;
	}

	private Date getDate(String str) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		//This  can be modified later, wrong time but correct date.
		LocalDateTime localDateTime = LocalDateTime.parse(str+"T00:00:00");
		Date date = Date.from(localDateTime.atZone(defaultZoneId).toInstant());
		return date;
	}

	public boolean updateCandidate(HttpServletRequest request) {
		try {
			synchronized(this) {
				cdto=new CandidateDto();//factory.getBean(CandidateDto.class);
				CandidateDto cdto=getCandidateDtoObject(request);
				cdi=new CandidateDaoImpl();//factory.getBean(CandidateDaoImpl.class);
				cdi.updateCandidate(cdto);
			}
		}catch(Exception er) {
			er.printStackTrace();
			return false;
		}finally{
                        cdi=null;cdto=null;
                }
		return true;
	}

	private CandidateDto getCandidateDtoObject(HttpServletRequest request) {
		cdto.setCandidateId(request.getParameter("candidateId"));
		cdto.setFinallyJoined(Boolean.valueOf(request.getParameter("finallyJoined")));

		Date date;

		String oAD=request.getParameter("offerAcceptedDate");
		date=(oAD==null)?null:getDate(oAD);
		cdto.setOfferAcceptedDate(date);

		String oDD=request.getParameter("offerDeclinedDate");
		date=(oDD==null)?null:getDate(oDD);
		cdto.setOfferDeclinedDate(date);

		return cdto;
	}


	/**
	 * Below method provides a sample implementation to check add and update working.
	 * */
	/*public static void main(String[] arg) {

		System.out.println("HIHII");
		ApplicationContext app=new AnnotationConfigApplicationContext(ModelServiceConfig.class);
		Candidate cd=app.getBean(Candidate.class);
		cd.setCandidateId("2020");
		cd.setEmail("vmngl7@gmail.com");
		cd.setExperience("11 months");
		cd.setOfferDate(new Date());
		cd.setOfferedSalary("1600000");
		cd.setPreviousSalary("1525000");
		cd.setPhone("+918930985080");
		cd.setAdminData(null);

		CandidateDao cdo=app.getBean(CandidateDaoImpl.class);
		cdo.addCandidate(cd);

		cd.setCandidateId("2020");
		cd.setEmail("vmngl7@gmail.com");
		cd.setExperience("11 months");
		cd.setOfferDate(new Date());
		cd.setOfferedSalary("1600000");
		cd.setPreviousSalary("1525000");
		cd.setPhone("+918930985080");
		cd.setAdminData(null);
		cdo.addCandidate(cd);

		CandidateDaoImpl cdi=(CandidateDaoImpl) cdo;
		//printAll(cdi);

		CandidateDto cto=new CandidateDto();
		cto.setCandidateId("16463");
		Boolean bool=Boolean.valueOf(Boolean.TRUE);
		cto.setFinallyJoined(bool);
		cto.setOfferAcceptedDate(new Date());
		//cdi.updateCandidate(cto);
	 */
	/*
		MongoTemplate mt=app.getBean(MongoTemplate.class);
		Query query=app.getBean(Query.class);
		query.addCriteria(Criteria.where("candidateId").is(cto.getCandidateId()));

		//Note that mt.find(query,class is not working)... requirimg a collectionName..
		List<Candidate> ll=mt.find(query, Candidate.class, Candidate.class.getSimpleName());
		System.out.println(ll.size()+" as size--> "+ll.get(0));

		Update ud=new Update();
		ud.set("adminData.finallyJoined", bool);

		mt.updateFirst(query, ud,Candidate.class.getSimpleName());
	 */
	/*printAll(cdi);



	}*/

	/*
	static void printAll(CandidateDaoImpl cdi) {

		List<Candidate> list=cdi.getAllCandidates();
		Iterator<Candidate> itr=list.iterator();
		while(itr.hasNext()){
			Candidate ccc=itr.next();
			System.out.println(
					ccc.getCandidateId()+"\n"+
							ccc.getEmail()+"\n"+
							ccc.getExperience()+"\n"+
							ccc.getName()+"\n"+
							ccc.getOfferedSalary()+"\n"+
							ccc.getPhone()+"\n"+
							ccc.getPreviousSalary()+"\n"+
							ccc.getJoiningDate()+"\n"+
							ccc.getOfferDate()+"\n"+
							ccc.getAdminData()+"\n"+
							ccc.getAdminData().getCreationDateTime()+"\n"+
							ccc.getAdminData().getFinallyJoined()+"\n"+
							ccc.getAdminData().getLastModifiedDateTime()+"\n"+
							ccc.getAdminData().getOfferAcceptedDate()+"\n"+
							ccc.getAdminData().getOfferDeclinedDate()
					);
		}

	}*/

}
