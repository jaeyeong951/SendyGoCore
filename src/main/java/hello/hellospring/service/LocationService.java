package hello.hellospring.service;

import hello.hellospring.domain.History;
import hello.hellospring.domain.Location;
import hello.hellospring.domain.User;
import hello.hellospring.repository.sendygo.LocationRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class LocationService {
    private final LocationRepository locationRepository;
    private final UserService userService;

    public LocationService(LocationRepository locationRepository, UserService userService) {
        this.locationRepository = locationRepository;
        this.userService = userService;
    }

    public List<Location> getLocation() {
        return locationRepository.getLocation();
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
            history.setId(id);
            history.setHistoryTime(htime);
            history.setHistoryDate(hdate);
            history.setReward(reward);
            history.setSrc(src);
            history.setDest(dest);
            history.setTime(time);
            history.setDistance(distance);
            locationRepository.insertHistory(history);

            userService.updateUser(id, reward + user.get().getCredit());

            return history;
        }
        else {
            System.out.println("잘못된 user ID 입니다.");
            return null;
        }
    }

    public List<History> getHistoryByUserID(String id) {
        return locationRepository.getHistoryByUserId(id);
    }
}
