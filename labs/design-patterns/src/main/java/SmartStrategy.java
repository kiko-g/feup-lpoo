import java.util.ArrayList;
import java.util.List;

public class SmartStrategy implements OrderingStrategy {
   private List<StringRecipe> wantedRecipes;
   private List<StringDrink> wantedDrinks;

   public SmartStrategy(){
      this.wantedDrinks = new ArrayList<>();
      this.wantedRecipes = new ArrayList<>();
   }


   public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
      if(bar.isHappyHour()) bar.order(drink,recipe);
      else{
         this.wantedDrinks.add(drink);
         this.wantedRecipes.add(recipe);
      }
   }

   @Override
   public void happyHourStarted(StringBar bar) {
      for(int i = 0; i<wantedDrinks.size(); i++ ){
         bar.order(wantedDrinks.get(i),wantedRecipes.get(i));
         wantedDrinks.remove(i);
         wantedRecipes.remove(i);
      }
   }