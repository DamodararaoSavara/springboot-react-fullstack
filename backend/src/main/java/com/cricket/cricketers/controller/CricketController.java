package com.cricket.cricketers.controller;

import com.cricket.cricketers.dto.PlayerDto;
import com.cricket.cricketers.service.impl.CricketServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.List;

@RestController
@RequestMapping("api/player")
@CrossOrigin(origins = "http://localhost:5174")
@AllArgsConstructor
public class CricketController {
    private CricketServiceImpl cricketService;
    @PostMapping
    public PlayerDto addNewPlayer(@RequestBody PlayerDto playerDto){
       return cricketService.addNewPlayer(playerDto);
    }
    @GetMapping
    public List<PlayerDto> getAllPlayers(){
      return  cricketService.getAllPlayers();
    }
    @GetMapping("/{id}")
    public PlayerDto getPlayerById(@PathVariable Long id) {
        return cricketService.getPlayerById(id);
    }
    @PutMapping("/{id}")
    public PlayerDto updatePlayer(@PathVariable Long id,@RequestBody PlayerDto playerDto){
       playerDto.setId(id);
       return cricketService.updatePlayer(playerDto.getId(),playerDto);
    }
    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id){
        cricketService.deletePlayer(id);
        return "Player deleted successfully";
    }
}
