package net.lyncas.services;

import lombok.RequiredArgsConstructor;
import net.lyncas.PlayDTO;
import net.lyncas.enums.EnCommonErrors;
import net.lyncas.enums.EnGameStatuses;
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

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setId(UUID.randomUUID());
        game.setStatus(EnGameStatuses.NEW);
        game.setPlayer1(player);
        gameStorage.addGame(game);
        return game;
    }


    public Game connectToGame(Player player2, String gameId) throws Exception {
        if(gameStorage.getGames().containsKey(gameId)){

            if(gameStorage.getGames().get(gameId).getPlayer2() != null){
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

        if(gamesWithoutPlayer2.isEmpty()) throw new Exception(EnCommonErrors.NO_GAMES_AVAILABLE.getValue());

        Game game = gamesWithoutPlayer2.get(0);
        game.setPlayer2(player2);
        game.setStatus(EnGameStatuses.IN_PROGRESS);
        gameStorage.addGame(game);

        return game;

    }

    public Game play(PlayDTO playDTO) throws Exception {
       if(!gameStorage.getGames().containsKey(playDTO.gameId())){
           throw new Exception(EnCommonErrors.INVALID_GAME_ID.getValue());
       }

       Game game = gameStorage.getGames().get(playDTO.gameId());

       if(game.getStatus().equals(EnGameStatuses.FINISHED)){
           throw new Exception(EnCommonErrors.GAME_FINISHED.getValue());
       }

       var board = game.getBoard();

       if(playDTO.coordinateX() < 0 || playDTO.coordinateX() > 2 || playDTO.coordinateY() < 0 || playDTO.coordinateY() > 2){
           throw new Exception(EnCommonErrors.WRONG_COORDINATE.getValue());
       }

       int cellValue = board[playDTO.coordinateX()][playDTO.coordinateY()];
       if( cellValue != 1 && cellValue != 2){
           board[playDTO.coordinateX()][playDTO.coordinateY()] = playDTO.type().getValue();
           return game;
       } else {
           throw new Exception(EnCommonErrors.CELL_ALREADY_USED.getValue());
       }

    }



}
