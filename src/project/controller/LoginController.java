package project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import project.bean.Account;
import project.entity.Employee;
import project.entity.Image;
import project.entity.Product;
import project.entity.User;

@Transactional
@Controller
@RequestMapping("/login/")
public class LoginController {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("out")
	public String out(ModelMap model,
					HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user2");
		session.removeAttribute("user3");
		session.removeAttribute("user4");
		
		
		return "redirect:/container/index.htm";
	}
	
	@RequestMapping("employee")
	public String login(ModelMap model) {
		Account acc = new Account();
		model.addAttribute("acc", acc);
		
		
		return "admin/form/login";
	}

	
	@RequestMapping("employee2")
	public String login2(ModelMap model,
						@Validated @ModelAttribute("acc")Account acc1,
						HttpServletRequest request,
						BindingResult errors) {
		if(errors.hasErrors()) {
			return "redirect:/login/employee.htm";
		}else {
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			List<User> list = getUsers();
			model.addAttribute("users", list);
			
			String userName = acc1.getUserName();
			String pass = acc1.getPassWord();
			
			int kT=0;
			int dem=-1, vT=-1;
			String u2="", p2="";
			for(User u1 : list) {
				dem++;
				u2 = u1.getUserName();
				p2 = u1.getPassWord();
				
				if((userName.equals(u2)) && (pass.equals(p2))) {
					kT=1;
					vT = dem;
					break;
				}
			}
			if(kT == 1) {
				list = getUsers();
				User user2 = list.get(vT);
				HttpSession session2 = request.getSession();
				session2.setAttribute("user2", user2);
				
			}else {
				
				return "redirect:/login/employee.htm";
			}
			User user2 = list.get(vT);
			if(user2.getRule()>=1) {
				HttpSession session2 = request.getSession();
				session2.setAttribute("user2", user2);
				session = factory.getCurrentSession();
				String hql = "FROM Product";
				Query query = session.createQuery(hql);
				List<Product> list2 = query.list();
				model.addAttribute("products", list2);
			}else {
				return "redirect:/login/employee.htm";
			}
			
		}
		
		
		
		return "redirect:/import/index.htm";
		
	}
	
	@RequestMapping("user")
	public String user(ModelMap model) {
		Account acc = new Account();
		model.addAttribute("acc", acc);
		
		
		return "admin/form/login2";
	}
	
	@RequestMapping("user2")
	public String user2(ModelMap model,
						HttpServletRequest request,
						@Validated @ModelAttribute("acc")Account acc,
						BindingResult errors) {
		if(errors.hasErrors()) {
			Account acc2 = new Account();
			model.addAttribute("acc", acc2);
			
			return "admin/form/login2";
		}else {
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			model.addAttribute("acc", acc);
			List<User> list = getUsers();
			
			String userName = acc.getUserName();
			String pass = acc.getPassWord();
			
			int kT=0;
			int dem=-1, vT=-1;
			String u2="", p2="";
			for(User u1 : list) {
				dem++;
				u2 = u1.getUserName();
				p2 = u1.getPassWord();
				
				if((userName.equals(u2)) && (pass.equals(p2))) {
					kT=1;
					vT = dem;
					break;
				}
			}
			if(kT == 1) {
				
			}else {
				Account acc2 = new Account();
				model.addAttribute("acc", acc2);
				
				return "admin/form/login2";
			}
			User user2 = list.get(vT);
			if(user2.getRule()>=2) {
				HttpSession session2 = request.getSession();
				session2.setAttribute("user3", user2);
				session = factory.getCurrentSession();
				
				String hql = "FROM User";
				Query query = session.createQuery(hql);
				List<User> list2 = query.list();
				model.addAttribute("users", list2);
				
				List<Employee> list21 = getEmployee();
				model.addAttribute("employees", list21);
			}else {
				Account acc2 = new Account();
				model.addAttribute("acc", acc2);
				
				return "admin/form/login2";
			}
			
		}
		
		
		
		return "redirect:/user/index.htm";
	}
	
	
	@RequestMapping("user3")
	public String user3(ModelMap model) {
		Account acc = new Account();
		model.addAttribute("acc", acc);
		List<Employee> list = getEmployee();
		model.addAttribute("employees", list);
		
		
		return "admin/form/login3";
	}
	
	@RequestMapping("user4")
	public String user4(ModelMap model,
						HttpServletRequest request,
						@Validated @ModelAttribute("acc")Account acc,
						BindingResult errors) {
		if(errors.hasErrors()) {
			return "redirect:/login/user3.htm";
		}else {
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			
			model.addAttribute("acc", acc);
			List<User> list = getUsers();
			model.addAttribute("users", list);
			
			String userName = acc.getUserName();
			String pass = acc.getPassWord();
			
			int kT=0;
			int dem=-1, vT=-1;
			String u2="", p2="";
			for(User u1 : list) {
				dem++;
				u2 = u1.getUserName();
				p2 = u1.getPassWord();
				
				if((userName.equals(u2)) && (pass.equals(p2))) {
					kT=1;
					vT = dem;
					break;
				}
			}
			if(kT == 1) {
				
			}else {
				
				return "redirect:/login/user3.htm";
			}
			User user2 = list.get(vT);
			if(user2.getRule()>=2) {
				HttpSession session2 = request.getSession();
				session2.setAttribute("user3", user2);
				session = factory.getCurrentSession();
				String hql = "FROM Employee";
				Query query = session.createQuery(hql);
				List<Employee> list2 = query.list();
				model.addAttribute("employees", list2);
			}else {
				return "redirect:/login/user3.htm";
			}
			
		}
		
		
		
		return "redirect:/employee/index.htm";
	}
	
	@RequestMapping("user5")
	public String user5(ModelMap model) {
		Account acc = new Account();
		model.addAttribute("acc", acc);
		
		return "admin/form/login4";
	}
	
	@RequestMapping("user6")
	public String user6(ModelMap model,
						HttpServletRequest request,
						@Validated @ModelAttribute("acc")Account acc,
						BindingResult errors) {
		if(errors.hasErrors()) {
			return "redirect:/login/user5.htm";
		}else {
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			
			model.addAttribute("acc", acc);
			List<User> list = getUsers();
			model.addAttribute("users", list);
			
			String userName = acc.getUserName();
			String pass = acc.getPassWord();
			
			int kT=0;
			int dem=-1, vT=-1;
			String u2="", p2="";
			for(User u1 : list) {
				dem++;
				u2 = u1.getUserName();
				p2 = u1.getPassWord();
				
				if((userName.equals(u2)) && (pass.equals(p2))) {
					kT=1;
					vT = dem;
					break;
				}
			}
			if(kT == 1) {
				
			}else {

				return "redirect:/login/user5.htm";
			}
			User user2 = list.get(vT);
			if(user2.getRule()>=1) {
				HttpSession session2 = request.getSession();
				session2.setAttribute("user2", user2);
				session = factory.getCurrentSession();
				String hql = "FROM Image";
				Query query = session.createQuery(hql);
				List<Image> list2 = query.list();
				model.addAttribute("images", list2);
			}else {
				return "redirect:/login/user5.htm";
			}
			
		}
		
		
		
		return "redirect:/image/index.htm";
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Product> getProducts(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers(){
		Session session = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployee(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		List<Employee> list = query.list();
		
		return list;
	}

}
