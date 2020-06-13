import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

public class Monster extends Element
{
   public Monster(int x, int y, String s) {
      super(x, y, s);
   }

   @Override
   public void draw(TextGraphics graphics) {
      graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
      graphics.enableModifiers(SGR.BOLD, SGR.BORDERED, SGR.FRAKTUR);
      graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), getSymbol());
   }

   public Position move()
   {
      Random random = new Random();
      Position pos = new Position(0,0);
      int move = random.nextInt(4) + 1;

      switch (move) {
         case 1:
            pos = new Position(getPosition().getX()-1, getPosition().getY());
            break;

         case 2:
            pos = new Position(getPosition().getX()+1, getPosition().getY());
            break;

         case 3:
            pos = new Position(getPosition().getX(),getPosition().getY()-1);
            break;

         case 4:
            pos = new Position(getPosition().getX(),getPosition().getY()+1);
            break;
      }

      return pos;
   }
}
