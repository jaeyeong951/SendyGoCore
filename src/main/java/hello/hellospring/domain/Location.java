package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Id
    private Long lid;
    private String location;

}
