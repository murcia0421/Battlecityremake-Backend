package com.example.battlecity_backend.service;

import com.example.battlecity_backend.model.Player;
import com.example.battlecity_backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public void save(Player player){
        playerRepository.save(player);
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Optional<Player> findById(String id){
        return playerRepository.findById(id);
    }

    public void deleteById(String id){
        playerRepository.deleteById(id);
    }
}
