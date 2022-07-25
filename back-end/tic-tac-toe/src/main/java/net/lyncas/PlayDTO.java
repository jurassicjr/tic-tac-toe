package net.lyncas;

import net.lyncas.enums.TicTacToe;

public record PlayDTO(TicTacToe type, int coordinateX, int coordinateY, String gameId) {
}
