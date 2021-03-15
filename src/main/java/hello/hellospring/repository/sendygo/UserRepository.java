package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User create(User user);
    Optional<User> findById(String id);
    List<User> findAll();
    int updateUser(User user);
}
