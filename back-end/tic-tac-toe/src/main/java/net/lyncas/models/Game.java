package net.lyncas.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.lyncas.enums.EnGameStatuses;
import net.lyncas.enums.TicTacToe;

import java.util.UUID;

/**
 * @author Alexandre Marinho de Souza Júnior on 25/07/2022
 */

@Data
@NoArgsConstructor
public class Game {

    private UUID id;
    private Player player1;
    private Player player2;
    private EnGameStatuses status;
    private int[][] board;
    private TicTacToe winner;
    private boolean isAIGame = false;
}
