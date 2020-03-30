package com.vivek.service;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

public class MyConnection {

	private static MongoTemplate mt; 
	
	private static MongoClient mc;
	
	private static URIInitializer uriI;
	
	MongoTemplate getMT() {
		if(mt==null)
			mt=new MongoTemplate(getMC(),getUriI().getDbName());
		return mt;
	}
	
	MongoClient getMC() {
		if(mc==null)
			mc=new MongoClient(getUriI().getHost(),getUriI().getPort());
		return mc;	
	}
	
	public URIInitializer getUriI() {
		if(uriI==null)
			uriI=new URIInitializer();
		return uriI;
	}
	

	
}
