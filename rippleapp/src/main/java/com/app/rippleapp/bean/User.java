package com.app.rippleapp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "users")
@Entity
public class User {
	@NotNull
    @Size(max = 100)
    @Column(name = "first_Name")
	public String firstName;
	@NotNull
    @Size(max = 100)
    @Column(name = "last_Name")
	public String lastName;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;

	public User() {
		super();
	}

//constructor of User Bean  
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
//generate toString  
	public String toString() {
		return String.format("Hello [%s %s (Id: %d)]", firstName, lastName, id);
	}
}