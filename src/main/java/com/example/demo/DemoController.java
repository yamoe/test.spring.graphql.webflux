package com.example.demo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Controller
public class DemoController {

    private final DataRepository repository;
    private final PostDao postDao;

    public DemoController(DataRepository dataRepository, PostDao postDao) {
        this.repository = dataRepository;
        this.postDao = postDao;
    }

    @QueryMapping
    public String greeting() {
        return this.repository.getBasic();
    }

    @QueryMapping
    public Mono<String> greetingMono() {
        return this.repository.getGreeting();
    }

    @QueryMapping
    public Flux<String> greetingsFlux() {
        return this.repository.getGreetings();
    }

    @SubscriptionMapping
    public Flux<String> greetings() {
        return this.repository.getGreetingsStream();
    }

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postDao.getRecentPosts(count, offset);
    }

    @MutationMapping
    public Post writePost(@Argument String title, @Argument String text, @Argument String category, @Argument String author) {
        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .title(title)
                .text(text)
                .category(category)
                .author(author)
                .build();

        postDao.savePost(post);

        return post;
    }

    @MutationMapping
    public Post writePostInput(@Argument PostInput postInput) {
        Post newPost = Post.builder()
                .id(UUID.randomUUID().toString())
                .title(postInput.getTitle())
                .text(postInput.getText())
                .category(postInput.getCategory())
                .author(postInput.getAuthor())
                .build();

        postDao.savePost(newPost);

        return newPost;
    }
}