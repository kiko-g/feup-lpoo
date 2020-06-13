import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class Game
{
   private Arena arena;
   private Screen screen;
//   private TextGraphics graphics = screen.newTextGraphics();

   public Game() {
      try {
         Terminal terminal = new DefaultTerminalFactory().createTerminal();
         screen = new TerminalScreen(terminal);
         screen.setCursorPosition(null);   // we don't need a cursor
         screen.startScreen();             // screens must be started
         screen.doResizeIfNecessary();     // resize screen if necessary
      } catch (IOException e) { e.printStackTrace(); }

      arena = new Arena(80, 24);
   }


   private void draw() throws IOException {
      screen.clear();
      arena.draw(screen.newTextGraphics());
      screen.refresh();
   }

   private boolean validKey(KeyStroke key) throws IOException {
      KeyType x = key.getKeyType();
      switch(x) {
         case EOF:
            return false;

         case Character:
            if(key.getCharacter() == 'q') {
               System.out.println("\nGAME OVER!\n");
               screen.close();
            }
            return true;

         default:
            arena.processKey(key);
            return true;
      }
   }

   public void run() throws IOException
   {
      draw();
      KeyStroke key;

      do {
         draw();
         key = screen.readInput();

      } while(validKey(key));
   }
}
