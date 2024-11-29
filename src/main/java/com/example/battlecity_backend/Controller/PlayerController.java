package com.example.battlecity_backend.Controller;

import com.example.battlecity_backend.model.Player;
import com.example.battlecity_backend.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/players")
    public ResponseEntity<Void> save(@RequestBody Player player){
        playerService.save(player);
        return ResponseEntity.status(201).build(); // Devuelve un HTTP 201 (Created) sin cuerpo
    }

    @GetMapping("/players")
    public List<Player> findAll(){
        return playerService.findAll();
    }

    @GetMapping("/player/{id}")
    public Player findById(@PathVariable String id){
        return playerService.findById(id).get();
    }

    @DeleteMapping("/player/{id}")
    public void deleteById(@PathVariable String id){
        playerService.deleteById(id);
    }

    @PutMapping("/players")
    public void update(@RequestBody Player player){
        playerService.save(player);
    }

}
