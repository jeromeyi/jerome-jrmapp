package com.jrmapp.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity  
@Table(name="MESSAGE")  
public class Message {  
      
    @Id  
    @Column(name="ID")  
   /* @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy="uuid")  */
        	@GeneratedValue(generator = "messageGenerator")   
@GenericGenerator(name = "messageGenerator", strategy = "sequence",    
        parameters = { @Parameter(name = "sequence", value = "seq_message") })
    private long id;  
      
    @Column(name="CONTENT")  
    private String content = null;  
      
    @ManyToMany(mappedBy="messages")  
    private Set<User> users = null;  
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
     * @return the content 
     */  
    public String getContent() {  
        return content;  
    }  
    /** 
     * @param content the content to set 
     */  
    public void setContent(String content) {  
        this.content = content;  
    }  
    /** 
     * @return the users 
     */  
    public Set<User> getUsers() {  
        return users;  
    }  
    /** 
     * @param users the users to set 
     */  
    public void setUsers(Set<User> users) {  
        this.users = users;  
    }  
}  
