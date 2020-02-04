package project.bean;

import java.util.ArrayList;
import java.util.List;

import project.entity.Customer;
import project.entity.Product;

public class CodeAndId {
	private Customer2 customer;
	
	private List<Product> list = new ArrayList<Product>();
	
	private int ma;

	public int getMa() {
		return ma;
	}
	public void setMa(int ma) {
		this.ma = ma;
	}

	
	
	public Customer2 getCustomer() {
		return customer;
	}
	public void setCustomer(Customer2 customer) {
		this.customer = customer;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}


	
}
