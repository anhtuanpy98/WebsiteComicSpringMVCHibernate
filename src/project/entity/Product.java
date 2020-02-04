package project.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="Container")
public class Product {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Detail")
	private String detail;
	
	@Column(name="Price")
	private float price;
	
	@ManyToOne
	@JoinColumn(name="Id_Image")
	private Image image;
	
	@Column(name="PriceNew")
	private float priceNew;
	
	
	@Column(name="Status")
	private int status;
	
	@Column(name="Amount")
	private int amount;
	
	
	@OneToMany(mappedBy="product", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy="product", fetch = FetchType.EAGER)
	private Collection<ImportDetail> importDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public float getPriceNew() {
		return priceNew;
	}

	public void setPriceNew(float priceNew) {
		this.priceNew = priceNew;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Collection<ImportDetail> getImportDetails() {
		return importDetails;
	}

	public void setImportDetails(Collection<ImportDetail> importDetails) {
		this.importDetails = importDetails;
	}

	
}
