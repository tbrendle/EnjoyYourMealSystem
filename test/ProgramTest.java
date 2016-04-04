import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Program
 * This is the test scenario of our program
 */
public class ProgramTest {
	
	//We avoid erasing the initial input file
	private Program program = new Program("testScenario_1_input", "testScenario_1_output");	

	/**
	 * First test scenario as described in the specifications
	 */
	@Test
	public void test1(){
		//execute the login<‘‘bobred’’, ‘‘123456’’> operation for “Bob Red”
		program.login("bobred", "123456");
		//execute the createMeal<‘‘Raclette’’> to create a meal named “Raclette”, costing 20euros
		program.createMeal("Raclette", 20);
		// execute addIngredient <ingredientName, quantity> to add 3 ingredients
		// (cheese 90g, ham 40g, potatoes 50g) to the “Raclette”
		program.addIngredient("cheese", 80);
		program.addIngredient("ham", 40);
		program.addIngredient("potatoes", 50);
		//  verify the currentMeal<> operation
		assertEquals(program.currentMeal().getName(), "Raclette");
		assertTrue(program.currentMeal().getIngredientQuantity("cheese")==80);
		assertTrue(program.currentMeal().getIngredientQuantity("ham")==40);
		assertTrue(program.currentMeal().getIngredientQuantity("potatoes")==50);
		// save your meal using saveMeal<>
		program.saveMeal();
		// add 20 different meals
		String[] mealNames = {
			"Soupe", "Croque-Monsieur", "Pates Carbonara", 
			"Pates Bolognaise", "Saumon", "Saucisse purée",
			"Steak", "Omelette", "Quiche", "Hamburger", "Côtes de porc",
			"Canard aux olives", "Tarte au fromage", "Lasagnes", "Pizza",
			"Pasta Boulettes", "Rillettes de porc", "Sandwich", "Blanquette", "Flamekuche"
		};
		String[] ingredientNames = {
			"pasta", "sugar", "meat", "ham", "cheese", "tomatoes"
		};
		for(int i = 0; i < mealNames.length; i++){
			//Everything costs 10euros
			program.createMeal(mealNames[i], 10);
			//30g for each
			program.addIngredient(ingredientNames[(i+1)%ingredientNames.length], 1+i*10);
			program.addIngredient(ingredientNames[(2*i)%ingredientNames.length], 2+i*11);
			program.saveMeal();
		}
		//TODO: When we will have CLUI
		//execute the listIngredients<‘‘Raclette’’> CLUI command to display the
		//content of the “Raclette” meal
		program.listIngredients("Raclette");
		assertTrue(program.listIngredients("Raclette").get("cheese").getQuantity()==80);
		assertTrue(program.listIngredients("Raclette").get("ham").getQuantity()==40);
		assertTrue(program.listIngredients("Raclette").get("potatoes").getQuantity()==50);
		//execute the registerClient <firstName, lastName, username, password>
		//to register a cliend “Mario Rossi” with username “Mario” and password “345678”
		program.registerClient("Mario", "Rossi", "Mario", "345678");
		//add the email address and the phone number for the client performing the
		//addContactInfo<contactInfo> operation
		program.addContactInfo("Mario", "mario@rossi.net", "email");
		program.addContactInfo("Mario", "+33645623142", "phone");
		//select a card for the client using the associateCard<userName, cardType>
		//operation
		program.associateCard("Mario", "Basic");
		// execute the login<‘‘mario’’, ‘‘345678’’’> operation for “Mario Rossi”
		program.login("Mario", "345678");
		// check the ingredients of the “Raclette” using the listIngredients < ‘‘Raclette’’>
		assertTrue(program.listIngredients("Raclette").get("cheese").getQuantity()==80);
		assertTrue(program.listIngredients("Raclette").get("ham").getQuantity()==40);
		assertTrue(program.listIngredients("Raclette").get("potatoes").getQuantity()==50);
		//order one Raclette using selectMeal<mealName, quantity> operation
		program.selectMeal("Raclette", 1);
		//modify the Raclette meal using the command personalizeMeal <
		//ingredient, quantity> to add 10g of cheese to the raclette. 
		program.personalizeMeal("cheese", 10);
		// Handle the case in which the quantity of an ingredient is modified to 0
		program.personalizeMeal("ham", -50);
		//order two standard Raclette using selectMeal<mealName, quantity> operation
		program.selectMeal("Raclette", 2);
		// save the order executing the command saveOrder<>
		Order o = program.saveOrder();
		assertTrue(o.getMeals().get(0).getIngredientQuantity("cheese") == 80+10);
		assertNull(o.getMeals().get(0).getIngredients().get("ham"));
		assertTrue(o.getMeals().get(1).getIngredientQuantity("cheese") == 80);
		assertTrue(o.getMeals().get(2).getIngredientQuantity("ham") == 40);
		//The meal cost 3*20 + 10 because we added 10g of cheese to the first Raclette
		assertTrue(o.getPrice()==70);
		//execute the login<‘‘bobred’’, ‘‘123456’’> operation for “Bob Red”
		program.login("bobred", "123456");
		//send a message to a user that has his birthday in the same day using the
		//notifyBirthday <> operation (for this operation it is not needed that the
		//email is sent, just that the system correctly recognizes the set of user to whom
		//send a message)
		program.notifyBirthday();
		//put the meal “Raclette” in special offer using the operation putInSpecialOffer
		//<mealName, price>
		program.insertOffer("Raclette", 2);
		//this offer does not change the price of previous orders
		assertTrue(o.getPrice()==70);
		//the system sends a special message using the notifyAd <message, mealName,
		//specialPrice> operation
		program.notifyAd("New offer on Raclette : It costs only 2 euros this week end", "Raclette", 2);
		
		//We verify program.saveData works by re importing datas from the output
		Program verifProgram = new Program("testScenario_1_output");
		for(String mealName : mealNames)
			assertEquals(verifProgram.showMeal().get(mealName).getName(), mealName);
		//We verify that raclette is still as it was
		Meal raclette = verifProgram.showMeal().get("Raclette");
		assertTrue(raclette.isPromotion());
		assertTrue(raclette.getSpecialPrice()==2);
		assertTrue(raclette.getPrice()==20);
		//We verify order stats. It also tests ordering strategies
		String[] sorting = {"JUST ON SALE", "AS IT IS", "MOSTLY MODIFIED"};
		for(String s : sorting){
			System.out.println("========="+s+"==========");
			for(ScorableMeal meal : verifProgram.showMeal(s)){
				System.out.println(meal.getScore()+" "+meal.getName());
			}
		}
		assertEquals(verifProgram.showMeal("AS IT IS").get(0).getName(), "Raclette");
		assertTrue(verifProgram.showMeal("AS IT IS").size() == 21);
		assertTrue(verifProgram.showMeal("AS IT IS").get(0).getScore()==3);
		assertEquals(verifProgram.showMeal("MOSTLY MODIFIED").get(0).getName(), "Raclette");
		assertTrue(verifProgram.showMeal("MOSTLY MODIFIED").get(0).getScore()==1);
	}
	

	


}
