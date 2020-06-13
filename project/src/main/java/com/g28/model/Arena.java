package com.g28.model;

import com.g28.view.*;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Arena extends State {
    public enum LevelType { TINY, MAZE, OPEN }
    public enum MovementType { NONE, UP, DOWN, LEFT, RIGHT }

    private Player player;
    private List<Wall> walls;
    private List<Ghost> ghosts;
    private List<Portal> portals;
    private List<PacDot> pacdots;
    private List<PowerUp> powerups;

    private WallView wallView = new WallView();
    private GhostView ghostView = new GhostView();
    private PlayerView playerView = new PlayerView();
    private PacDotView pacDotView = new PacDotView();
    private PowerUpView powerUpView = new PowerUpView();

    private Timer timer;
    private TimerTask task, taskSpawn;
    private Position spawnPoint;
    private int spawnCounter = 0;
    private LevelType level;
    private MovementType move = MovementType.NONE;

    public Arena(int width, int height) {
        super(width, height);
        this.player = new Player();
        this.walls = new CopyOnWriteArrayList<>();
        this.ghosts = new CopyOnWriteArrayList<>();
        this.pacdots = new CopyOnWriteArrayList<>();
        this.portals = new CopyOnWriteArrayList<>();
        this.powerups = new CopyOnWriteArrayList<>();
        this.level = LevelType.MAZE;
        this.createMap();

        this.timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run()
            {
                switch(move) {
                    case UP:
                        movePacman(player.moveUp());
                        break;
                    case DOWN:
                        movePacman(player.moveDown());
                        break;
                    case LEFT:
                        movePacman(player.moveLeft());
                        break;
                    case RIGHT:
                        movePacman(player.moveRight());
                        break;
                    case NONE:
                        movePacman(null);
                }
            }
        };

        this.taskSpawn = new TimerTask() {
            @Override
            public void run() {
                spawnOneGhost();
            }
        };
    }

    public void start() {
        timer.scheduleAtFixedRate(task, new Date(), 200);
        timer.scheduleAtFixedRate(taskSpawn, new Date(), 5000);
    }

    private void spawnOneGhost() {
        ghosts.get(spawnCounter).setPosition(spawnPoint);
        spawnCounter++;
        if (spawnCounter >= ghosts.size())
            taskSpawn.cancel();
    }

    public void nextLevel() {
        if(level.equals(LevelType.MAZE)) level = LevelType.OPEN;
        else if(level.equals(LevelType.OPEN)) level = LevelType.TINY;

        walls.clear();
        ghosts.clear();
        portals.clear();
        powerups.clear();

        this.createMap();
    }

    private void createMap() {
        int i = 0;
        String line;

        try {
            Scanner scanner = new Scanner(new File("src/main/resources/"+level.toString().toLowerCase()+".txt"));
            while(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                for(int j = 0; j < line.length(); j++)
                {
                    switch(line.charAt(j))
                    {
                        case '#': //wall
                            walls.add(new Wall(j, i));
                            break;

                        case '0': //ghost
                            switch(ghosts.size()) {
                                case 0:
                                    ghosts.add(new Ghost(j, i, "#d5212e", new ChaseAgressive(), new FrightenedWandering()));
                                    spawnPoint = new Position(j, i);
                                    break;
                                case 1:
                                    ghosts.add(new Ghost(j, i, "#99ccff", new ChaseSentinel(), new FrightenedWandering()));
                                    break;
                                case 2:
                                    ghosts.add(new Ghost(j, i, "#ffc0cb", new ChaseRandom(), new FrightenedWandering()));
                                    break;
                                default:
                                    ghosts.add(new Ghost(j, i, "#ffe338", new ChasePredict(), new FrightenedWandering()));
                                    break;
                            }
                            break;
                        case '=': //portal
                            portals.add(new Portal(j, i));
                            break;
                        case 'X': //pacman himself
                            player.setPosition(new Position(j, i));
                            break;
                        case 'o': //powerup
                            powerups.add(new PowerUp(j, i));
                            break;
                        default: //pacdot
                            pacdots.add(new PacDot(j, i));
                            break;
                    }
                }
                i++;
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LevelType getLevel() {
        return level;
    }

    public List<PacDot> getPacdots() {
        return pacdots;
    }

    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();

        elements.addAll(walls);
        elements.addAll(portals);
        elements.addAll(pacdots);
        elements.addAll(powerups);
        elements.addAll(ghosts);
        elements.add(player);

        return elements;
    }

    private Element getCollidingElement(Position position, List<? extends Element> elements) {
        for (Element element : elements)
            if (element.getPosition().equals(position))
                return element;

        return null;
    }

    private void movePacman(Position position) {
        moveGhosts();
        checkCell(player.getPosition());

        if (position == null)
            return;

        if(canMove(position)) {
            player.setPosition(position);
            checkCell(position);
        }
    }

    private void moveGhosts() {
        List<Element> elements = new ArrayList<>();
        elements.addAll(walls);
        elements.addAll(ghosts);
        elements.addAll(portals);

        for (int i = 0; i < spawnCounter; i++) {
            if(player.getPoweredUp()) ghosts.get(i).enterFrightenedMode(player, elements);
            else ghosts.get(i).enterChaseMode(player, elements);
        }
    }

    private boolean canMove(Position pos) {
        if (pos.getX() < 0 || pos.getX() >= width || pos.getY() < 0 || pos.getY() >= height)
            return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(pos)) return false;

        return true;
    }

    private void checkCell(Position position) {
        Ghost ghost = (Ghost) getCollidingElement(position, ghosts);
        PacDot pacdot = (PacDot) getCollidingElement(position, pacdots);
        Portal portal = (Portal) getCollidingElement(position, portals);
        PowerUp powerup = (PowerUp) getCollidingElement(position, powerups);

        if(pacdot != null) {
            pacdots.remove(pacdot);
            player.incrementScore(10);
        }
        if(powerup != null) {
            player.incrementScore(50);
            powerups.remove(powerup);
            powerup.activate(player, powerups);
        }
        if(portal != null) {
            portal.warp(player, level);
        }
        if(ghost != null) {
            if (!player.decreaseHearts()) this.close();
            if (player.getPoweredUp()) ghost.returnToInitialPosition();
            else {
                for(Ghost g : ghosts)
                    g.returnToInitialPosition();
            }
        }
    }

    public void close() {
        timer.cancel();
        timer.purge();
        end = true;
        saveScore();
    }

    public boolean checkEnd() {
        return end;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case Escape:
                this.close();
                break;

            case ArrowUp:
                move = MovementType.UP;
                break;

            case ArrowDown:
                move = MovementType.DOWN;
                break;

            case ArrowLeft:
                move = MovementType.LEFT;
                break;

            case ArrowRight:
                move = MovementType.RIGHT;
                break;

            default:
                break;
        }
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public void saveScore() {
        try {
            File highscoresFile = new File("src/main/resources/highscore.txt");
            highscoresFile.createNewFile();
            FileWriter myWriter = new FileWriter(highscoresFile, true);
            myWriter.write(player.getScore() + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}