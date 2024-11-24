package com.example.battlecity_backend.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    private final Map<String, Integer> roomPlayers = new HashMap<>();

    public RoomController() {
        roomPlayers.put("room1", 0);
        roomPlayers.put("room2", 0);
        roomPlayers.put("room3", 0);
        roomPlayers.put("room4", 0);
    }

    @MessageMapping("/join")
    @SendTo("/topic/room-status")
    public String joinRoom(String roomName) {
        logger.info("Attempting to join room: {}", roomName);

        if (roomPlayers.containsKey(roomName)) {
            int players = roomPlayers.get(roomName);
            if (players < 4) {
                roomPlayers.put(roomName, players + 1);
                logger.info("Player joined {}. Current players: {}", roomName, roomPlayers.get(roomName));
                return "Player joined " + roomName + ". Current players: " + roomPlayers.get(roomName);
            } else {
                logger.warn("Room {} is full!", roomName);
                return "Room " + roomName + " is full!";
            }
        }
        logger.error("Room {} not found!", roomName);
        return "Room not found!";
    }

    @MessageMapping("/leave")
    @SendTo("/topic/room-status")
    public String leaveRoom(String roomName) {
        logger.info("Attempting to leave room: {}", roomName);

        if (roomPlayers.containsKey(roomName)) {
            int players = Math.max(0, roomPlayers.get(roomName) - 1);
            roomPlayers.put(roomName, players);
            logger.info("Player left {}. Current players: {}", roomName, roomPlayers.get(roomName));
            return "Player left " + roomName + ". Current players: " + roomPlayers.get(roomName);
        }
        logger.error("Room {} not found!", roomName);
        return "Room not found!";
    }
}