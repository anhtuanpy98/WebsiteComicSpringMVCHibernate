package project.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.bean.Account;
import project.entity.Employee;
import project.entity.User;

@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String login(ModelMap model) {
		List<User> list = getUsers();
		model.addAttribute("users", list);
		
		return "admin/create/user/index";
	}
	
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("employees", getEmployee());
		
		return "admin/form/create-account";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insert(ModelMap model,
						@ModelAttribute("user")User us1) {

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			model.addAttribute("user", us1);
			model.addAttribute("employees", getEmployee());
			
			try {
				session.save(us1);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công");
			}catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại");
			}finally {
				session.close();
			}
		
		
		
		return "admin/form/create-account";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.GET)
	public String insert1(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.getCurrentSession();
		User us1 = (User) session.get(User.class,id);
		
		model.addAttribute("user2", us1);
		model.addAttribute("users", getUsers());
		model.addAttribute("employees", getEmployee());
		
		return "admin/create/user/update";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.POST)
	public String insert2(ModelMap model,
						@ModelAttribute("user2")User user1
						) {

				Session session = factory.openSession();
				int id = user1.getId();
				User user2 = (User) session.get(User.class, id);
				Transaction t = session.beginTransaction();
				
				model.addAttribute("user2", user2);
				model.addAttribute("users", getUsers());
				model.addAttribute("employees", getEmployee());
				
				try {
					user2.setName(user1.getName());
					user2.setUserName(user1.getUserName());
					user2.setPassWord(user1.getPassWord());
					user2.setRule(user1.getRule());
					user2.setStatus(user1.getStatus());
					user2.setEmployee(user1.getEmployee());
					
					session.update(user2);
					t.commit();
					model.addAttribute("message", "Edit thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Edit thất bại!");
				}finally {
					session.close();
				}
			

		return "admin/create/user/update";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.openSession();
		User user2 = (User) session.get(User.class, id);
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(user2);
			
			t.commit();
			model.addAttribute("message", "Xóa thành công");
		}catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}finally {
			session.close();
		}

		return "redirect:/user/index.htm";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.getCurrentSession();
		User user2 = (User) session.get(User.class, id);
		
		model.addAttribute("user2", user2);
		model.addAttribute("users", getUsers());
		model.addAttribute("employees", getEmployee());
		
		return "admin/create/user/update";
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployee(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		List<Employee> list = query.list();
		
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
}
