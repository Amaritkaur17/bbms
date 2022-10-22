package com.cdac.project.bbms.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User implements UserDetails {
    // UserDteails : customize the user details service,
    // which is in a nutshell what graghs your user and make sure,that the user actually exists,
    //that that you actually have a value user that you're trying to login with
    //And then of course that user details service also at some point
    // will take care of making sure that password
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message="username is required")
    @Column(unique=true)
    @Size(max = 20)
    private String username;

    @Email(message="email is needed")
    @NotBlank(message="Please enter your email id")
    @Size(max = 50)
    private String email;


    @NotBlank(message="password is required")
    @Size(max = 120)
    private String password;

    @Transient
    private String confirmPassword;
    private Date create_At;
    private Date update_At;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String email, String password, String confirmPassword, Date create_At, Date update_At, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.create_At = create_At;
        this.update_At = update_At;
        this.roles = roles;
    }

    public User(String userName, String email, String password) {
        this.username = userName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
            }

            @PreUpdate
            protected void onUpdate(){
        this.update_At = new Date();
            }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    //userdetails interface methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
       // by def :  return false;
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // by def :  return false;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // by def : return false;
        return true;
    }

    @Override
    public boolean isEnabled() {
        // by def :  return false;
        return true;
    }

}
