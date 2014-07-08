package com.borayu.model.xml;

import java.io.Serializable;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class XmlUser implements Serializable {
	
    private int id;
    private String login;
    private String passwd;
    private String email2;
    private java.sql.Date lastlogin;
    private java.sql.Date created;
    private String status;

    public XmlUser(int id, String login, String passwd, String email2, java.sql.Date lastlogin, java.sql.Date created, String status) {
        this.id = id;
        this.login = login;
        this.passwd = passwd;
        this.email2 = email2;
        this.lastlogin = lastlogin;
        this.created = created;
        this.status = status;
    }

    public XmlUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public java.sql.Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(java.sql.Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public java.sql.Date getCreated() {
        return created;
    }

    public void setCreated(java.sql.Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	
    
    
}
