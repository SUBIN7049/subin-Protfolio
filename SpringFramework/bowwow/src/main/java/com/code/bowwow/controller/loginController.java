package com.code.bowwow.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.bowwow.entity.product;
import com.code.bowwow.entity.user;
import com.code.bowwow.service.bowwowService;

@Controller
public class loginController {
	
	@Autowired
	private bowwowService bowwowService;

	@GetMapping("/login")
	public String LoginPage(Model theModel) {
		
		return "user/login_form";
	}
	
	//회원가입 
	@PostMapping("/signup")
	public String signup(user u, Model theModel) {
		if(bowwowService.getUser(u.getEmail()).isEmpty()) {
			bowwowService.saveUser(u);
			
			List<product> theProducts = bowwowService.getProductsDESC();
			theModel.addAttribute("products",theProducts);
			return "home";
		}else {
			//회원가입 중복체크 후 에러알람 발생
			theModel.addAttribute("error", "error");
			return "user/signup_form";
		}
		
		
	}
	
	@GetMapping("/signup_form")	
	public String signupForm() {
		return"user/signup_form";
	}
	
	@PostMapping("/Mypage")
	   public String Mypage(user modifyUser,Principal p, Model theModel,HttpServletRequest request) {
	      if(request.getParameter("ddd")!=null) {
	         bowwowService.saveUser(modifyUser);   //변경한 유저정보 업데이트
	         List<product> theProducts = bowwowService.getProductsDESC(); //평점 높은 제품 노출
	         theModel.addAttribute("products",theProducts);
	         return"home";
	      }
	      List<user> theUser = bowwowService.getUser(p.getName());  //유저정보 출력
	      theModel.addAttribute("edituser", theUser.get(0));
	      
	      return "user/Mypage";
	   }
	
	@GetMapping("/master_form")   
	   public String master(Model theModel) {
	      List<user> theuser = bowwowService.masteruser();
	      for(int i=0; i<theuser.size(); i++) {
	         String usergrade = theuser.get(i).getGrade();
	         String removerole=usergrade.substring(5);
	         theuser.get(i).setGrade(removerole);
	      }
	      theModel.addAttribute("user",theuser);
	      return "user/master_form";
	   }
	   
	
	@GetMapping("/user_btn")
	public String usergrade_update(Principal p, @RequestParam("grade")String grade,@RequestParam("useremail")String useremail,user user,Model theModel) {
		List<user> theUser = bowwowService.getUser(p.getName());
		bowwowService.usergrade_update(grade,useremail);
		return "redirect:/master_form";
	}
}
