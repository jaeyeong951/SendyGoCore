package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryJpaRepository extends JpaRepository<History, Long> {
    List<History> getHistoryByUserID(String ID);
}
