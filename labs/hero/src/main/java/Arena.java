import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Arena
{
   private int width;
   private int height;
   private Hero hero;
   private List<Wall> walls;
   private List<Coin> coins;
   private List<Monster> monsters;


   public Arena(int w, int h) {
      this.width = w;
      this.height = h;
      this.hero = new Hero(20, 10, "X");
      this.walls = createWalls();
      this.coins = createCoins();
      this.monsters = createMonsters();

      if(width < 6) width = 6;   if(width > 80) width = 80;
      if(height < 6) height = 6; if(height > 24) height = 24;
   }


   public void draw(TextGraphics graphics) {
      graphics.setBackgroundColor(TextColor.Factory.fromString("#001A34"));
      graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
      for(Wall wall : walls) wall.draw(graphics);
      for(Coin coin : coins) coin.draw(graphics);
      for(Monster monster : monsters) monster.draw(graphics);
      hero.draw(graphics);
      retrieveCoins();
      moveMonsters();
   }


   private List<Wall> createWalls() {
      List<Wall> walls = new ArrayList<>();

      for (int c=0; c < width; c++) {
         walls.add(new Wall(c, 0, "="));
         walls.add(new Wall(c, height - 1, "="));
      }

      for (int r=1; r < height-1; r++) {
         walls.add(new Wall(0, r, "|"));
         walls.add(new Wall(width - 1, r, "|"));
      }

      return walls;
   }


   private List<Monster> createMonsters(){
      Random random = new Random();
      ArrayList<Monster> monsters = new ArrayList<>();
      for(int i = 0; i < 3; i++)
         monsters.add(new Monster(random.nextInt(width-2)+1,random.nextInt(height-2)+1, "><"));

      return monsters;
   }


   private List<Coin> createCoins() {
      Random random = new Random();
      ArrayList<Coin> coins = new ArrayList<>();
      List<Position> posUsed = new ArrayList<>();
      posUsed.add(hero.getPosition());

      //max is (80-2)*(24-2) - 1 ----> 1715
      for (int i=0; i < 10; i++) {
         Coin c = new Coin(random.nextInt(width-2)+1, random.nextInt(height-2) + 1, "o");
         while(listContainsPos(posUsed, c.getPosition()))
            c = new Coin(random.nextInt(width-2)+1, random.nextInt(height-2) + 1, "o");

         coins.add(c);
         posUsed.add(c.getPosition());
      }

      return coins;
   }


   private boolean listContainsPos(List<Position> L, Position P)
   {
      for(Position pos : L)
         if(P.equals(pos)) return true;

      return false;
   }


   private void retrieveCoins(){
      for(int i=0; i<coins.size(); i++)
         if(coins.get(i).getPosition().equals(hero.getPosition())) {
            coins.remove(i);
            break;
         }
   }


   public void processKey(KeyStroke key)
   {
      KeyType type = key.getKeyType();
      if (type == KeyType.ArrowUp) moveHero(hero.getMoveUp());
      if (type == KeyType.ArrowDown) moveHero(hero.getMoveDown());
      if (type == KeyType.ArrowLeft) moveHero(hero.getMoveLeft());
      if (type == KeyType.ArrowRight) moveHero(hero.getMoveRight());

      System.out.println(type);
   }


   public void moveHero(Position move) {
      if(canMoveHero(move)) hero.setPosition(move);
   }

   private boolean canMoveHero(Position p) {
      for(Wall wall : walls)
         if (wall.getPosition().equals(p)) return false;

      return p != null && !(p.getX()<0 || p.getX()>(width-1) || p.getY()<0 || p.getY()>(height-1));
   }


   public boolean canMoveMonster(Position p) {
      if(p.getX() < width && p.getX() >= 0 && p.getY() >= 0 && p.getY() < height) {
         for(Wall wall: walls) if(wall.getPosition().equals(p)) return false;
         for(Monster monster: monsters) if(monster.getPosition().equals(p)) return false;
         return true;
      }

      return false;
   }

   private void moveMonsters() {
      Position p;
      for(Monster monster : monsters) {
         do {
            p = monster.move();
         } while(!canMoveMonster(p));
         monster.setPosition(p);
      }
   }
}

