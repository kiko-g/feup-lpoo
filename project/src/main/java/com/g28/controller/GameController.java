package com.g28.controller;

import com.g28.model.Arena;
import com.g28.view.GameView;

import java.io.IOException;

public class GameController {
    private final ArenaController arenaController;

    private final Arena model;
    private final GameView view;

    public GameController(Arena model, GameView view) {
        this.view = view;
        this.model = model;

        this.arenaController = new ArenaController(model, view);
    }

    public void start() throws IOException {
        arenaController.start();
    }
}
