package com.cricket.cricketers.service.impl;

import com.cricket.cricketers.dto.PlayerDto;
import com.cricket.cricketers.exception.PlayerNotFoundException;
import com.cricket.cricketers.mapper.Mapper;
import com.cricket.cricketers.model.Player;
import com.cricket.cricketers.repository.CricketRepository;
import com.cricket.cricketers.service.CricketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CricketServiceImpl implements CricketService {
    private CricketRepository cricketRepository;
    private Mapper mapper;

    @Override
    public PlayerDto addNewPlayer(PlayerDto playerDto) {
        Player player = Mapper.mapToPlayer(playerDto);
        Player save = cricketRepository.save(player);
        return Mapper.mapToPlayerDto(save);
    }
    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = cricketRepository.findAll();
        return players.stream().map(player -> Mapper.mapToPlayerDto(player)).collect(Collectors.toList());
    }
    @Override
    public PlayerDto getPlayerById(Long id) {
        Player player = cricketRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        return Mapper.mapToPlayerDto(player);
    }
    @Override
    public PlayerDto updatePlayer(Long id, PlayerDto playerDto) {
        Player player = cricketRepository.findById(id).orElseThrow(()->new PlayerNotFoundException("Player not found"));
        player.setPlayerName(playerDto.getPlayerName());
        player.setPlayerCountry(playerDto.getPlayerCountry());
        player.setPlayerRole(playerDto.getPlayerRole());
        Player updatedPlayer = cricketRepository.save(player);
        return Mapper.mapToPlayerDto(updatedPlayer);
    }
    @Override
    public String deletePlayer(Long id) {
        Player player = cricketRepository.findById(id).orElseThrow(()->new PlayerNotFoundException("Player not found"));
        cricketRepository.deleteById(id);
        return id+" Player deleted successfully";
    }
}
