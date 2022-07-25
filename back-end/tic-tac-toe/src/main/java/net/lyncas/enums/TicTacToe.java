package net.lyncas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Alexandre Marinho de Souza Júnior on 25/07/2022
 */

@AllArgsConstructor
@Getter
public enum TicTacToe {
    X(1), O(2);

    private Integer value;


}
