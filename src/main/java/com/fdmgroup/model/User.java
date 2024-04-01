package com.fdmgroup.model;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * User class
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Entity
@Table(name = "`USER`") // Escape the table name
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "select u from User u"),
		@NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username = :username") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Autowired
	@Column(name = "FIRST_NAME", updatable = false)
	private String firstName;
	
	@Autowired
	@Column(name = "LAST_NAME", updatable = false)
	private String lastName;

	@Autowired
	@Column(name = "ADDRESS")
	private String address;

	public User() {
		super();
		setFirstName("defaultFirstName");
		setLastName("defaultLastName");
		setAddress("defaultAddress");
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 *  
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param address
	 */
	
	
	public User(String username, String password, String firstName, String lastName, String address) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	/**
	 * @param updatedUser
	 */
	public void updateDetails(User updatedUser) {
		setAddress(updatedUser.getAddress());
		setUsername(updatedUser.getUsername());
		setPassword(updatedUser.getPassword());

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, id, lastName, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

}

