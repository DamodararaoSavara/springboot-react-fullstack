package com.cricket.cricketers.service;

import com.cricket.cricketers.dto.PlayerDto;

import java.util.List;

public interface CricketService {
    public PlayerDto addNewPlayer(PlayerDto playerDto);
    public List<PlayerDto> getAllPlayers();
    public PlayerDto getPlayerById(Long id);
    public PlayerDto updatePlayer(Long id,PlayerDto playerDto);
    public String deletePlayer(Long id);
}
