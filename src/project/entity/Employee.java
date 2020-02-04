package project.entity;

import java.util.Collection;
import java.util.Date;

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
@Table(name="Employee")
public class Employee {
		@Id
		@GeneratedValue
		@Column(name="Id")
		private int id;
		
		@Column(name="Name")
		private String name;
		
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern="MM/dd/yyyy")
		@Column(name="Birthday")
		private Date birthDay;
		
		@Column(name="Address")
		private String addRess;
		
		@Column(name="PhoneNumber")
		private String phoneNumber;
		
		@OneToMany(mappedBy="employee", fetch = FetchType.EAGER)
		private Collection<User> users;

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

		public Date getBirthDay() {
			return birthDay;
		}

		public void setBirthDay(Date birthDay) {
			this.birthDay = birthDay;
		}

		public String getAddRess() {
			return addRess;
		}

		public void setAddRess(String addRess) {
			this.addRess = addRess;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Collection<User> getUsers() {
			return users;
		}

		public void setUsers(Collection<User> users) {
			this.users = users;
		}
		
		
}
