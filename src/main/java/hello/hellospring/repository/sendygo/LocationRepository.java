package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.History;
import hello.hellospring.domain.Location;

import java.util.List;

public interface LocationRepository {
    List<Location> getLocation();
}
