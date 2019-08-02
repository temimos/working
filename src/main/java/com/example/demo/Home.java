package com.example.demo;
import com.example.demo.security.User;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String title;


    private String content;


    @DateTimeFormat(pattern = "dd MMM yyyy, hh:mm a")
    private LocalDateTime postedDateTime;

    private String pic;

    @ManyToOne
    private User user;


    public String gravatarURL()
    {
        return "http://gravatar.com/avatar/afd87b3415ef623a1a0337c2b2171949?s=60";
    }


    public Home() {
        pic = "";
        postedDateTime = LocalDateTime.now();
        user = new User();
    }
    public Home(String title, LocalDateTime postedDateTime, String content, String pic, User user) {
        this.title = title;
        this.postedDateTime = postedDateTime;
        this.content = content;
        this.pic = pic;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPostedDateTime() {
        return postedDateTime;
    }

    public void setPostedDateTime(LocalDateTime postedDateTime) {
        this.postedDateTime = postedDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
