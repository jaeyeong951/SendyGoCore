package hello.hellospring;

import hello.hellospring.repository.sendygo.LocationRepository;
import hello.hellospring.repository.sendygo.LocationRepositoryImpl;
import hello.hellospring.repository.sendygo.UserRepository;
import hello.hellospring.repository.sendygo.UserRepositoryImpl;
import hello.hellospring.service.LocationService;
import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//컴포넌트 스캔을 사용하지 않고 스프링 빈을 직접 등록하는 방법
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    private final MemberRepository memberRepository;

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository);
//    }
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    LocationService locationService() {
        return new LocationService(locationRepository(), userService());
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//
//    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(dataSource);
    }

    @Bean
    public LocationRepository locationRepository() {
        return new LocationRepositoryImpl(dataSource);
    }

    /*
    스프링을 왜 쓰는가? 스프링이 뭐가 좋은가?
    다형성을 활용한(인터페이스를 활용하여 구현체만 바꿔끼우는 등) 객체지향적 설계를 개발하기 굉장히 편리하도록 스프링 프레임워크(컨테이너)가 지원해준다.
    그 예시가 위와 같은 DI(의존성주입) 지원.
     */
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
