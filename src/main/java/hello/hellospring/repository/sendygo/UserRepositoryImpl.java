package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;


import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User create(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("user");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getId());
        parameters.put("car", user.getCar());
        parameters.put("credit", user.getCredit());
        parameters.put("accu_credit", user.getAccu_credit());
        parameters.put("ranking", user.getRanking());
        jdbcInsert.execute(new MapSqlParameterSource(parameters));

        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        List<User> result = jdbcTemplate.query("select * from User where id = ?", userRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        List<User> result = jdbcTemplate.query("select * from User order by credit DESC", userRowMapper());
        return result;
    }

    @Override
    public int updateUser(User user) {
        int result = jdbcTemplate.update("update User set credit = ?, accu_credit = ?, rank = ? where id = ?", user.getCredit(), user.getAccu_credit(), user.getRanking(), user.getId());
        return result;
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setAccu_credit(rs.getLong("accu_credit"));
            user.setCar(rs.getString("car"));
            user.setCredit(rs.getLong("credit"));
            user.setRanking(rs.getLong("rank"));
            return user;
        };
    }
}
