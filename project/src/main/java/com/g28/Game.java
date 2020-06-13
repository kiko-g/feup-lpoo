package com.g28;
import com.g28.view.GameView;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        GameView view = new GameView();

        try {
            view.run();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
