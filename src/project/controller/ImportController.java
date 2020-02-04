package project.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.entity.Image;
import project.entity.ImportDetail;
import project.entity.Product;
import project.entity.User;

@Transactional
@Controller
@RequestMapping("/import/")
public class ImportController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		model.addAttribute("products", list);
		
		List<Image> list2 = getImage();
		model.addAttribute("images", list2);
		
		return "employee/product/index";
	}
	
//	@RequestMapping("index2")
//	public String index2(ModelMap model) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Product";
//		Query query = session.createQuery(hql);
//		List<Product> list = query.list();
//		model.addAttribute("products", list);
//		
//		List<Image> list2 = getImage();
//		model.addAttribute("images", list2);
//		
//		return "employee/product/index";
//	}
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("product", new Product());
		
		List<Image> list = getImage();
		model.addAttribute("images", list);
		
		return "employee/product/insert";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insert(ModelMap model,
						@ModelAttribute("product")Product product,
						HttpServletRequest request
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
				//session.close();
			}
			
			HttpSession ss1 = request.getSession();
			User user = (User) ss1.getAttribute("user2");
			User user2 = (User) ss1.getAttribute("user3");
			if(user != null) {
				ImportDetail imDe = new ImportDetail();
				imDe.setEmployeeId(user.getEmployee().getId());
				imDe.setProduct(product);
				imDe.setAmount(product.getAmount());
				Date date = new Date();
				imDe.setDate(date);
				imDe.setPrice(product.getPrice());
				Image img = (Image) session.get(Image.class, product.getImage().getId());
				imDe.setImage(img.getFileName());
				
				session =  factory.openSession();
				t = session.beginTransaction();
				
				try {
					session.save(imDe);
					t.commit();
					model.addAttribute("message", "Insert IMDE thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Insert IMDE thất bại!");
				}finally {
					//session.close();
				}
				
			}else if(user2 != null) {
				ImportDetail imDe = new ImportDetail();
				imDe.setEmployeeId(user.getEmployee().getId());
				imDe.setProduct(product);
				
				Date date = new Date();
				imDe.setDate(date);
				imDe.setPrice(product.getPrice());
				Image img = (Image) session.get(Image.class, product.getImage().getId());
				imDe.setImage(img.getFileName());
				
				session =  factory.openSession();
				t = session.beginTransaction();
				
				try {
					session.save(imDe);
					t.commit();
					model.addAttribute("message", "Insert IMDE thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Insert IMDE thất bại!");
				}finally {
					//session.close();
				}
			}
		

		return "employee/product/insert";
	}
	
	@RequestMapping(value="insert2", method = RequestMethod.GET)
	public String insert2(ModelMap model,
							@PathVariable("id")int id) {
		Session session = factory.openSession();
		Product product = (Product) session.get(Product.class, id);
		
		model.addAttribute("product2", product);
		
		List<Image> list = getImage();
		model.addAttribute("images", list);
		
		
		return "employee/product/update";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.POST)
	public String insert2(ModelMap model,
						@ModelAttribute("product2")Product product1,
						HttpServletRequest request
						) {

				Session session = factory.openSession();
				Product product = (Product) session.get(Product.class, product1.getId());
				Transaction t = session.beginTransaction();
				
				List<Image> list = getImage();
				model.addAttribute("images", list);
				
				try {
					
					product.setName(product1.getName());
					product.setDetail(product1.getDetail());
					product.setImage(product1.getImage());
					product.setPrice(product1.getPrice());
					product.setPriceNew(product1.getPriceNew());
					product.setStatus(product1.getStatus());
					
					
					session.update(product);
					t.commit();
					model.addAttribute("message", "Edit thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Edit thất bại!");
				}finally {
					//session.close();
				}
				
				
				HttpSession ss1 = request.getSession();
				User user = (User) ss1.getAttribute("user2");
				User user2 = (User) ss1.getAttribute("user3");
				if(user != null) {
					ImportDetail imDe = new ImportDetail();
					imDe.setEmployeeId(user.getEmployee().getId());
					imDe.setProduct(product);
					Date date = new Date();
					imDe.setDate(date);
					imDe.setPrice(product.getPrice());
					Image img = (Image) session.get(Image.class, product.getImage().getId());
					imDe.setImage(img.getFileName());
					
					
					
					session =  factory.openSession();
					t = session.beginTransaction();
					
					try {
						session.save(imDe);
						t.commit();
						model.addAttribute("message", "Insert IMDE thành công");
					}catch (Exception e) {
						t.rollback();
						model.addAttribute("message", "Insert IMDE thất bại!");
					}finally {
						//session.close();
					}
					
				}else if(user2 != null) {
					ImportDetail imDe = new ImportDetail();
					imDe.setEmployeeId(user.getEmployee().getId());
					imDe.setProduct(product);
					
					Date date = new Date();
					imDe.setDate(date);
					imDe.setPrice(product.getPrice());
					Image img = (Image) session.get(Image.class, product.getImage().getId());
					imDe.setImage(img.getFileName());
					
					
					session =  factory.openSession();
					t = session.beginTransaction();
					
					try {
						session.save(imDe);
						t.commit();
						model.addAttribute("message", "Insert IMDE thành công");
					}catch (Exception e) {
						t.rollback();
						model.addAttribute("message", "Insert IMDE thất bại!");
					}finally {
						//session.close();
					}
				}

		return "employee/product/update";
	}
	
	
	@RequestMapping(value="insert4", method = RequestMethod.POST)
	public String insert4(ModelMap model,
						@ModelAttribute("product2")Product product1,
						HttpServletRequest request
						) {

				Session session = factory.openSession();
				Product product = (Product) session.get(Product.class, product1.getId());
				Transaction t = session.beginTransaction();
				
				List<Image> list = getImage();
				model.addAttribute("images", list);
				
				int soLg = product.getAmount();
				product.setAmount(product1.getAmount());
				soLg = product.getAmount() - soLg;
				
				try {
					product.setAmount(product1.getAmount());
									
					session.update(product);
					t.commit();
					model.addAttribute("message", "Edit thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Edit thất bại!");
				}finally {
					//session.close();
				}
				
				HttpSession ss1 = request.getSession();
				User user = (User) ss1.getAttribute("user2");
				User user2 = (User) ss1.getAttribute("user3");
				if(user != null) {
					ImportDetail imDe = new ImportDetail();
					imDe.setEmployeeId(user.getEmployee().getId());
					imDe.setProduct(product);
					imDe.setAmount(soLg);
					Date date = new Date();
					imDe.setDate(date);
					imDe.setPrice(product.getPrice());
					Image img = (Image) session.get(Image.class, product.getImage().getId());
					imDe.setImage(img.getFileName());
					
					session =  factory.openSession();
					t = session.beginTransaction();
					
					try {
						session.save(imDe);
						t.commit();
						model.addAttribute("message", "Insert IMDE thành công");
					}catch (Exception e) {
						t.rollback();
						model.addAttribute("message", "Insert IMDE thất bại!");
					}finally {
						//session.close();
					}
					
				}else if(user2 != null) {
					ImportDetail imDe = new ImportDetail();
					imDe.setEmployeeId(user.getEmployee().getId());
					imDe.setProduct(product);
					imDe.setAmount(soLg);
					Date date = new Date();
					imDe.setDate(date);
					imDe.setPrice(product.getPrice());
					Image img = (Image) session.get(Image.class, product.getImage().getId());
					imDe.setImage(img.getFileName());
					
					session =  factory.openSession();
					t = session.beginTransaction();
					
					try {
						session.save(imDe);
						t.commit();
						model.addAttribute("message", "Insert IMDE thành công");
					}catch (Exception e) {
						t.rollback();
						model.addAttribute("message", "Insert IMDE thất bại!");
					}finally {
						//session.close();
					}
				}

		return "employee/product/update2";
	}
	
	
	@RequestMapping(value="insert4", method = RequestMethod.GET)
	public String insert3(ModelMap model,
							@PathVariable("id")int id) {
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class,id);
		
		model.addAttribute("product2", product);
		model.addAttribute("products", getProduct());
		
		List<Image> list = getImage();
		model.addAttribute("images", list);
		
		
		return "employee/product/update";
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

		return "redirect:/import/index.htm";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap model,
						@PathVariable("id")int id) {
		
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class,id);
		
		model.addAttribute("product2", product);
		model.addAttribute("products", getProduct());
		
		List<Image> list = getImage();
		model.addAttribute("images", list);
		
		return "employee/product/update";
	}
	
	@RequestMapping("edit2/{id}")
	public String edit2(ModelMap model,
						@PathVariable("id")int id) {
		
		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class,id);
		
		model.addAttribute("product2", product);
		model.addAttribute("products", getProduct());
		
		List<Image> list = getImage();
		model.addAttribute("images", list);
		
		return "employee/product/update2";
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProduct(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Image> getImage(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Image";
		Query query = session.createQuery(hql);
		List<Image> list = query.list();
		
		return list;
	}
}
