package project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.smartcardio.Card;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.bean.Cart;
import project.bean.CodeAndId;
import project.bean.Customer2;
import project.bean.Email;
import project.bean.Mailer;
import project.entity.Customer;
import project.entity.Order2;
import project.entity.OrderDetail;
import project.entity.Product;

@Transactional
@Controller
@RequestMapping("/form/")
public class FormController {
	public static int code4=0;
	public static List<Product> list = new ArrayList<>();
	

	
	@Autowired
	Mailer mailer;
	
	
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("login")
	public String login() {
		
		return "customer/form/login";
	}
	
	@RequestMapping("create-account")
	public String create() {
		
		return "admin/form/create-account";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model, 
						@PathVariable("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Product product = (Product) session.get(Product.class, id);
		int so1 = -1;
		model.addAttribute("message", so1);
		try {
			int dem=-1;
			for(Product pro1: list) {
				dem++;
				if(pro1.getId()==id) {
					list.remove(dem);
				}
			}
		}catch (Exception e) {
			
		}
		Email email = new Email();
		model.addAttribute("email", email);
		
		float tongTien = (float) (0 * 1.0);
		for(Product pro3 : list) {
			tongTien += pro3.getPriceNew() * pro3.getAmount() * 1.0;
		}
		
		model.addAttribute("TongTien", tongTien);
		
		return "redirect:/form/order.htm";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap model, 
						@PathVariable("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Product product = (Product) session.get(Product.class, id);
		
		model.addAttribute("product", product);
		

		return "customer/form/edit";
	}
	
	@RequestMapping("edit2/{id}")
	public String edit22(ModelMap model, 
						@ModelAttribute("product")Product product1) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			int soLg = 0;
			int vT=-1, dem=-1;
			
			for(Product pro1 : list) {
				dem++;
				if(pro1.getId() == product1.getId()) {
					vT = dem;
					break;
				}else {
					
				}
			}
			if(vT== -1) {
				
			}else {
				int kT=0;
				List<Product> list2 = getProducts();
				for(Product pro2 : list2) {
					if(product1.getId() == pro2.getId()) {
						if(product1.getAmount() > pro2.getAmount()) {
							model.addAttribute("message", "Số lượng truyện còn lại không đủ để bán!");
							kT=1;
							break;
						}
					}
				}
				if(kT==0) {
					Product product = (Product) session.get(Product.class, product1.getId());
					product.setAmount(product1.getAmount());
					list.set(vT, product);
				}
			}
			

			//return "redirect:/form/order.htm";
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		float tongTien = (float) (0 * 1.0);
		for(Product pro3 : list) {
			tongTien += pro3.getPriceNew() * pro3.getAmount() * 1.0;
		}
		
		model.addAttribute("TongTien", tongTien);

		return "redirect:/form/order.htm";
	}
	
	
	
	@RequestMapping(value="order")
	public String order(ModelMap model
			) {

		model.addAttribute("products", list);
		Customer2 cus2 = new Customer2();
		model.addAttribute("customer", cus2);
		
		return "customer/form/order";
	}
	
	@RequestMapping("order/{id}")
	public String order(ModelMap model,
			@PathVariable("id")int id) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Product product = (Product) session.get(Product.class, id);
		if(list.size() != 0) {
			try {
				int soLg = 0;
				int vT=-1, dem=-1;
				
				for(Product pro1 : list) {
					dem++;
					if(pro1.getId() == id) {
						//model.addAttribute("message", "PRO1");
						vT = dem;
						break;
					}else {
						
					}
				}
				if(vT== -1) {
					//model.addAttribute("message", "PRODUCT");
					int soLg2 = 1;
					product.setAmount(soLg2);
					list.add(product);
				}else {
					for(Product pro1 : list) {
						if(pro1.getId() == id) {
							soLg = pro1.getAmount() + 1;
							product.setAmount(soLg);
							break;
						}
					}
					int kT7=0;
					List<Product> list2 = getProducts();
					for(Product pro2 : list2) {
						if(product.getId() == pro2.getId()) {
							if(product.getAmount() > pro2.getAmount()) {
								kT7=1;
								break;
							}
						}
					}
					if(kT7==0) {
						list.set(vT, product);
					}else {
						model.addAttribute("message", "Số lượng hàng không đủ!");
						return "redirect:/container/index.htm";
					}
					
				}
				
			}catch (Exception e) {
				

				return "redirect:/form/order.htm";
			}
			
		}else {
			int soLg2 = 1;
			product.setAmount(soLg2);
			list.add(product);
		}
		
		model.addAttribute("products", list);
		
		float tongTien = (float) (0 * 1.0);
		for(Product pro3 : list) {
			tongTien += pro3.getPriceNew() * pro3.getAmount() * 1.0;
		}
		
		model.addAttribute("TongTien", tongTien);
		
		return "redirect:/form/order.htm";
	}
	
	@RequestMapping("order-failed")
	public String orderFailed() {
		
		return "customer/form/OrderFail";
	}
	
