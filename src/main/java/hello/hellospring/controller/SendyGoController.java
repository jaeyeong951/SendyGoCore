package hello.hellospring.controller;

import hello.hellospring.domain.History;
import hello.hellospring.domain.Location;
import hello.hellospring.domain.User;
import hello.hellospring.service.LocationService;
import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SendyGoController {
    private final UserService userService;
    private final LocationService locationService;

    @Autowired
    public SendyGoController(UserService userService, LocationService locationService) {
        this.userService = userService;
        this.locationService = locationService;
    }

    @PostMapping("/sendygo/new")
    @ResponseBody
    public User create(@RequestParam String id) {
        return userService.create(id);
    }

    @PostMapping("sendygo/update")
    @ResponseBody
    public String update(@RequestParam String id,
                         @RequestParam Long credit) {
        return userService.updateUser(id, credit);
    }

    @GetMapping("sendygo/all")
    @ResponseBody
    public List<User> all() {
        return userService.findAll();
    }

    @GetMapping("sendygo/location")
    @ResponseBody
    public List<Location> location() {
        return locationService.getLocation();
    }

    @PostMapping("sendygo/insert_history")
    @ResponseBody
    public History insertHistory(@RequestParam String id,
                                 @RequestParam String time,
                                 @RequestParam String src,
                                 @RequestParam String dest,
                                 @RequestParam String distance,
                                 @RequestParam Long reward,
                                 @RequestParam String htime,
                                 @RequestParam String hdate) {
        return locationService.insertHistory(id, time, src, dest, distance, reward, htime, hdate);
    }

    @GetMapping("sendygo/get_history")
    @ResponseBody
    public List<History> getHistory(@RequestParam String id) {
        return locationService.getHistoryByUserID(id);
    }
}
