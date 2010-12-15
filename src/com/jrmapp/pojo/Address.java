package com.jrmapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity  
@Table(name="ADDRESS")
//@Proxy (lazy = false )
public class Address implements Serializable{  
      
    @Id  
    @Column(name="ID")  
/*    @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy="uuid")  */
    	@GeneratedValue(generator = "addressGenerator")   
@GenericGenerator(name = "addressGenerator", strategy = "sequence",    
        parameters = { @Parameter(name = "sequence", value = "seq_address") }) 
    private long id ;  
      
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
    public long getId() {  
        return id;  
    }  
    /** 
     * @param id the id to set 
     */  
    public void setId(long id) {  
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
