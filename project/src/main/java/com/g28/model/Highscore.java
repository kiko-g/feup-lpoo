package com.g28.model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Highscore extends State{
    private boolean end;
    private List<Integer> scores, topScores;

    public Highscore(int width, int height) {
        super(width, height);

        scores = new ArrayList<>();
        topScores = new ArrayList<>();

        makeTopScores();
    }

    private void makeTopScores() {
        int score, max;
        String line;

        try {
            Scanner scanner = new Scanner(new File("src/main/resources/highscore.txt"));

            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                score = Integer.parseInt(line);
                scores.add(score);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(scores, Collections.reverseOrder());

        max = scores.size();
        if(max > 10) {
            max = 10;
        }

        for(int i = 0; i < max; i++) {
            topScores.add(scores.get(i));
        }
    }

    public void draw(TextGraphics graphics) {
        int i = 0;

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);

        String title = "Highscore!";
        graphics.putString(width/2 - title.length() / 2, 2, title);

        for(Integer score : topScores) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));

            graphics.putString(width / 2 - 5, 5 + 1 * i, String.valueOf(i + 1));

            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.putString(width / 2, 5 + 1 * i, score.toString());
            i++;
        }
    }

    public void close() {
        end = true;
    }

    public boolean checkEnd() {
        return end;
    }


    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case Escape:
                this.close();
                break;

            default:
                break;
        }
    }
}