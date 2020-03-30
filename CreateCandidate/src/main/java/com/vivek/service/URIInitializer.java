package com.vivek.service;

import org.springframework.stereotype.Component;

//@Component
public class URIInitializer {

	private String host="localhost";
	private int port=27017;
	private String userName=null;
	private String password=null;
	private String dbName="sample0";

	public URIInitializer() {}

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String db) {
		dbName=db;
	}



}
