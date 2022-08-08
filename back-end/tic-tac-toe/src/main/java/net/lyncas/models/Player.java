package net.lyncas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lyncas.enums.TicTacToe;

/**
 * @author Alexandre Marinho de Souza Júnior on 25/07/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private String username;

    private TicTacToe ticTacToe;

}
