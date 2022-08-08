package net.lyncas.dtos;

import net.lyncas.models.Player;

public record CreateGameOrConnectToRandomGameDTO(
        Player player,
        boolean isAIGame
) {
}
