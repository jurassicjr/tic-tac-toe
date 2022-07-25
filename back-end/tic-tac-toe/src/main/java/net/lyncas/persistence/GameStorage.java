package net.lyncas.persistence;

import lombok.Getter;
import net.lyncas.models.Game;

import javax.ejb.Singleton;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alexandre Marinho de Souza Júnior on 25/07/2022
 */

@Singleton
@Getter
public class GameStorage {

    private ConcurrentHashMap<String, Game> games;

    private GameStorage(){
        games = new ConcurrentHashMap<>();
    }

    public void addGame(Game game){
        games.put(game.getId().toString(), game);
    }

    public void removeGame(UUID id){
        games.remove(id.toString());
    }





}
