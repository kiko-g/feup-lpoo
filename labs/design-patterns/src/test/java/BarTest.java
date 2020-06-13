import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class BarTest
{
   @Test
   public void happyHour() {
      Bar bar = new StringBar();
      assertFalse(bar.isHappyHour());

      bar.startHappyHour();
      assertTrue(bar.isHappyHour());

      bar.endHappyHour();
      assertFalse(bar.isHappyHour());
   }


   @Test
   public void addObserver() {
      Bar bar = new StringBar();

      HumanClient clientMock = Mockito.mock(HumanClient.class);
      bar.addObserver(clientMock);

      Mockito.verify(clientMock, Mockito.never()).happyHourStarted(bar);
      Mockito.verify(clientMock, Mockito.never()).happyHourEnded(bar);

      bar.startHappyHour();
      Mockito.verify(clientMock, Mockito.times(1)).happyHourStarted(bar);
      Mockito.verify(clientMock, Mockito.never()).happyHourEnded(bar);

      bar.endHappyHour();
      Mockito.verify(clientMock, Mockito.times(1)).happyHourStarted(bar);
      Mockito.verify(clientMock, Mockito.times(1)).happyHourEnded(bar);
   }

   @Test
   public void removeObserver() {
      Bar bar = new StringBar();

      HumanClient clientMock = Mockito.mock(HumanClient.class);
      bar.addObserver(clientMock);
      bar.removeObserver(clientMock);

      bar.startHappyHour();
      bar.endHappyHour();

      Mockito.verify(clientMock, Mockito.never()).happyHourStarted(bar);
      Mockito.verify(clientMock, Mockito.never()).happyHourEnded(bar);
   }


   private StringRecipe getRecipe() {
      StringInverter si = new StringInverter();
      StringCaseChanger cc = new StringCaseChanger();
      StringReplacer sr = new StringReplacer('A', 'X');

      List<StringTransformer> transformers = new ArrayList<>();
      transformers.add(si);
      transformers.add(cc);
      transformers.add(sr);

      StringRecipe recipe = new StringRecipe(transformers);
      return recipe;
   }

   @Test
   public void orderStringRecipe() {
      StringBar stringBar = new StringBar();
      StringDrink drink = new StringDrink("AbCd-aBcD");
      StringRecipe recipe = getRecipe();

      stringBar.order(drink, recipe);
      assertEquals("dCbX-DcBa", drink.getText());
   }
}
