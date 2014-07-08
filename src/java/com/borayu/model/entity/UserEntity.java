/*
 * Projeto Borayu
 * RestFull Server  * 
 */

package com.borayu.model.entity;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

/**
 *
 * @author borayu02
 */
@Entity //@Searchable
public class UserEntity implements Serializable{
    
    @Id //@SearchableId
    private Long id;
    
    //@SearchableProperty
    private String login;
    
    private String passwd;
    
    private String email2;
    
    //@SearchableProperty(format="dd-MM-yyyy")
    //@Temporal(TemporalType.DATE)
    private Date lastlogin;
    
    //@SearchableProperty(format="dd-MM-yyyy")
    //@Temporal(TemporalType.DATE)
    private Date created;
    
    //@SearchableProperty
    private String status;

    public UserEntity(Long id, String login, String passwd, String email2, Date lastlogin, Date created, String status) {
        this.id = id;
        this.login = login;
        this.passwd = passwd;
        this.email2 = email2;
        this.lastlogin = lastlogin;
        this.created = created;
        this.status = status;
    }

    public UserEntity() {
    }
 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
              
    
}