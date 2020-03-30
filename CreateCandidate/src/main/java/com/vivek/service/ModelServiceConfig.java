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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.validation.beanvalidation.*;//not required all classes, but i am lazy.
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.MongoClient;
import com.vivek.CreateCandidate.App;
import com.vivek.model.AdminData;
import com.vivek.model.Candidate;
import com.vivek.model.CandidateDto;

@Configuration
@ComponentScan({"com.vivek.CreateCandidate","com.vivek.service","com.vivek.model"})
public class ModelServiceConfig {

	public ModelServiceConfig() {
		System.out.println("Called in modelservicecofig");
	}

	@Bean
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	@Bean
	public Candidate getCandy() {
		return new Candidate();
	}
	
	@Bean
	public CandidateDto getCDto() {
		return new CandidateDto();
	}
	
	@Bean
	public AdminData getAdminData() {
		return new AdminData();
	}
	
	@Bean
	public App getApp() {
		return new App();
	}
	
	@Bean
	public CandidateDaoImpl getCDI() {
		return new CandidateDaoImpl();
	}
	
	@Bean
	public URIInitializer getUriI() {
		return new URIInitializer();
	}
	
	@Bean
	LocalValidatorFactoryBean getLCFB() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	ValidatingMongoEventListener getVMEL() {
		return new ValidatingMongoEventListener(getLCFB());
	}


	@Bean
	Date getDate() {
		return new Date();
	}

	@Bean
	MongoTemplate getMT() {
		MongoTemplate mt=new MongoTemplate(getMC(),getUriI().getDbName());
		return mt;
	}
	@Bean
	MongoClient getMC() {
		MongoClient mc=new MongoClient(getUriI().getHost(),getUriI().getPort());
		return mc;	
	}

	@Bean
	public Query getQuery() {
		return new Query();
	}

	@Bean
	public Update getUpdate() {
		return new Update();
	}


}
