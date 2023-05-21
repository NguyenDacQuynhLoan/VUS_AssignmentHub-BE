//package com.AssigmentSubmissionApp.Entities;
//package com.springpersonalproect.AssigmentSubmissionApp.Entities;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import javax.persistence.*;
//
//import com.springpersonalproect.AssigmentSubmissionApp.service.Authority;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Entity
////@Table(name = "Users")
////@AttributeOverride(name = "id", column = @Column(name = "author_id"))
//public class User implements UserDetails {//extends BaseEntity
//	private static final long serialVersionUID = 12693089128595222L;
//
//	//properties
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String username;
//	
//	private String password;
//	
//	private LocalDate assignedDate;
//
//	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Assignment> assignments = new ArrayList<>();
//
//	//getter & setter
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	 
//	@Override //user account
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	
//	public LocalDate getAssignedDate() {
//		return assignedDate;
//	}
//	public void setAssignedDate(LocalDate assignedDate) {
//		this.assignedDate = assignedDate;
//	}
//	
//	public List<Assignment> getAssignments() {
//		return assignments;
//	}
//	public void setAssignments(List<Assignment> assignments) {
//		this.assignments = assignments;
//	}
//	
//	@Override
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	// default  constructor
//	public User() {
//	}
//
//	//constructor
//	public User(String username, LocalDate assignedDate, List<Assignment> assignments) {
//		this.username = username;
//		this.assignedDate = assignedDate;
//		this.assignments = assignments;
//	}
//	
//	/// Security configuration
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> roles = new ArrayList<>();
//		roles.add(new Authority("ROLE_STUDENT"));
//		return roles;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//}
