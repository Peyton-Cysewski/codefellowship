package com.peytoncysewski.codefellowship.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    String firstName;
    String lastName;
    String bio;
    Date dob;

    public ApplicationUser(){}
    public ApplicationUser(String username, String password, String firstName, String lastName, Date dob, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dob = dob;
    }

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "connections",
            joinColumns = { @JoinColumn(name = "follower") },
            inverseJoinColumns = { @JoinColumn(name = "followee") }
    )
     public Set<ApplicationUser> whoTheUserFollows = new HashSet<>();

    @ManyToMany(mappedBy = "whoTheUserFollows")
    public Set<ApplicationUser> whoIsFollowingTheUser = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}