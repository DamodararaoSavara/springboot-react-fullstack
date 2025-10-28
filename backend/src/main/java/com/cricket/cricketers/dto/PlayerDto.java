package com.cricket.cricketers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private Long id;
    private String playerName;
    private String playerCountry;
    private String playerRole;
}
