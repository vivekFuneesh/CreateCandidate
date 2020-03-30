package com.vivek.CreateCandidate;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.vivek.service.ModelServiceConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	public WebInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ModelServiceConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

}
