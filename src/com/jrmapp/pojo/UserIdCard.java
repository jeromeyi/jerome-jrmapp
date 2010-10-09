package com.jrmapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity  
@Table(name="USERIDCARD")  
public class UserIdCard {  
      
    @Id  
    @Column(name="USERIDCARDID")  
    @GeneratedValue(generator="foreign")  
    @GenericGenerator(name="foreign", strategy="foreign", parameters={@Parameter(name="property",value="user")})  
    private long id;  
      
    @Column(name="SEQNUMBER")  
    private String seqNumber = null;  
      
    /*//@OneToOne(mappedBy="userIdCard", fetch=FetchType.LAZY) 
    @OneToOne(mappedBy="userIdCard",optional=false)   */
    @OneToOne(fetch=FetchType.LAZY, optional = false)  
    @PrimaryKeyJoinColumn  
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
     * @return the seqNumber 
     */  
    public String getSeqNumber() {  
        return seqNumber;  
    }  
    /** 
     * @param seqNumber the seqNumber to set 
     */  
    public void setSeqNumber(String seqNumber) {  
        this.seqNumber = seqNumber;  
    }  
}  
