package com.example.demo.security;

import com.example.demo.security.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User_Data")


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="email", nullable = false)
    private String email;
    @Column (name = "password")
    private String password;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "enabled")
    private boolean enabled;

    @Column (name = "username")
    private String username;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<User> user;

    //For followers and following
    @ManyToMany
    private Set<User> followings;

    @ManyToMany(mappedBy = "followings")
    private Set<User> followers;


    public String gravatarURL()
    {
        return "http://gravatar.com/avatar/afd87b3415ef623a1a0337c2b2171949?s=60";
    }


    public User(){
        roles = new HashSet<>();
        user = new HashSet<>();
        followers = new HashSet<>();
        followings = new HashSet<>();
    }



    public User(String email, String password, String firstName, String lastName, boolean enabled, String username)
    {
        this.setEmail(email);
        this.setPassword(password);
//        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
        this.firstName = firstName;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder=
                new BCryptPasswordEncoder();
        this.password=passwordEncoder.encode(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        firstName = firstName;
//    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<User> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<User> followings) {
        this.followings = followings;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public void addFollower(User follower) {
        followers.add(follower);
        follower.followings.add(this);
    }

    public void removeFollower(User follower) {
        followers.remove(follower);
        follower.followings.remove(this);
    }

    public void addFollowing(User followed) {
        followings.add(followed);
        //followed.addFollower(this);
    }

    public void removeFollowing(User followed) {
        followings.remove(followed);
        //followed.removeFollower(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
               ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        if (id != user.id) return false;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + username.hashCode();
        return result;
    }
}

