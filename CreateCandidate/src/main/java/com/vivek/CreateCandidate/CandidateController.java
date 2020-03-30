package com.vivek.CreateCandidate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.vivek.service.ModelServiceConfig;

@Controller
public class CandidateController {

	@Autowired
	private ApplicationContext factory;

	@RequestMapping("/add")
	public ModelAndView addCandidate(HttpServletRequest request, HttpServletResponse response) {
		
		//AnnotationConfigApplicationContext acac(ModelServiceConfig.class);
		ModelAndView mv=factory.getBean(ModelAndView.class);//new ModelAndView();
		App app=factory.getBean(App.class);//new App();

		Integer k=Integer.valueOf(900);
		boolean result=app.addCandidate(request);
		mv.setViewName("Display.jsp");
		mv.addObject("result",k+(result?"<br><br>"
				+ "You have successfylly rejistered"+request.getParameter("candidateId"):"Error, check stackTrace"));
		return mv;
	}

	@RequestMapping("/update")
	public ModelAndView updateCandidate(HttpServletRequest request, HttpServletResponse response) {
		//AnnotationConfigApplicationContext acac(ModelServiceConfig.class);
		ModelAndView mv=factory.getBean(ModelAndView.class);//new ModelAndView();
		App app=factory.getBean(App.class);//new App();

		boolean result=app.updateCandidate(request);
		Integer k=Integer.valueOf(800);
		mv.setViewName("Display.jsp");
		mv.addObject("result",k+(result?"<br><br>"
				+ "You have successfylly updated"+request.getParameter("candidateId"):"Error, check stackTrace"));
		return mv;
	}

}
