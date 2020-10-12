package com.peytoncysewski.codefellowship.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByApplicationUserOrderByCreatedAtDesc(ApplicationUser applicationUser);
    public Post findById(Id id);
}
