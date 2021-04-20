package chess.repository;

import chess.dao.MovementDao;
import chess.entity.Movement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCMovementDao implements MovementDao {

    static final RowMapper<Movement> movementMapper = (rs, rowNum) -> new Movement(
        rs.getString("movement_id"),
        rs.getString("chess_id"),
        rs.getString("source_position"),
        rs.getString("target_position"),
        rs.getTimestamp("created_date").toLocalDateTime()
    );

    private final JdbcTemplate jdbcTemplate;

    public JDBCMovementDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final Movement movement) {
        jdbcTemplate.update("insert into movement value (?, ?, ?, ?, ?)",
            movement.getId(),
            movement.getChessId(),
            movement.getSourcePosition(),
            movement.getTargetPosition(),
            movement.getCreatedDate());
    }

    @Override
    public List<Movement> findByChessName(final String name) {
        return jdbcTemplate.query("select * from movement as mv" +
                " left outer join chess as ch using(chess_id)" +
                " where ch.name = ?" +
                " order by mv.created_date",
            movementMapper,
            name);
    }
}