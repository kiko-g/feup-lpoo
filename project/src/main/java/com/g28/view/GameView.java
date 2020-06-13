package com.g28.view;

import com.g28.model.*;
import com.g28.model.Menu;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameView {

    private Menu menu;
    private Arena arena;
    private Screen screen;
    private ArenaView arenaView;
    private GameOverMenu gameOverMenu;
    private int screenWidth, screenHeight;

    public GameView() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/open.txt"));
            screenWidth = reader.readLine().length();
            screenHeight = 5;
            while (reader.readLine() != null) screenHeight++;
            reader.close();

            File fontFile = new File("src/main/resources/square.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory().
                    setInitialTerminalSize(new TerminalSize(screenWidth, screenHeight));
            defaultTerminalFactory.setForceAWTOverSwing(true);
            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

            Terminal terminal = defaultTerminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.startScreen();
            this.screen.setCursorPosition(null);
            this.screen.doResizeIfNecessary();

            this.menu = new Menu(screenWidth, screenHeight);
            this.gameOverMenu = new GameOverMenu(screenWidth, screenHeight);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void drawState(State state) throws IOException {
        this.screen.clear();

        if(state.getClass().equals(Arena.class)) {
            arenaView.drawArena(this.screen.newTextGraphics(), (Arena) state);
        } else {
            state.draw(this.screen.newTextGraphics());
        }

        this.screen.refresh();
    }

    private void processKey(State state, KeyStroke key) {
        state.processKey(key);
    }

    public void run() throws IOException
    {
        KeyStroke key;
        this.arena = new Arena(screenWidth, screenHeight);

        while(true) {
            while (!menu.checkEnd()) {
                this.drawState(menu);
                key = this.screen.readInput();
                if (key.getKeyType() == KeyType.Enter && menu.getOption() == 0) {
                    menu.close();

                    this.arenaView = new ArenaView();
                    arena.start();

                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                drawState(arena);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    timer.schedule(task, new Date(), 200);

                    while (!arena.checkEnd() ) {
                        this.drawState(arena);

                        key = this.screen.readInput();

                        if (arena.getPacdots().isEmpty()) arena.nextLevel();

                        else if (arena.getPacdots().isEmpty() && arena.getLevel() == Arena.LevelType.OPEN) {
                            arena.close();
                        }

                        this.processKey(arena, key);
                    }

                    timer.cancel();
                    timer.purge();

                    gameOverMenu.setScore(arena.getPlayerScore());
                    this.drawState(gameOverMenu);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else if (key.getKeyType() == KeyType.Enter && menu.getOption() == 1) {
                    menu.close();

                    Highscore highscore = new Highscore(this.screenWidth, this.screenHeight);

                    while (!highscore.checkEnd() ) {
                        this.drawState(highscore);

                        key = this.screen.readInput();

                        highscore.processKey(key);
                    }
                } else if (key.getKeyType() == KeyType.Enter && menu.getOption() == 2) {
                    menu.close();
                    this.screen.close();
                    System.exit(0);
                }

                menu.processKey(key);
            }

            this.menu.start();
        }
    }
}