package com.g28.model;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;

public class Menu extends State {
    private int option = -1;
    private boolean end;
    private java.util.List<String> options;

    public Menu(int width, int height) {
        super(width, height);
        this.options = new ArrayList<>();

        this.options.add("Play");
        this.options.add("Highscores");
        this.options.add("Exit");
    }

    public int getOption() {
        return option;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);

        String title = "PACMAN";
        graphics.putString(width/2 - title.length() / 2, 2, title);

        for(String op : options) {
            int id = options.indexOf(op);
            if(option == id) {
                graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
                graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            } else {
                graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            }

            graphics.putString(width / 2 - op.length() / 2, 5 + 2 * id, op);
        }
    }

    public void start() {
        end = false;
    }

    public void close() {
        end = true;
    }

    public boolean checkEnd() {
        return end;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                if (option > 0) option--;
                break;

            case ArrowDown:
                if (option < options.size()-1) option++;
                break;

            case Enter:
                close();
                break;

            default:
                break;
        }
    }
}
