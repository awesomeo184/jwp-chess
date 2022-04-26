package chess.dao;

import chess.domain.board.ChessGame;
import chess.domain.piece.property.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MovementDAOTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void before() {
        ChessGameDAO chessGameDAO = new ChessGameDAO(jdbcTemplate);
        ChessGame chessGame = ChessGame.fromName("zero");
        String gameId = chessGameDAO.addGame(chessGame);

        org.assertj.core.api.Assertions.assertThat(gameId).isNotNull();
    }

    @Test
    @DisplayName("움직임 확인")
    void checkMovement() {
        ChessGame chessGame = ChessGame.createChessGame();
        MovementDAO movementDAO = new MovementDAO(jdbcTemplate);
        Movement movement = new Movement(
                Position.of("B2"),
                Position.of("B3")
        );
        movement.setGameId("1");
        movement.setTeam(Team.BLACK);
        chessGame.execute(movement);
        Assertions.assertDoesNotThrow(() -> movementDAO.addMoveCommand(movement));
    }
}
