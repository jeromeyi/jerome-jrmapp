package com.jrmapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity  
@Table(name="ADDRESS")  
public class Address {  
      
    @Id  
    @Column(name="ID")  
    @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy="uuid")  
    private String id = null;  
      
    @Column(name="ADDRESS")  
    private String address = null;  
      
    @ManyToOne(fetch=FetchType.LAZY)  
    @JoinColumn(name="USERID")  
    private User user = null;  
      
    /** 
     * @return the user 
     */  
    public User getUser() {  
        return user;  
    }  
    /** 
     * @param user the user to set 
     */  
    public void setUser(User user) {  
        this.user = user;  
    }  
    /** 
     * @return the id 
     */  
    public String getId() {  
        return id;  
    }  
    /** 
     * @param id the id to set 
     */  
    public void setId(String id) {  
        this.id = id;  
    }  
    /** 
     * @return the address 
     */  
    public String getAddress() {  
        return address;  
    }  
    /** 
     * @param address the address to set 
     */  
    public void setAddress(String address) {  
        this.address = address;  
    }  
}  
