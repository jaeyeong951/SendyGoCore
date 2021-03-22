package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.Location;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LocationJpaRepository implements LocationRepository {

    private final EntityManager em;

    public LocationJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Location> getLocation() {
        List<Location> result = em.createQuery("select '*' from location order by rand() limit 2", Location.class).getResultList();
        return null;
    }
}
