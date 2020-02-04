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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.entity.ImportDetail;
import project.entity.Product;
import project.entity.User;

@Transactional
@Controller
@RequestMapping("/container/")
public class ContainerController {
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		model.addAttribute("products", list);
		
		
		
		return "customer/product/index";
	}
	
	@RequestMapping("index2")
	public String index2(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		model.addAttribute("products", list);
		
		
		
		return "admin/product/index";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("product", new Product());
		
		return "admin/product/insert";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insert(ModelMap model,
						@ModelAttribute("product")Product product
						) {
		
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			try {
				session.save(product);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công!");
			}catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại!");
			}finally {
				session.close();
			}
		

		return "admin/product/insert";
	}
	
	@RequestMapping(value="insert2", method = RequestMethod.GET)
	public String insert2(ModelMap model) {
		model.addAttribute("product2", new Product());
		model.addAttribute("products", getProduct());
		
		
		return "admin/product/update";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.POST)
	public String insert2(ModelMap model,
						@ModelAttribute("product2")Product product1
						) {

				Session session = factory.openSession();
				int id = product1.getId();
				Product product = (Product) session.get(Product.class, id);
				Transaction t = session.beginTransaction();
				
				try {
					
					product.setName(product1.getName());
					product.setDetail(product1.getDetail());
					product.setImage(product1.getImage());
					product.setPrice(product1.getPrice());
					product.setPriceNew(product1.getPriceNew());
					product.setStatus(product1.getStatus());
					product.setAmount(product1.getAmount());
					
					
					session.update(product);
					t.commit();
					model.addAttribute("message", "Edit thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Edit thất bại!");
				}finally {
					session.close();
				}

		return "admin/product/update";
	}
	
	
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.openSession();
		Product product = (Product) session.get(Product.class,id);
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(product);
			
			t.commit();
			model.addAttribute("message", "Xóa thành công");
		}catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}finally {
			session.close();
		}

		return "redirect:/container/index2.htm";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap model,
						@PathVariable("id")int id) {
		
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class,id);
		
		model.addAttribute("product2", product);
		model.addAttribute("products", getProduct());
		
		return "admin/product/update";
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProduct(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		
		return list;
	}
	
	
	
}
