package hello.hellospring.service;

import hello.hellospring.domain.History;
import hello.hellospring.domain.Location;
import hello.hellospring.domain.User;
import hello.hellospring.repository.sendygo.HistoryJpaRepository;
import hello.hellospring.repository.sendygo.LocationJpaQueryRepository;
import hello.hellospring.repository.sendygo.LocationJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LocationService {
    private final HistoryJpaRepository historyJpaRepository;
    private final LocationJpaQueryRepository locationJpaRepository;
    private final UserService userService;

    public LocationService(HistoryJpaRepository historyJpaRepository, LocationJpaQueryRepository locationJpaRepository, UserService userService) {
        this.locationJpaRepository = locationJpaRepository;
        this.historyJpaRepository = historyJpaRepository;
        this.userService = userService;
    }

    public List<Location> getLocation() {
        return locationJpaRepository.getLocation();
    }

    public History insertHistory(String id,
                                 String time,
                                 String src,
                                 String dest,
                                 String distance,
                                 Long reward,
                                 String htime,
                                 String hdate) {
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()) {
            History history = new History();
            history.setUserID(id);
            history.setHistoryTime(htime);
            history.setHistoryDate(hdate);
            history.setReward(reward);
            history.setSrc(src);
            history.setDest(dest);
            history.setTime(time);
            history.setDistance(distance);
            historyJpaRepository.save(history);

            userService.updateUser(id, reward + user.get().getCredit());

            return history;
        }
        else {
            System.out.println("잘못된 user ID 입니다.");
            return null;
        }
    }

    public List<History> getHistoryByUserID(String id) {
        return historyJpaRepository.getHistoryByUserID(id);
    }
}
