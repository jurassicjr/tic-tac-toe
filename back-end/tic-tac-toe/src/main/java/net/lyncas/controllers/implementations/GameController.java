package net.lyncas.controllers.implementations;

import lombok.RequiredArgsConstructor;
import net.lyncas.controllers.IGameController;
import net.lyncas.dtos.ConnectToGameDTO;
import net.lyncas.dtos.CreateGameOrConnectToRandomGameDTO;
import net.lyncas.dtos.PlayDTO;
import net.lyncas.models.Game;
import net.lyncas.services.GameService;

/**
 * @author Alexandre Marinho de Souza Júnior on 08/08/2022
 */

@RequiredArgsConstructor
public class GameController implements IGameController {

    private final GameService gameService;


    @Override
    public Game createGame(CreateGameOrConnectToRandomGameDTO createGameOrConnectToRandomGameDTO) throws Exception {
        return gameService.createGame(createGameOrConnectToRandomGameDTO);
    }

    @Override
    public Game connect(ConnectToGameDTO connectToGameDTO) throws Exception {
        return gameService.connectToGame(connectToGameDTO.player(), connectToGameDTO.gameID());
    }

    @Override
    public Game connectToRandomGame(CreateGameOrConnectToRandomGameDTO createGameOrConnectToRandomGameDTO) throws Exception {
        return gameService.connectToRandomGame(createGameOrConnectToRandomGameDTO.player());
    }

    @Override
    public Game play(PlayDTO playDTO) throws Exception {
        return gameService.play(playDTO);
    }

}
