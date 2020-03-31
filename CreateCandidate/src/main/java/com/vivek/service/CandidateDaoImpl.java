package com.vivek.service;


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
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoException;
import com.vivek.model.AdminData;
import com.vivek.model.Candidate;
import com.vivek.model.CandidateDto;



/**
 * This class provides implementation for add and update candidate.<br>
 * @Autowired objects will process either add or update at a time...Naa it's only for sharable objects so not using this tag.<br>
 * With every call made to it's methods, a new object of Service to be created
 * by it's caller. This memory can be an overhead at middle layer for application logic
 * flow & hence I think that threading can be used.. at initial level of logic..
 * */
//@Repository
public class CandidateDaoImpl implements CandidateDao {

	
	@Autowired
	private ApplicationContext factory;

	private MongoTemplate mongoTemplate;//=factory.getBean(MongoTemplate.class);
	private Query query;//=factory.getBean(Query.class);
	private Update update;//e=factory.getBean(Update.class);
	private AdminData admin;
	private static MyConnection myCon=new MyConnection();

	private final static String collectionName=Candidate.class.getSimpleName();

	// private /*static */ApplicationContext factory=new AnnotationConfigApplicationContext(ModelServiceConfig.class);
	//above statement leading to infinite  loop of constructors.
	/*static {
    	factory=getModelServiceContext();
    }
	 */
	/**BELOW IS GIVING AN INFINITE LOOP...<br>
	 * Even if there are multiple instances of candidateDAOImpl,
	 * this method can be shared b/w all of them.....
	 * because with every new service request from @controller, there
	 * would be new instances of every @component but the instances returned by
	 * this method will always be different.
	 * */
	/*    public static ApplicationContext getModelServiceContext() {
		if(factory==null)
			factory=new AnnotationConfigApplicationContext(ModelServiceConfig.class);
		return factory;
	}
	 */  
	/**
	 * Send data of candidate only.<br>
	 * and CAN append null for 
	 * adminData:{creationDate, modificationDate, finallyJoined, offerAcceptedDate, offerDeclinedDate}
	 * */
	public void addCandidate(Candidate candidate) {
		/*try {*/
		mongoTemplate=myCon.getMT();//factory.getBean(MongoTemplate.class);
		if(!mongoTemplate.collectionExists(collectionName)) {
			mongoTemplate.createCollection(collectionName);
		}

		if(!validateCandidate(candidate))throw new MongoException("validateCandidate() Failed...");
		//If adminData is not null , override @Bean (admin)
		if(candidate.getAdminData()!=null)
			admin=candidate.getAdminData();
		else admin=new AdminData();//factory.getBean(AdminData.class);
		admin.setCreationDateTime(new Date()); 
		
		admin.setLastModifiedDateTime(admin.getCreationDateTime());
		candidate.setAdminData(admin);
		mongoTemplate.insert(candidate,collectionName);
		/*}catch(Exception er) {
			er.printStackTrace();
			//System.exit(1);
		}*/
	}

	/**
	 * Assuming that email, phone, offeredData, experience, previousSalary, offeredSalary
	 *  are pre-validated at front-end UI, returning true.<br>
	 * Otherwise, validation code can be implemented here.
	 * */
	private Boolean validateCandidate(Candidate candy) {
		return checkDuplicacy(candy);
		//return true;
	}

	private boolean checkDuplicacy(Candidate candy) {

		//query=new Query();//This must be changed to getBean() from applicationContext object, 
		//otherwise I don't think, it's a good practice.
		//..Now i have commented it as, at lowest layer, no need to allocate new objects...
		//allocate only at middle or higher layer within logic-flow.
		//sync. can be achieved at middle or higher level too.
		//another reason is also because of my experience with MYSql db says that db takes care of 
		//sync issues by itself.
		query=new Query();//factory.getBean(Query.class);
		query.addCriteria(Criteria.where("candidateId").is(candy.getCandidateId()));

		return (mongoTemplate.find(query, Candidate.class, Candidate.class.getSimpleName()).size())>0?false:true;
		//return true;
	}


	/**
	 * retrieves all candidate records 
	 * */
	public List<Candidate> getAllCandidates() {
		/*try {*/
		mongoTemplate=myCon.getMT();//factory.getBean(MongoTemplate.class);
		return mongoTemplate.findAll(Candidate.class, collectionName);
		/*}catch(Exception er) {
			er.printStackTrace();
			return null;
		}*/
	}

	/**
	 * Update candidate on basis on ID.<br>
	 * ID to be sent to CandidateDaoImpl.<br>
	 * Update {finallyJoined, offerAcceptedDate, offerDeclinedDate} on basis of ID.<br>
	 * In later development stages, this method can be made more generalized for update object.
	 */
	public void updateCandidate(CandidateDto newDetails) {
		/*try {*/
		query=new Query();//factory.getBean(Query.class);
		query.addCriteria(Criteria.where("candidateId").is(newDetails.getCandidateId()));
		//checking dates and joinboolean
		//Although below check depends upon businessPeople's working enviornment.
		//Still, giving basic functionality...
		Boolean bool=newDetails.getFinallyJoined();
		Date oDD=newDetails.getOfferDeclinedDate();
		System.out.println("entering into update");

		//if bool=true & (declinedDate!=null )-->throw;....can be further checked as acceptDate<declinedDate, but me lazy.
		if(bool && oDD!=null)
			throw new MongoException("Invalid Entry, if candidate has joined then how can offerDeclinedDate be notNull"
					+ "\n...may be someone's mood got changed. Still, throwing from here.");

		//newDetails.set
		update=new Update();//factory.getBean(Update.class);
		update.set("adminData.finallyJoined", newDetails.getFinallyJoined());
		update.set("adminData.offerAcceptedDate", newDetails.getOfferAcceptedDate());
		update.set("adminData.offerDeclinedDate", newDetails.getOfferDeclinedDate());
		update.set("adminData.lastModifiedDateTime", new Date());
		mongoTemplate=myCon.getMT();//factory.getBean(MongoTemplate.class);
		List<Candidate> list=mongoTemplate.find(query,Candidate.class, Candidate.class.getSimpleName());
		System.out.println("trying to update"+ update+list);
		//Note that updateFirst(query,update,Candidate.class) is not working...
		if(list.size()>0)
			mongoTemplate.updateFirst(query, update, Candidate.class.getSimpleName());
		else throw new MongoException("Candidate Not Found");
		/*}catch(Exception er) {
			er.printStackTrace();
		}*/
	}

}
