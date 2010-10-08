package com.jrmapp.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity  
@Table(name="USERINFO")  
public class User {  
      
    @Id  
    @Column(name="ID")  
    @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy="uuid")  
    private String id = null;  
      
    @Column(name="NAME")  
    private String name = null;  
      
    @ManyToMany  
    @JoinTable(  
        name="USERMESSAGE",  
        joinColumns={@JoinColumn(name="USERID")},  
        inverseJoinColumns={@JoinColumn(name="MESSAGEID")}  
    )  
    private Set<Message> messages = null;//Many to many  
      
    @OneToMany(mappedBy="user")  
    private Set<Address> addresses = null;//One to many  
      
  /*  @OneToOne(fetch=FetchType.LAZY, optional = false)  
    @PrimaryKeyJoinColumn  */
  //@OneToOne(mappedBy="userIdCard", fetch=FetchType.LAZY) 
    @OneToOne(mappedBy="user",optional=false)   
    private UserIdCard userIdCard = null;//One to one  
     
    /** 
     * @return the userIdCard 
     */  
    public UserIdCard getUserIdCard() {  
        return userIdCard;  
    }  
    /** 
     * @param userIdCard the userIdCard to set 
     */  
    public void setUserIdCard(UserIdCard userIdCard) {  
        this.userIdCard = userIdCard;  
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
     * @return the name 
     */  
    public String getName() {  
        return name;  
    }  
    /** 
     * @param name the name to set 
     */  
    public void setName(String name) {  
        this.name = name;  
    }  
    /** 
     * @return the messages 
     */  
    public Set<Message> getMessages() {  
        return messages;  
    }  
    /** 
     * @param messages the messages to set 
     */  
    public void setMessages(Set<Message> messages) {  
        this.messages = messages;  
    }  
    /** 
     * @return the addresses 
     */  
    public Set<Address> getAddresses() {  
        return addresses;  
    }  
    /** 
     * @param addresses the addresses to set 
     */  
    public void setAddresses(Set<Address> addresses) {  
        this.addresses = addresses;  
    }  
}  
