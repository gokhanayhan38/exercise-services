package com.pluralsight.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement   // bunu koymamızın nedeni xmlde kullanırsak diye 
public class User {

	
	private String name;
	private String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
