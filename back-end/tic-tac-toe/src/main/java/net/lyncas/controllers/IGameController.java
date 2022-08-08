package net.lyncas.controllers;

import net.lyncas.dtos.ConnectToGameDTO;
import net.lyncas.dtos.CreateGameOrConnectToRandomGameDTO;
import net.lyncas.dtos.PlayDTO;
import net.lyncas.models.Game;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Alexandre Marinho de Souza Júnior on 08/08/2022
 */

@Path("/game")
public interface IGameController {


    @POST
    Game createGame(CreateGameOrConnectToRandomGameDTO createGameOrConnectToRandomGameDTO) throws Exception;


    @POST
    @Path("/connect")
    Game connect(ConnectToGameDTO connectToGameDTO) throws Exception;


    @POST
    @Path("/connect-to-random-game")
    Game connectToRandomGame(CreateGameOrConnectToRandomGameDTO createGameOrConnectToRandomGameDTO) throws Exception;

    @POST
    @Path("/play-a-move")
    Game play(PlayDTO playDTO) throws Exception;
}
