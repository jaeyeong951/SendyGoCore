package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationJpaQueryRepository extends JpaRepository<Location, Long> {
    @Query(value = "select * from location order by rand() limit 2", nativeQuery = true)
    List<Location> getLocation();
}
