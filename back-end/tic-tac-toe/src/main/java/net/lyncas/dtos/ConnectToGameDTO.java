package net.lyncas.dtos;

import net.lyncas.models.Player;

import java.util.UUID;

public record ConnectToGameDTO(
        Player player,
        String gameID
) {
}
