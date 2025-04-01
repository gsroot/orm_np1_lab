package com.example.orm_np1_lab;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final SlowUserRepository slowUserRepository;

    @GetMapping("")
    public String getUsersWithPosts() {
        List<User> users = userRepository.findAll(); // 여러 유저 조회

        for (User user : users) {
            System.out.println("User: " + user.getName());

            for (Post post : user.getPosts()) { // posts 로딩
                System.out.println("  Post: " + post.getTitle());
            }
        }

        return "OK";
    }

    @GetMapping("/slow")
    public String getSlowUsersWithPosts() {
        List<SlowUser> users = slowUserRepository.findAll(); // 여러 유저 조회

        for (SlowUser user : users) {
            System.out.println("User: " + user.getName());

            for (SlowPost post : user.getPosts()) { // posts 로딩
                System.out.println("  Post: " + post.getTitle());
            }
        }

        return "OK";
    }

    @GetMapping("/fetch-join")
    public String getUsersWithPostsFetchJoin() {
        List<User> users = userRepository.findAllWithPosts(); // 여러 유저 조회

        for (User user : users) {
            System.out.println("User: " + user.getName());

            for (Post post : user.getPosts()) { // posts 로딩
                System.out.println("  Post: " + post.getTitle());
            }
        }

        return "OK";
    }

    @GetMapping("/subs")
    public String getUsersWithSubs() {
        User user = userRepository.findById(1L).orElse(null);

        System.out.println("User: " + user.getName());

        for (User sub : user.getSubordinates()) { // posts 로딩
            System.out.println("  Sub: " + sub.getName());

            for (User sub2 : sub.getSubordinates()) { // posts 로딩
                System.out.println("    Sub2: " + sub2.getName());
            }
        }

        return "OK";
    }

    @GetMapping("/subs/slow")
    public String getSlowUsersWithSubs() {
        SlowUser user = slowUserRepository.findById(1L).orElse(null);

        System.out.println("User: " + user.getName());

        for (SlowUser sub : user.getSubordinates()) { // posts 로딩
            System.out.println("  Sub: " + sub.getName());

            for (SlowUser sub2 : sub.getSubordinates()) { // posts 로딩
                System.out.println("    Sub2: " + sub2.getName());
            }
        }

        return "OK";
    }

    @GetMapping("/subs/fetch-join")
    public String getUsersWithSubsFetchJoin() {
        List<User> users = userRepository.findAllWithSubsByDepth(1); // 여러 유저 조회

        for (User user : users) {
            System.out.println("User: " + user.getName());

            for (User sub : user.getSubordinates()) { // posts 로딩
                System.out.println("  Sub: " + sub.getName());

                for (User sub2 : sub.getSubordinates()) { // posts 로딩
                    System.out.println("    Sub2: " + sub2.getName());
                }
            }
        }

        return "OK";
    }
}
