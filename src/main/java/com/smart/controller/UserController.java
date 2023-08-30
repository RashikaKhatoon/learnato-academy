package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.smart.dao.CoursesRepository;
import com.smart.dao.CoursyRepository;
import com.smart.dao.MyOrderRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.Courses;
import com.smart.entities.MyOrder;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	//method for adding common data to response
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private CoursyRepository coursyRepository;
		
	@Autowired
	private MyOrderRepository myOrderRepository;
	 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@ModelAttribute
	public void addCommonData(Model model,Principal principal) {
	String userName=principal.getName();
	System.out.println("USERNAME"+userName);
	//get the user using username(Email)
	User user=userRepository.getUserByUserName(userName);
	model.addAttribute("USER" +user);
	model.addAttribute("user",user);
	}
	
	//dashboard home
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {

	model.addAttribute("title","User Dashboard");
		return "normal/user_dasboard";
	}
	@RequestMapping("/offline")
	public String offline(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/offline";
	}
	@RequestMapping("/Ruby.html")
	public String Ruby(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/Ruby";
	}
	@RequestMapping("/Perl.html")
	public String Perl(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/Perl";
	}
	@RequestMapping("/C.html")
	public String C(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/C";
	}
	@RequestMapping("/Python.html")
	public String C1(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/Python";
	}
	@RequestMapping("/HTML.html")
	public String HTML(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/HTML";
	}
	@RequestMapping("/CSS.html")
	public String CSS(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/CSS";
	}
	@RequestMapping("/AI.html")
	public String AI(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/AI";
	}
	@RequestMapping("/Java.html")
	public String Java(Model model) {

	model.addAttribute("title","User Dashboard");
		return "normal/Java";
	}
	
	@RequestMapping("/show-coursy")
	public String show_coursy(Model model) {

	model.addAttribute("title","All Coursers");
	//courses ka list bhejna hai toh
	
		return "normal/show_coursy";
	}
	
	//open add courses controller
	@RequestMapping("/add-courses")
	public String openAddCoursesForm(Model model)
	{
		System.out.println("Entering add-courses");
		
     model.addAttribute("title", "Add Courses");
	
	 model.addAttribute("courses", new Courses());
	 System.out.println("Exiting add-courses");
	
		return "normal/add_courses_form";
		
	}
	
	//process add  courses form
	@PostMapping("/process-courses")
	public String processCourses(@RequestParam("coursename") String coursename,
			@RequestParam("courselevel") String courselevel,
			@RequestParam("coursedate") String coursedate,
			@RequestParam("coursedescription") String coursedescription, 
			@RequestParam("coursetitlename") String coursetitlename, 
			@RequestParam("courseimage") MultipartFile imgfile,
			@RequestParam("coursepdf") MultipartFile pdfile,
			@RequestParam("coursevideo") MultipartFile vidfile,Principal principal)
	{
		try {
			System.out.println("Principal:"+principal.toString());
			System.out.println("Entering process-courses");
//			System.out.println("DATA" + courses);
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			// processing and uploading file..
			String fileName=StringUtils.cleanPath(imgfile.getOriginalFilename());
			if(fileName.isEmpty()) {
				System.out.println("Error in image");
			} else {
				Courses c = new Courses();
				c.setCname(coursename);
				c.setTname(coursetitlename);
				c.setLevel(courselevel);
				//save Image
				c.setCimage(imgfile.getOriginalFilename());
				String imgfilename = imgfile.getOriginalFilename();
				System.out.println(imgfilename);
				File imageFile = new ClassPathResource("static/img").getFile();
				System.out.println("Image Saved to:"+imageFile);
				Path imagePath = Paths.get(imageFile.getAbsolutePath() + File.separator + imgfilename);
				System.out.println("Full path of Image:"+imagePath);
				Files.copy(imgfile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image successfully uploaded");
				

				
				//save pdf
				c.setPdf(pdfile.getOriginalFilename());
				File pdfFile = new ClassPathResource("static/img").getFile();
				System.out.println("Pdf Saved to:"+pdfFile);
				Path pdfPath = Paths.get(pdfFile.getAbsolutePath()+File.separator+pdfile.getOriginalFilename());
				System.out.println("Full path of Pdf:"+pdfPath);
				Files.copy(imgfile.getInputStream(),pdfPath,StandardCopyOption.REPLACE_EXISTING); 
				System.out.println("Pdf successfully uploaded"); 


				
				//save Video
				c.setCvideo(vidfile.getOriginalFilename());
				File vidFile = new ClassPathResource("static/img").getFile();
				System.out.println("Video Saved to:"+vidFile);
				Path vidPath = Paths.get(vidFile.getAbsolutePath()+File.separator+vidfile.getOriginalFilename());
				System.out.println("Full path of Video:"+vidPath);
				Files.copy(imgfile.getInputStream(),vidPath,StandardCopyOption.REPLACE_EXISTING); 
				System.out.println("Video successfully uploaded"); 

				c.setDate(coursedate);
				c.setCdescription(coursedescription);

				user.getCourses().add(c);
				c.setUser(user);
				this.userRepository.save(user);
				System.out.println("Added to database");
				System.out.println("Exiting process-courses");
			}

		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
			e.printStackTrace();
		}
		return "normal/add_courses_form";
	}
//show courses
//per page=5[n]
//current page = 0[page]	
   @GetMapping("/show-courses/{page}")
   public String showCourses(@PathVariable("page") Integer page,Model m,Principal principal)
   {
	  m.addAttribute("title","Show User Courses");
	  //courses list display
	  
//	  String userName=principal.getName();
//	  User user=this.userRepository.getUserByUserName(userName);
//	  List<Courses> courses=user.getCourses();
	  
	  String userName=principal.getName();
	  User user=this.userRepository.getUserByUserName(userName);
	  Pageable pageable=PageRequest.of(page,5);
	  
	  Page<Courses> courses=this.coursesRepository.findCoursesByUser(user.getId(),pageable);
	  m.addAttribute("courses",courses);
	  m.addAttribute("currentPage",page);
	  m.addAttribute("totalPages", courses.getTotalPages());
	return "normal/show_courses";
	   
   }
	//show particular course details
   @RequestMapping("/{cid}/course/")
	   public String showCoursesDetail(@PathVariable("cid") Integer cid,Model model,Principal principal)
	   {
	       System.out.println("CID"+cid);
	       
	       Optional<Courses> coursesOptional=this.coursesRepository.findById(cid);
	       Courses courses=coursesOptional.get();
	       
	       //
	       String userName=principal.getName();
	       User user=this.userRepository.getUserByUserName(userName);
	       
	       if(user.getId()==courses.getUser().getId())
	       {
	       model.addAttribute("courses",courses);
	       model.addAttribute("title",courses.getCname());
	       }
		   return "normal/courses_details";
	   }
   //delete courses handler
   @GetMapping("/delete/{cid}")
   public String deleteCourses(@PathVariable("cid") Integer cid,Model model,HttpSession session)
   {
	   Courses courses=this.coursesRepository.findById(cid).get();
	   
	   //check....
	   System.out.println("Courses"+courses.getCid());
   courses.setUser(null);
	   
	   this.coursesRepository.delete(courses);
	   
	
	   
	   System.out.println("Contact"+courses.getCid());
	   System.out.println("Deleted");
	   
	   return "redirect:/user/show-courses/0";
   }
   
   //open update form handler
   
   @PostMapping("/update-contact/{cid}")
   public String updateForm(@PathVariable("cid") Integer cid,Model m)
   {
	   m.addAttribute("title","Update Courses");
	  
	   Courses courses=this.coursesRepository.findById(cid).get();
	   
	   m.addAttribute("courses", courses);
	   return "normal/update_form";
   }
   
   //update handling
   
   
   //update course handler
   @RequestMapping(value="/process-update", method=RequestMethod.POST)
   public String updateHandler(@ModelAttribute Courses courses,@RequestParam("courseimage") MultipartFile imgfile,
			@RequestParam("coursepdf") MultipartFile pdfile,
			@RequestParam("coursevideo") MultipartFile vidfile,Model m,HttpSession session,Principal principal)
   {
	   try
	   {
		   //old course details
		  Courses oldcoursesDetail= this.coursesRepository.findById(courses.getCid()).get();
		   System.out.println("COURSES NAME"+oldcoursesDetail.getCname());
		   System.out.println("COURSES ID"+oldcoursesDetail.getCid());
		 if(!imgfile.isEmpty())
		 {
			 //file work
			 //rewrite
			 //delete old pic
			 File deleteFile=new ClassPathResource("static/img").getFile();
			 File file1=new File(deleteFile,oldcoursesDetail.getCimage());
			file1.delete();
			 //update new pic
			 String imgfilename = imgfile.getOriginalFilename();
				System.out.println(imgfilename);
				File imageFile = new ClassPathResource("static/img").getFile();
				System.out.println("Image Saved to:"+imageFile);
				Path imagePath = Paths.get(imageFile.getAbsolutePath() + File.separator + imgfilename);
				System.out.println("Full path of Image:"+imagePath);
				Files.copy(imgfile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image successfully uploaded");
		 }
		 else
		 {
			 courses.setCimage(oldcoursesDetail.getCimage());
		 }
		
		 User user=this.userRepository.getUserByUserName(principal.getName());
		 courses.setUser(user);
		this.coursesRepository.save(courses);
		 
		 session.setAttribute("message", new Message("Your course has been updated.....","success"));
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	  // System.out.println("COURSES NAME"+oldcoursesDetail.getCname());
	  // System.out.println("COURSES ID"+oldcoursesDetail.getCid());
	return "redirect:/user/"+courses.getCid()+"/course/";
	   
   }
   
   
//open add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model)
	{
		
     model.addAttribute("title", "Add Your Details");
	
	 model.addAttribute("contact",new Contact());
	
		return "normal/add_contact_form";
		
	}

//processing add contact form
	
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact,Principal principal,HttpSession session){
		
		try {
		String name=principal.getName();
		User user=this.userRepository.getUserByUserName(name);
		 contact.setUser(user) ;
		 user.getContacts().add(contact); 

		
          this.userRepository.save(user);
           System.out.println(user); 
           System.out.println("added to database");
           //message success
           session.setAttribute("message",new Message("Successfully Registered!!!","success"));
          
		}
		catch (Exception e) {
			System.out.println("Error"+e.getMessage());
			e.printStackTrace();
			//message error
			session.setAttribute("message",new Message("Something went wrong!!!","error"));
			
		}
		 return "normal/add_contact_form";
		 
	}
		 //show contacts handler
		 @GetMapping("/profile")
		 public String showContacts(Model model) {
			 
		model.addAttribute("title", "Profile Page");
		 return "normal/profile";
		 
		
	}
		 
		 //open settings handler
		 @GetMapping("/settings")
		 public String openSettings()
		 {
			 return "normal/settings";
		 
		 }
		//change password handler
		   
		 @PostMapping("/change-password")
		 public String changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,Principal principal,HttpSession session) {
		 

			 System.out.println("OLD PASSWORD"+oldPassword);
			 System.out.println("NEW PASSWORD"+newPassword);
			 String userName=principal.getName();
			 User currentUser=this.userRepository.getUserByUserName(userName);
			 System.out.println(currentUser.getPassword());
			 
			 
			 if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword()))
			 {
				//change password
				 currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
				 this.userRepository.save(currentUser);
				//message success
		           session.setAttribute("message",new Message("Your password has been successfully changed!!!","success"));
				 
			 }
			 else
			 {
				//message success
		           session.setAttribute("message",new Message("OOPS!!!!Wrong old password..","danger"));
			 }
			 
		 return "redirect:/user/settings";
		 
		 }
		 //creating order for payment
		 @PostMapping("/create_order")
		 @ResponseBody
		 public String createOrder(@RequestBody Map<String,Object> data,Principal principal) throws Exception
		 {
			 //System.out.println("Hey order function ex.");
			 System.out.println(data); 
			 int amt=Integer.parseInt(data.get("amount").toString());
			 var client=new RazorpayClient("rzp_test_PHr2cHwZcE2llZ", "af5KE1zonBdlxZ8WIIiUscOA");
			 
			 JSONObject ob=new JSONObject();
			 ob.put("amount", amt*100);
			 ob.put("currency", "INR");
			 ob.put("receipt", "txn_235432");
			 
			 //creating new order
			 Order order=client.Orders.create(ob);
			 System.out.println(order);
			 
			 //save the order in database
			 MyOrder myOrder=new MyOrder();
			 
			 myOrder.setAmount(order.get("amount")+"");
			 myOrder.setOrderId(order.get("id"));
			 myOrder.setPaymentId(null);
			 myOrder.setStatus("created");
			 myOrder.setUser(this.userRepository.getUserByUserName(principal.getName()));
			 myOrder.setReceipt(order.get("receipt"));
			 
			 this.myOrderRepository.save(myOrder);
			 
			 //if you want you can save this to your database
			 
			 
			 
			 return order.toString();

		 }
			//payment home
			@RequestMapping("/payment")
			public String payment(Model model,Principal principal) {

			model.addAttribute("title","payment");
				return "normal/payment";
			}
		 @PostMapping("/update_order")
		 public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data)
		 {
			MyOrder myorder= this.myOrderRepository.findByOrderId(data.get("order_id").toString());
			 myorder.setPaymentId(data.get("payment_id").toString());
			 myorder.setStatus(data.get("status").toString());
			 
			 this.myOrderRepository.save(myorder);
			
			
			System.out.println(data);
			 return ResponseEntity.ok(Map.of("msg","updated"));
		 }
		 
		 }

