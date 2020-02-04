package project.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import project.entity.Image;

@Transactional
@Controller
@RequestMapping("/image/")
public class ImageController {
	@Autowired
	ServletContext context;
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		
		List<Image> list2 = getImage();
		model.addAttribute("images", list2);
		
		return "employee/image/index";
	}
	
	@RequestMapping("index2")
	public String index2(ModelMap model) {
		Session session = factory.getCurrentSession();
		
		List<Image> list2 = getImage();
		model.addAttribute("images", list2);
		
		return "employee/image/index";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		
		Image image = new Image();
		model.addAttribute("image", image);
		model.addAttribute("images", getImage());
		
		return "employee/image/insert";
	}
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String insert(ModelMap model,
						@ModelAttribute("image")Image image,
						@RequestParam("photo")MultipartFile photo
						) {
		if(photo.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn file");
		}else {
			try {
				image.setFileData(photo.getBytes());
				
				String photoPath = context.getRealPath("/images/" + photo.getOriginalFilename());
				
				photo.transferTo(new File(photoPath));
				
				
				
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();
				
				try {
					image.setFileName(photo.getOriginalFilename());
					
					session.save(image);
					t.commit();
					model.addAttribute("message", "Thêm mới thành công!");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Thêm mới thất bại!");
				}finally {
					session.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		model.addAttribute("image", image);
		model.addAttribute("images", getImage());
		
		return "employee/image/insert";
	}
	
	@RequestMapping(value="insert2", method = RequestMethod.GET)
	public String insert2(ModelMap model) {
		
		Image image = new Image();
		model.addAttribute("image2", image);
		model.addAttribute("images", getImage());
		
		
		return "employee/image/update";
	}
	
	
	@RequestMapping(value="insert2", method = RequestMethod.POST)
	public String insert2(ModelMap model,
						@ModelAttribute("image2")Image image1
						) {

				Session session = factory.openSession();
				int id = image1.getId();
				Image image = (Image) session.get(Image.class,id);
				Transaction t = session.beginTransaction();
				
				try {
					
					image.setFileName(image1.getFileName());
					image.setDescription(image1.getDescription());
	
					session.update(image);
					t.commit();
					model.addAttribute("message", "Edit thành công");
				}catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Edit thất bại!");
				}finally {
					session.close();
				}
				model.addAttribute("images", getImage());

		return "employee/image/update";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(ModelMap model,
						@PathVariable("id")int id) {
		Session session = factory.openSession();
		Image image = (Image) session.get(Image.class,id);
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(image);
			
			t.commit();
			model.addAttribute("message", "Xóa thành công");
		}catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}finally {
			session.close();
		}

		return "redirect:/image/index.htm";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap model,
						@PathVariable("id")int id) {
		
		Session session = factory.getCurrentSession();
		Image image = (Image) session.get(Image.class,id);
		
		model.addAttribute("image2", image);
		model.addAttribute("images", getImage());
		
		return "employee/image/update";
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
