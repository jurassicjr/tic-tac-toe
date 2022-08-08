package net.lyncas.services;

import lombok.RequiredArgsConstructor;
import net.lyncas.controllers.implementations.GameWebSocket;
import net.lyncas.dtos.CreateGameOrConnectToRandomGameDTO;
import net.lyncas.dtos.PlayDTO;
import net.lyncas.enums.EnCommonErrors;
import net.lyncas.enums.EnGameStatuses;
import net.lyncas.enums.TicTacToe;
import net.lyncas.models.Game;
import net.lyncas.models.Player;
import net.lyncas.persistence.GameStorage;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.UUID;

/**
 * @author Alexandre Marinho de Souza Júnior on 25/07/2022
 */

@RequestScoped
@RequiredArgsConstructor
public class GameService {

    private static GameStorage gameStorage;


    public Game createGame(CreateGameOrConnectToRandomGameDTO createGameOrConnectToRandomGameDTO) throws Exception {
        var player = createGameOrConnectToRandomGameDTO.player();

        if (player.getUsername().equals("a.i")) {
            throw new Exception(EnCommonErrors.INVALID_USERNAME.getValue());
        }
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setId(UUID.randomUUID());
        game.setStatus(EnGameStatuses.NEW);
        game.setPlayer1(player);

        if (createGameOrConnectToRandomGameDTO.isAIGame()) {
            game.setPlayer2(new Player("a.i", player.getTicTacToe() == TicTacToe.X
                    ? TicTacToe.O
                    : TicTacToe.X));
            game.setStatus(EnGameStatuses.IN_PROGRESS);
            game.setAIGame(true);
        }
        gameStorage.addGame(game);
        return game;
    }


    public Game connectToGame(Player player2, String gameId) throws Exception {
        if (gameStorage.getGames().containsKey(gameId)) {

            if (gameStorage.getGames().get(gameId).getPlayer2() != null) {
                Game game = gameStorage.getGames().get(gameId);
                game.setPlayer2(player2);
                game.setStatus(EnGameStatuses.IN_PROGRESS);
                gameStorage.addGame(game);
                return game;
            } else {
                throw new Exception(EnCommonErrors.PLAYER_2_ALREADY_ATTACHED.getValue());
            }

        } else {
            throw new Exception(EnCommonErrors.INVALID_GAME_ID.getValue());
        }
    }

    public Game connectToRandomGame(Player player2) throws Exception {
        List<Game> gamesWithoutPlayer2 = gameStorage.getGames().values().stream().filter(game -> game.getPlayer2() == null).toList();

        if (gamesWithoutPlayer2.isEmpty()) throw new Exception(EnCommonErrors.NO_GAMES_AVAILABLE.getValue());

        Game game = gamesWithoutPlayer2.get(0);
        game.setPlayer2(player2);
        game.setStatus(EnGameStatuses.IN_PROGRESS);
        gameStorage.addGame(game);

        return game;

    }

    public Game play(PlayDTO playDTO) throws Exception {
        if (!gameStorage.getGames().containsKey(playDTO.gameId())) {
            throw new Exception(EnCommonErrors.INVALID_GAME_ID.getValue());
        }

        Game game = gameStorage.getGames().get(playDTO.gameId());

        if (game.getStatus().equals(EnGameStatuses.FINISHED)) {
            throw new Exception(EnCommonErrors.GAME_FINISHED.getValue());
        }

        var board = game.getBoard();

        if (playDTO.coordinateX() < 0 || playDTO.coordinateX() > 2 || playDTO.coordinateY() < 0 || playDTO.coordinateY() > 2) {
            throw new Exception(EnCommonErrors.WRONG_COORDINATE.getValue());
        }

        int cellValue = board[playDTO.coordinateX()][playDTO.coordinateY()];
        if (cellValue != 1 && cellValue != 2) {
            board[playDTO.coordinateX()][playDTO.coordinateY()] = playDTO.type().getValue();
            if (checkWinner(board, playDTO.type())) {
                game.setStatus(EnGameStatuses.FINISHED);
                game.setWinner(playDTO.type());
            }

            if (!game.isAIGame()) {
                var session = GameWebSocket.sessions.get(game.getPlayer1().getTicTacToe().equals(playDTO.type())
                        ? game.getPlayer2().getUsername()
                        : game.getPlayer1().getUsername());
                session.getAsyncRemote().sendObject(game);
            }
            return game;
        } else {
            throw new Exception(EnCommonErrors.CELL_ALREADY_USED.getValue());
        }

    }


    public boolean checkWinner(int[][] board, TicTacToe ticTacToe) {
        int[] boardArray = new int[9];
        int boardArrayIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++, boardArrayIndex++) {
                boardArray[boardArrayIndex] = board[i][j];
            }
        }

        int[][] possibleWinGames = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        int positionSequence = 0;
        for (int i = 0; i < possibleWinGames.length; i++) {
            for (int j = 0; j < possibleWinGames[i].length; j++) {
                if (boardArray[possibleWinGames[i][j]] == ticTacToe.getValue()) {
                    positionSequence++;
                }
            }
            if (positionSequence == 3) return true;
            positionSequence = 0;
        }
        return false;
    }


}
