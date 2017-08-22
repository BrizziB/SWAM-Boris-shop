package it.unifi.ing.swam.brizzi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Utenti")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userID;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="buyer", cascade={CascadeType.REMOVE})
	private List<Order> orders;
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public User(){}
	
	public void copyUser(User user){
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
 