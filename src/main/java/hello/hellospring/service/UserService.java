package hello.hellospring.service;

import hello.hellospring.domain.User;
import hello.hellospring.repository.sendygo.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

//    private final UserRepository repository;
    private final UserJpaRepository userJpaRepository;

    public UserService(
//            UserRepository repository,
            UserJpaRepository userJpaRepository) {
//        this.repository = repository;
        this.userJpaRepository = userJpaRepository;
    }

    public List<User> findAll() {
        return userJpaRepository.findAllByOrderByCreditDesc();
        //return repository.findAll();
    }

    public Optional<User> findUserById(String id) {
        return userJpaRepository.findById(id);
        //return repository.findById(id);
    }

    public User create(String id) {
        User user = new User();

        Optional<User> u = userJpaRepository.findById(id);
        if(u.isPresent()) {
            user = u.get();
            System.out.println("이미 존재하는 회원입니다.");
        }
        else {
            user.setId(id);
            user.setRanking(1L);
            user.setCredit(0L);
            user.setAccu_credit(0L);
            user.setCar("car");
            userJpaRepository.save(user);
            //repository.create(user);
        }

        return user;
    }

    public String updateUser(String id, Long credit) {
        Optional<User> u = userJpaRepository.findById(id);
        if(u.isPresent()) {
            u.get().setCredit(credit);
            u.get().setAccu_credit(credit);
            userJpaRepository.updateUserByID(u.get().getCredit(), u.get().getAccu_credit(), id);
//            userJpaRepository.save(u.get());
//            repository.updateUser(u.get());
            List<User> ranking = userJpaRepository.findAll();
            for(long i = 0L; i < ranking.size(); i++) {
                User user = userJpaRepository.findById(ranking.get((int) i).getId()).get();
                System.out.println(user.getId() + " = " + user.getRanking());
                user.setRanking(i + 1);
                System.out.println(user.getId() + " = " + user.getRanking());
                userJpaRepository.updateUserByID(user.getRanking(), user.getId());
            }
        }
        else {
            //잘못된 id, 없는 회원
        }

        return id;
    }

}
