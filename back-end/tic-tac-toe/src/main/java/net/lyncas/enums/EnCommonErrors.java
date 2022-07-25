package net.lyncas.enums;

import io.netty.util.collection.ByteObjectHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Alexandre Marinho de Souza Júnior on 25/07/2022
 */

@AllArgsConstructor
@Getter
public enum EnCommonErrors {

    INVALID_GAME_ID("ID informado para o jogo não existe"),
    PLAYER_2_ALREADY_ATTACHED("Já há um jogador dois nessa partida"),
    NO_GAMES_AVAILABLE("Não há jogos disponíveis para você se conectar"),
    GAME_FINISHED("Esse jogo já finalizou"),
    WRONG_COORDINATE("Coordenada fora do grid do jogo"),
    CELL_ALREADY_USED("Posição de célula já jogada");
    private String value;
}
