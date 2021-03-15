package hello.hellospring.repository.sendygo;

import hello.hellospring.domain.History;
import hello.hellospring.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationRepositoryImpl implements LocationRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Location> getLocation() {
        return jdbcTemplate.query("select * from location order by rand() limit 2", locationRowMapper());
    }

    @Override
    public History insertHistory(History history) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("History");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", history.getId());
        parameters.put("idx", history.getIdx());
        parameters.put("src", history.getSrc());
        parameters.put("dest", history.getDest());
        parameters.put("distance", history.getDistance());
        parameters.put("historyDate", history.getHistoryDate());
        parameters.put("historyTime", history.getHistoryTime());
        parameters.put("reward", history.getReward());
        parameters.put("time", history.getTime());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));

        return history;
    }

    @Override
    public List<History> getHistoryByUserId(String id) {
        return jdbcTemplate.query("select * from history where id = ?", historyRowMapper(), id);
    }


    private RowMapper<Location> locationRowMapper() {
        return (rs, rowNum) -> {
            Location loc = new Location();
            loc.setLid(rs.getLong("LID"));
            loc.setLocation(rs.getString("Location"));

            return loc;
        };
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) -> {
            History history = new History();
            history.setIdx(rs.getLong("idx"));
            history.setId(rs.getString("id"));
            history.setSrc(rs.getString("src"));
            history.setDest(rs.getString("dest"));
            history.setHistoryDate(rs.getString("historydate"));
            history.setHistoryTime(rs.getString("historytime"));
            history.setDistance(rs.getString("distance"));
            history.setReward(rs.getLong("reward"));
            history.setTime(rs.getString("time"));

            return history;
        };
    }
}