	@RequestMapping("order-success")
	public String orderSuccess(ModelMap model ,
								@ModelAttribute("code") CodeAndId b1) {
		int code2 = 0, id1=-1;
		Product product = null;
		
		
		code2= b1.getMa();

		if(code2 != code4) {
			return "customer/form/OrderFail";
		}else {
			int kT2 = 0, kT3=0;
			
			
			for(Product pro1 : list) {
				kT3=0;
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();
				
				List<Product> list2 = getProducts();
				for(Product pro2 : list2) {
					if(pro2.getId() == pro1.getId()) {
						product = (Product) session.get(Product.class, pro1.getId());
						int soLg = pro2.getAmount() - pro1.getAmount();
						product.setAmount(soLg);
						try {
							session.update(product);
							t.commit();
							kT2 = 1;
							kT3 = 1;
							model.addAttribute("message", "Mua thành công");
							
							
							
						}catch (Exception e) {
							t.rollback();
							model.addAttribute("message", "Mua thất bại!");
						}finally {
							//session.close();
						}
					}
					if(kT3==1) {
						break;
					}
					
				}
			}
			
			
			if(kT2 == 1) {
				
				Customer2 cus2 = b1.getCustomer();
				
				Customer cus = new Customer();
				cus.setAddRess(cus2.getAddRess());
				cus.setBirthDay(cus2.getBirthDay());
				cus.setEmail(cus2.getEmail());
				cus.setName(cus2.getName());
				cus.setTel(cus2.getTel());
				
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();
				
				
				try {
					session.save(cus);
					t.commit();
					model.addAttribute("message", "Thêm mới C thành công!");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Thêm mới C thất bại!");
				}finally {
					//session.close();
				}
				
				// Thêm vào Class Order
				Session session2 = factory.openSession();
				Transaction t2 = session2.beginTransaction();
				List<Customer> list3 = getCustomers();
				Customer cus3 = new Customer();
				for(Customer li : list3) {
					cus3 = li;
				}
				Order2 order = new Order2();
				Date date = new Date();
				order.setCustomer(cus3);
				order.setDate(date);
				order.setStatus(1);
				
				try {
					session2.save(order);
					t2.commit();
					model.addAttribute("message", "Thêm mới O thành công!");
				}catch (Exception e) {
					t2.rollback();
					model.addAttribute("message", "Thêm mới O thất bại!");
				}finally {
					//session2.close();
				}
				
				
				
				List<Order2> list4 = getOrders();
				Order2 or2 = new Order2();
				for(Order2 li : list4) {
					or2 = li;
				}
				OrderDetail orDe = new OrderDetail();
				for(Product li : list) {
					orDe.setOrder_Id(or2.getId());
					orDe.setProduct(li);
					orDe.setQuantity(li.getAmount());
					
					Session session3 = factory.openSession();
					Transaction t3 = session3.beginTransaction();
					
					try {
						session3.save(orDe);
						t3.commit();
						model.addAttribute("message", "Thêm mới OD thành công!");
					}catch (Exception e) {
						t3.rollback();
						model.addAttribute("message", "Thêm mới OD thất bại!");
					}finally {
						//session3.close();
					}
				}
				model.addAttribute("message2", "Đã xóa!");
				list.clear();
				model.addAttribute("products", list);
				model.addAttribute("product", list);
				
				
			}
			
			
			
			model.addAttribute("products", list);
		}
		
		
		return "customer/form/OrderSuccess";
	}
	
	public static int createCode() {
		int code=1;
		
		Random rd = new Random();
		for(int i=0;i<4;i++) {
			int number = 1 + rd.nextInt(10); 
			code = code * 10 + number;
		}
		
		
		return code;
	}

	
	@RequestMapping(value="order-identify", method = RequestMethod.POST)
	public String orderIdentify2(ModelMap model	,
								@ModelAttribute("customer")Customer2 customer
								){
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		int kT1 = 0, kT2 = 0;
		List<Product> list2 = getProducts();
		
		for(Product pro1 : list) {
			for(Product pro2 : list2) {
				if(pro1.getId() == pro2.getId()) {
					if(pro1.getAmount() > pro2.getAmount()) {
						kT1 = 1;
						kT2 = 1;
						break;
					}
				}
			}
			if(kT2 == 1) {
				break;
			}
		}
		
		if(kT1 == 1) {
			return "customer/form/OrderFail";
		}else {
			int code2 = createCode();
			code4 = code2;

			String body = Integer.toString(code2);
			String subject = "Xác nhận email";
			String from = "anhtuan200298@gmail.com";
			
			String email = customer.getEmail();
			try {

				mailer.send(from, email, subject, body);
	
			}catch (Exception e) {

				return "redirect:/form/order.htm";
			}
		}
		model.addAttribute("products", list);
		CodeAndId code = new CodeAndId();
		code.setCustomer(customer);
		model.addAttribute("code", code);

		return "customer/form/IdentifyMail";
		
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
	public List<Customer> getCustomers(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Customer";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order2> getOrders(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Order2";
		Query query = session.createQuery(hql);
		List<Order2> list = query.list();
		
		return list;
	}
}
