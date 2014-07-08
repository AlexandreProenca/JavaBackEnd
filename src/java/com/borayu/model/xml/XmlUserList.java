package com.borayu.model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
public class XmlUserList {
	
	private List<XmlUser> users;
	

	public XmlUserList() {
		
	}
	
	public XmlUserList(List<XmlUser> users) {
		this.users = users;
	}
	
	@XmlElement(name="user")
	public List<XmlUser> getUsers() {
		return users;
	}
	
	public void setUsers(List<XmlUser> users) {
		this.users = users;
	}
	
	@XmlElement
	public int getCount() {
		return users.size();
	}

}
