package com.cricket.cricketers.mapper;

import com.cricket.cricketers.dto.PlayerDto;
import com.cricket.cricketers.model.Player;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static PlayerDto mapToPlayerDto(Player player){
       return new PlayerDto(
                player.getId(),
                player.getPlayerName(),
                player.getPlayerCountry(),
                player.getPlayerRole()
        );
    }
    public static Player mapToPlayer(PlayerDto playerDto){
        return new Player(
                playerDto.getId(),
                playerDto.getPlayerName(),
                playerDto.getPlayerCountry(),
                playerDto.getPlayerRole()
        );
    }
}
