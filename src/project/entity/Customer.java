package project.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Customers")
public class Customer {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="Birthday")
	private java.util.Date birthDay;
	
	@Column(name="Address")
	private String addRess;
	
	@Column(name="Tel")
	private String tel;
	
	@Column(name="Email")
	private String email;
	
	@OneToMany(mappedBy="customer", fetch = FetchType.EAGER)
	private Collection<Order2> order;

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

	public java.util.Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(java.util.Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddRess() {
		return addRess;
	}

	public void setAddRess(String addRess) {
		this.addRess = addRess;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Order2> getOrder() {
		return order;
	}

	public void setOrder(Collection<Order2> order) {
		this.order = order;
	}
	
	
	
	
	
}
