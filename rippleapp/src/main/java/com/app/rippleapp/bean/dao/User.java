package com.app.rippleapp.bean.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Override
//generate toString  
	public String toString() {
		return String.format("Hello [%s %s (Id: %d)]", firstName, lastName, id);
	}

}