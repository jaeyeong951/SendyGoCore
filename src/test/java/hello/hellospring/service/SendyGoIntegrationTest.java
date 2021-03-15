package hello.hellospring.service;

import hello.hellospring.domain.User;
import hello.hellospring.repository.sendygo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendyGoIntegrationTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 회원가입() {
//        User user = new User();
//        user.setId("damon");
//        user.setCar("car");
//        user.setCredit(0L);
//        user.setAccu_credit(0L);
//        user.setRank(0L);
        String id = "damon2";
        User result = userService.create(id);
    }

    @Test
    void 전체회원출력() {
        System.out.println(userService.findAll().size());
    }
}
