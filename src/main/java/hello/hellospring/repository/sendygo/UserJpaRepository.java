package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {
    List<User> findAllByOrderByCreditDesc();
    @Modifying
    @Query("update User set credit = :credit, accu_credit = :accu_credit where id = :id")
    Integer updateUserByID(@Param("credit") Long credit,
                        @Param("accu_credit") Long accu_credit,
                        @Param("id") String ID);
    @Modifying
    @Query("update User set ranking = :ranking where id = :id")
    Integer updateUserByID(@Param("ranking") Long ranking,
                        @Param("id") String ID);
}
