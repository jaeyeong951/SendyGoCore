package hello.hellospring.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;

    private String car;

    private Long credit;

    private Long accu_credit;

    private Long ranking;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Long getAccu_credit() {
        return accu_credit;
    }

    public void setAccu_credit(Long accu_credit) {
        this.accu_credit = accu_credit;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long rank) {
        this.ranking = rank;
    }
}
