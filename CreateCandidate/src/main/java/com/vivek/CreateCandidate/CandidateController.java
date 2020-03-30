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
		ModelAndView mv=new ModelAndView();
		App app=new App();

		Integer k=Integer.valueOf(900);
		boolean result=app.addCandidate(request);
		mv.setViewName("Display.jsp");
		mv.addObject("result",k+(result?"<br><br>"
				+ "You have successfylly rejistered"+request.getParameter("candidateId"):"Error, check stackTrace"));
                app=null;
		return mv;
	}

	@RequestMapping("/update")
	public ModelAndView updateCandidate(HttpServletRequest request, HttpServletResponse response) {
		//AnnotationConfigApplicationContext acac(ModelServiceConfig.class);
		ModelAndView mv=new ModelAndView();
		App app=new App();

		boolean result=app.updateCandidate(request);
		Integer k=Integer.valueOf(800);
		mv.setViewName("Display.jsp");
		mv.addObject("result",k+(result?"<br><br>"
				+ "You have successfylly updated"+request.getParameter("candidateId"):"Error, check stackTrace"));
		app=null;
                return mv;
	}

}
