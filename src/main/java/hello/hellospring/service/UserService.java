package hello.hellospring.service;

import hello.hellospring.domain.User;
import hello.hellospring.repository.sendygo.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findUserById(String id) {
        return repository.findById(id);
    }

    public User create(String id) {
        User user = new User();

        Optional<User> u = repository.findById(id);
        if(u.isPresent()) {
            user = u.get();
            System.out.println("이미 존재하는 회원입니다.");
        }
        else {
            user.setId(id);
            user.setRank(1L);
            user.setCredit(0L);
            user.setAccu_credit(0L);
            user.setCar("car");
            repository.create(user);
        }

        return user;
    }

    public String updateUser(String id, Long credit) {
        Optional<User> u = repository.findById(id);
        if(u.isPresent()) {
            u.get().setCredit(credit);
            u.get().setAccu_credit(credit);
            repository.updateUser(u.get());
            List<User> ranking = repository.findAll();
            for(long i = 0L; i < ranking.size(); i++) {
                User user = repository.findById(ranking.get((int) i).getId()).get();
                System.out.println(user.getId() + " = " + user.getRank());
                user.setRank(i + 1);
                System.out.println(user.getId() + " = " + user.getRank());
                repository.updateUser(user);
            }
        }
        else {
            //잘못된 id, 없는 회원
        }

        return id;
    }

}
