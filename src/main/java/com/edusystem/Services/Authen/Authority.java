package com.edusystem.Services.Authen;
//package com.springpersonalproect.AssigmentSubmissionApp.Services.Authen;

//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import com.springpersonalproect.AssigmentSubmissionApp.Entities.User;
//import org.springframework.security.core.GrantedAuthority;
//
//@Entity
//public class Authority implements GrantedAuthority {
//	private static final long serialVersionUID = -1769531196535167074L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String authority;
//	
//	@ManyToOne(optional = false,fetch = FetchType.LAZY)
//	private User user;
//	
//	// constructor
//	public Authority() {}
//	
//	public Authority(String authority) {
//		this.authority = authority;
//	}
//	
//	// getter & setter
//	@Override
//	// define Role
//	public String getAuthority() {
//		return authority;
//	}
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}
//	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	} 
//}
