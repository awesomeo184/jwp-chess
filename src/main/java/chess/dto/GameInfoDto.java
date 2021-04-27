package chess.dto;

import chess.domain.ChessGame;
import chess.domain.board.Team;

import java.util.List;

public class GameInfoDto {
    private final SquaresDto squares;
    private final Team turn;
    private final ScoresDto scores;
    private final Team winner;

    public GameInfoDto(ChessGame chessGame) {
        this.squares = new SquaresDto(chessGame.board());
        this.turn = chessGame.turn();
        this.scores = new ScoresDto(new PointDto(chessGame.calculatePoint()));
        this.winner = chessGame.winner();
    }

    public List<SquareDto> squares() {
        return squares.squares();
    }

    public Team turn() {
        return turn;
    }

    public List<ScoreDto> scores() {
        return scores.scores();
    }

    public Team winner() {
        return winner;
    }
}