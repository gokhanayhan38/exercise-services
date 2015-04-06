package com.pluralsight.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement   // bu onemli olmazsa messagebodywriter not found hatası verir.
//bu bize sunu diyor ben bu activity.java clasının gösterecegi elementim .
public class Activity {

	private String Id;
	private String description;
	private int duration;
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	@XmlElement(name="desc")  //bunu sadece browserda daha az yazı gozuksun diye yapıyoruz
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
