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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.entity.Employee;
import project.entity.Image;

@Transactional
@Controller
@RequestMapping("/employee/")
public class EmployeeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		
		List<Employee> list2 = getEmployee();
		model.addAttribute("employees", list2);
		
		return "admin/create/index";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("employee", new Employee());
		
		return "admin/create/insert";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insert(ModelMap model,
						@ModelAttribute("employee")Employee employee) {

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			try {
				session.save(employee);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công");
			}catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại");
			}finally {
				session.close();
			}
		
		
		
		return "admin/create/insert";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.GET)
	public String insert1(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.getCurrentSession();
		Employee emp1 = (Employee) session.get(Employee.class,id);
		
		model.addAttribute("employee2", emp1);
		model.addAttribute("employees", getEmployee());
		
		return "admin/create/update";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.POST)
	public String insert2(ModelMap model,
						@ModelAttribute("employee2")Employee employee1
						) {

				Session session = factory.openSession();
				int id = employee1.getId();
				Employee employee = (Employee) session.get(Employee.class, id);
				Transaction t = session.beginTransaction();
				
				model.addAttribute("employee2", employee);
				model.addAttribute("employees", getEmployee());
				
				try {
					employee.setName(employee1.getName());
					employee.setBirthDay(employee1.getBirthDay());
					employee.setAddRess(employee1.getAddRess());
					employee.setPhoneNumber(employee1.getPhoneNumber());
					
					session.update(employee);
					t.commit();
					model.addAttribute("message", "Edit thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Edit thất bại!");
				}finally {
					session.close();
				}
			

		return "admin/create/update";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.openSession();
		Employee employee = (Employee) session.get(Employee.class,id);
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(employee);
			
			t.commit();
			model.addAttribute("message", "Xóa thành công");
		}catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}finally {
			session.close();
		}

		return "redirect:/employee/index.htm";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class,id);
		
		model.addAttribute("employee2", employee);
		model.addAttribute("employees", getEmployee());
		
		return "admin/create/update";
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
