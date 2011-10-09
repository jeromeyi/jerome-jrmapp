package com.jrmapp.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.jrmapp.action.base.BaseAction;
import com.jrmapp.vo.Name;
import com.opensymphony.xwork2.validator.annotations.ConditionalVisitorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.DateRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.UrlValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class AnnotationValidationAction extends BaseAction{
	private Name name;
    private String password;
    private String email;
    private int age;
    private Date birthday;
    private String homeUrl;
    private String question;
    private String answer;
    
    @Validations(
            stringLengthFields={@StringLengthFieldValidator(fieldName="password",minLength="8",maxLength="20",message="密码的长度必须大于8小于20个字符")},
            emails={@EmailValidator(fieldName="email",message="邮件字段的格式不对")},
            conversionErrorFields={@ConversionErrorFieldValidator(fieldName="age",message="年龄输入的值转换错误")},
            intRangeFields={@IntRangeFieldValidator(fieldName="age",min="0",max="150",message="年龄范围为0到150")},
            urls={@UrlValidator(fieldName="homeUrl",message="个人主页的格式不对")},
            dateRangeFields={@DateRangeFieldValidator(fieldName="birthday",min="1900-01-01",message="日期输入不真确")},
            visitorFields={@VisitorFieldValidator(fieldName="name",context="name",message="姓名错误：",appendPrefix=true)},
            fieldExpressions={@FieldExpressionValidator(expression="age>10",fieldName="age",message="年龄不大于10岁")},
            expressions={@ExpressionValidator(expression="age<10",message="年龄大于10岁")}, //不显示信息
            regexFields={@RegexFieldValidator(expression="1*",fieldName="question",message="问题不是全部1")},
            conditionalVisitorFields={@ConditionalVisitorFieldValidator(expression="age>10",context="name",fieldName="name",appendPrefix=true,message="ConditionVistor:")}
            )
   @Action( results = { @Result(name = "input", location = "/validation.html")})  
    public String execute() throws Exception {
        
        return super.execute();
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
