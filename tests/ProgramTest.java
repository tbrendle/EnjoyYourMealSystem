import static org.junit.Assert.*;

import org.junit.Test;

public class ProgramTest {
	
	//We avoid to erase the initial input file
	private Program program = new Program("testScenario_1_input", "testScenario_1_output");

	/*@Test 
	public void testCreateBobRed(){
		try{
			program.insertChef("Bob", "Red", "bobred", "123456");
		} catch (Exception e){}
	}*/
	

	@Test
	public void test1(){
		//
		program.authenticateUser("bobred", "123456");
		program.createMeal("Raclette", 20);
		program.addIngredient("cheese", 80);
		program.addIngredient("ham", 40);
		program.addIngredient("potatoes", 50);
		program.saveMeal();
		program.createMeal("Cheeeeese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Chese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeceese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeessese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeeddese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Chedfggfeeeese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.listIngredients("Raclette");
		program.registerCustomer("Mario", "Rossi", "Mario", "345678");
		program.authenticateUser("Mario", "345678");
		
	}
	

	


}
