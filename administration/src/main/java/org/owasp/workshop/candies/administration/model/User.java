package org.owasp.workshop.candies.administration.model;

import org.hibernate.annotations.Type;
import org.owasp.workshop.candies.administration.dtos.StudentUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "security_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username")
    private String username;   

	@Column(name = "password")
    private String password;   

	@Column(name = "email")
    private String email;
	
	@Column(name = "firstname")
    private String firstName;
	
	@Column(name = "surname")
    private String surname;

	@Column(name = "userrole")
	private String userRole;

	@Column(name ="active")
	private boolean active;

	@Column(name ="login_attempts")
	private short loginAttempts;

	@Column(name ="last_login")
	private Date lastLogin;

	public User(){
		
	}
	
	public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;       
        this.password = user.password;
        this.firstName = user.firstName;
        this.surname = user.surname;
        this.active=user.active;        
	}

	public User(String username, String password, String email, String firstName, String surname, String userRole) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.surname = surname;
		this.userRole = userRole;
		this.active= true;
		this.loginAttempts=0;
	}

	public User(StudentUser studentUser) {
		this.username = studentUser.getUsername();
		this.email = studentUser.getEmail();
		this.password = studentUser.getPassword();
		this.firstName = studentUser.getFirstName();
		this.surname = studentUser.getSurname();
		this.userRole=UserRoleValue.STUDENT_USER_ROLE;
		this.active= true;
		this.loginAttempts=0;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long userid) {
		this.id = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public short getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(short loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
