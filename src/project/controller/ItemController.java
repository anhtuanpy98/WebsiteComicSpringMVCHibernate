package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.bean.CodeAndId;
import project.entity.Customer;
import project.entity.Image;
import project.entity.Product;

@Transactional
@Controller
@RequestMapping("/list/")
public class ItemController {
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("{id}")
	public String item1(ModelMap model,
			@PathVariable("id")int id) {
		
		Session session = factory.openSession();
		Product product = (Product) session.get(Product.class,id);
		
		int id2 = product.getImage().getId();
		Image image = (Image) session.get(Image.class, id2);
		
		model.addAttribute("image", image);
		model.addAttribute("product3", product);
		
		List<Product> list = getProducts();
		model.addAttribute("products", list);
		
		
		return "customer/list/item";
	}
	
	@RequestMapping("search")
	public String search(@RequestParam("nameSearch")String name,
						ModelMap model) {
		Session session = factory.getCurrentSession();
		
		List<Product> list2 = getProducts();
		List<Product> list1 = new ArrayList<Product>();
		int kT1=0;
		for(Product pro1 : list2) {
			if(pro1.getName().equalsIgnoreCase(name)) {
				list1.add(pro1);
				kT1=1;
			}
		}
		if(kT1==0) {
			model.addAttribute("message", "Không tìm thấy truyện tương ứng");
			return "redirect:/container/index.htm";
		}else {
			model.addAttribute("products", list1);
		}
		
		
		return "customer/product/search";
	}

	
	@SuppressWarnings("unchecked")
	public List<Product> getProducts(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		
		return list;
	}
	

}
