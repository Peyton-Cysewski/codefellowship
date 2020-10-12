package com.peytoncysewski.codefellowship.models.user;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String body;
    Long createdAt;

    @ManyToOne
    public ApplicationUser applicationUser;

    public Post(){}

    public Post(ApplicationUser user, String body) {
        this.applicationUser = user;
        this.body = body;
        this.createdAt = new Date().getTime();
    }

    public long getId() { return id; }

    public String getBody() { return body; }

    public Long getCreatedAt() { return createdAt; }

    public ApplicationUser getApplicationUser() { return applicationUser; }

    public String toString() {return body; }
}
