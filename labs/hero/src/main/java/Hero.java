import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element
{
   public Hero(int x, int y, String s) {
      super(x, y, s);
   }

   @Override
   public void draw(TextGraphics graphics) {
      graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
      graphics.enableModifiers(SGR.BOLD, SGR.BORDERED, SGR.FRAKTUR);
      graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), getSymbol());
   }

   public Position getMoveUp() { return new Position(getPosition().getX(), getPosition().getY()-1); }
   public Position getMoveDown() { return new Position(getPosition().getX(), getPosition().getY()+1); }
   public Position getMoveLeft() { return new Position(getPosition().getX()-1, getPosition().getY()); }
   public Position getMoveRight() { return new Position(getPosition().getX()+1, getPosition().getY()); }

}
