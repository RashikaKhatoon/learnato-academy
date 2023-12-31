package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {


	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	
	@Autowired
	private UserRepository userRepository;
	@RequestMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("title", "Home-EasyWEASY");
		return "home";
			}
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title", "Register-EasyWEASY");
		model.addAttribute("user", new User());
		return "signup";
			}

			
	 @RequestMapping("/signin") 
	 public String login(Model model) {
	 model.addAttribute("title", "Login-EasyWEASY"); 
	 return "login";
	       }
			 
	@RequestMapping("/contact")
	public String contact(Model model)
	{
		model.addAttribute("title", "Contact-EasyWEASY");
		return "contact";
			}
	@RequestMapping("/demo")
	public String demo(Model model)
	{
		model.addAttribute("title", "Contact-EasyWEASY");
		return "demo";
			}
	@RequestMapping("/classroom")
	public String classroom(Model model)
	{
		model.addAttribute("title", "Contact-EasyWEASY");
		return "classroom";
			}
	@RequestMapping("/courses")
	public String courses(Model model)
	{
		model.addAttribute("title", "Courses-EasyWEASY");
		return "courses";
			}
	@RequestMapping("/contacting")
	public String contacting(Model model)
	{
		model.addAttribute("title", "Contact-EasyWEASY");
		return "contacting";
			}
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "About-EasyWEASY");
		return "about";
			}
	@RequestMapping("/teachers")
	public String teachers(Model model)
	{
		model.addAttribute("title", "Teachers-EasyWEASY");
		return "Teachers";
			}
	@RequestMapping("/single")
	public String single(Model model)
	{
		model.addAttribute("title", "Teachers-EasyWEASY");
		return "Single";
			}
	@RequestMapping("/blog")
	public String blog(Model model)
	{
		model.addAttribute("title", "Teachers-EasyWEASY");
		return "blog";
			}
	@RequestMapping("/base")
	public String navbar(Model model)
	{
		model.addAttribute("title", "Contact-EasyWEASY");
		return "base";
			}
	//handler for registering user
	@RequestMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1,@RequestParam(value="agreement",defaultValue="false")boolean agreement,Model model,HttpSession session)
	{
		try {
			if(!agreement)
			{
				System.out.println("You have not agreed terms and conditions");
				throw new Exception();
			}
			
			if(result1.hasErrors())
			{
				System.out.println("ERROR"+result1.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnable(true);
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("Agreement"+agreement);
			System.out.println("User"+user);
			
			User result=this.userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message",new Message("Successfully Registered!!!","alert-success"));
			return "signup";
		
		}
		catch (Exception e){
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message",new Message("Something went wrong!!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
		
	}
}
