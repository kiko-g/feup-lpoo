package com.g28.model;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends Element
{
   private Timer timer;
   public PowerUp(int x, int y) {
      super(x, y);
      timer = new Timer();
   }

   public void cancelTimer() {
      this.timer.cancel();
   }

   public void activate(Player player, List<PowerUp> powerUps) {
       if(player.getPoweredUp()) {
          for(PowerUp powerUp : powerUps) {
             powerUp.cancelTimer();
          }
       }

      player.setPoweredUp(true);
      new Timer().schedule(new TimerTask() {
         @Override
         public void run() {
            player.setPoweredUp(false);
         }
      }, 5000);
   }
}
