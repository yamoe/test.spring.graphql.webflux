package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostDao {
    private List<Post> posts;

    @PostConstruct
    public void PostConstruct() {
        this.posts = new ArrayList<>();
        for (int postId = 0; postId < 100; ++postId) {
            Post post = new Post();
            post.setId("id " + postId);
            post.setTitle("title " +  postId);
            post.setText("text " + postId);
            post.setAuthor("author" + postId);
            posts.add(post);
        }
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return posts.stream().skip(offset).limit(count).collect(Collectors.toList());
    }

    public List<Post> getAuthorPosts(String author) {
        return posts.stream().filter(post -> author.equals(post.getAuthor())).collect(Collectors.toList());
    }

    public void savePost(Post post) {
        posts.add(0, post);
    }
}
