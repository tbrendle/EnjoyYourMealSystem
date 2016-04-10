
package clui;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Meal;
import core.Program;


/**
 * Test class for the controller
 */
public class ProgramControllerTest {
	private Program program=  new Program("testScenario_1_input", "testScenario_1_output");	
	private ProgramController pc = new ProgramController(program);
	
	/**
	 * Test the parsing of a command given by the user
	 */
	@Test
	public void testParsing(){
		String args[] = pc.parse("method arg1 \"arg 2\" arg 3");
		assertTrue(args.length == 5);
		assertTrue(args[0].equals("method"));
		assertTrue(args[1].equals("arg1"));
		assertTrue(args[2].equals("arg 2"));
		assertTrue(args[3].equals("arg"));
		assertTrue(args[4].equals("3"));
	}
	
	/**
	 * Full test, with the scenario given in the specifications, testing all controller possibilities
	 */
	@Test
	public void testFull() {
		pc.handleRequest("login \"bobred\" \"123456\"");
		pc.handleRequest("createMeal \"Raclette\" 20");
		pc.handleRequest("addIngredient \"cheese\" 80");
		pc.handleRequest("addIngredient \"ham\" 40");
		pc.handleRequest("addIngredient \"potatoes\" 50");
		//  verify the currentMeal<> operation
		assertEquals(program.currentMeal().getName(), "Raclette");
		assertTrue(program.currentMeal().getIngredientQuantity("cheese")==80);
		assertTrue(program.currentMeal().getIngredientQuantity("ham")==40);
		assertTrue(program.currentMeal().getIngredientQuantity("potatoes")==50);
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Soupe\" 10");
		pc.handleRequest("saveMeal");
		pc.handleRequest("addIngredient carotte 10");
		pc.handleRequest("createMeal \"Croque-Monsieur\" 10");
		pc.handleRequest("addIngredient fromage 10");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Pates Carbonara\" 10");
		pc.handleRequest("addIngredient pates 300");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Pates Bolognaise\" 10");
		pc.handleRequest("addIngredient pates 200");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Saumon\" 10");
		pc.handleRequest("addIngredient saumon 200");
		pc.handleRequest("addIngredient fenouil 2");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Saucisse purée\" 10");
		pc.handleRequest("addIngredient \"pommes de terre\" 200");
		pc.handleRequest("addIngredient saucisse 200");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Steak\" 10");
		pc.handleRequest("addIngredient steak 300");
		pc.handleRequest("addIngredient ");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Omelette\" 10");
		pc.handleRequest("addIngredient oeuf 100");
		pc.handleRequest("addIngredient ");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Quiche\" 10");
		pc.handleRequest("addIngredient oeuf 50");
		pc.handleRequest("addIngredient pates 50");
		pc.handleRequest("addIngredient creme 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Hamburger\" 10");
		pc.handleRequest("addIngredient pain 100");
		pc.handleRequest("addIngredient cornichon 10");
		pc.handleRequest("addIngredient steak 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Côtes de porc\" 10");
		pc.handleRequest("addIngredient porc 200");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Canard aux olives\" 10");
		pc.handleRequest("addIngredient canard 200");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Tarte au fromage\" 10");
		pc.handleRequest("addIngredient fromage 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Lasagnes\" 10");
		pc.handleRequest("addIngredient pates 100");
		pc.handleRequest("addIngredient viande 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Pizza\" 10");
		pc.handleRequest("addIngredient tomate 100");
		pc.handleRequest("addIngredient pain 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Pasta Boulettes\" 10");
		pc.handleRequest("addIngredient pates 100");
		pc.handleRequest("addIngredient boulettes 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Rillettes de porc\" 10");
		pc.handleRequest("addIngredient rillettes 100");
		pc.handleRequest("addIngredient porc 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Eclair\" 10");
		pc.handleRequest("addIngredient sugar 50");
		pc.handleRequest("addIngredient vanille 50");
		pc.handleRequest("addIngredient \"pate a choux\" 50");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Gateau au chocolat\" 10");
		pc.handleRequest("addIngredient sugar 100");
		pc.handleRequest("addIngredient farine 100");
		pc.handleRequest("addIngredient oeuf 100");
		pc.handleRequest("addIngredient chocolat 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("createMeal \"Brownie\" 10");
		pc.handleRequest("addIngredient sugar 70");
		pc.handleRequest("addIngredient chocolat 100");
		pc.handleRequest("saveMeal");
		pc.handleRequest("listIngredients \"Raclette\"");
		assertTrue(program.listIngredients("Raclette").get("cheese").getQuantity()==80);
		assertTrue(program.listIngredients("Raclette").get("ham").getQuantity()==40);
		assertTrue(program.listIngredients("Raclette").get("potatoes").getQuantity()==50);
		pc.handleRequest("registerClient \"Mario\" \"Rossi\" \"Mario\" \"345678\"");
		pc.handleRequest("addContactInfo \"Mario\" \"mario@rossi.net\" \"email\"");
		pc.handleRequest("addContactInfo \"Mario\" \"+33645623142\" \"phone\"");
		pc.handleRequest("associateCard \"Mario\" \"Basic\"");
		pc.handleRequest("setBirthday \"Mario\" 21/2/2012");
		pc.handleRequest("login \"Mario\" \"345678\"");
		pc.handleRequest("selectMeal \"Raclette\" 1");
		pc.handleRequest("personalizeMeal \"cheese\" 10");
		pc.handleRequest("personalizeMeal \"ham\" -50");
		pc.handleRequest("selectMeal \"Raclette\" 2");
		pc.handleRequest("saveOrder");
		pc.handleRequest("login \"bobred\" \"123456\"");
		pc.handleRequest("notifyBirthday");
		pc.handleRequest("insertOffer Raclette 2");
		pc.handleRequest("notifyAd \"New offer on Raclette : It costs only 2$ this week end\" \"Raclette\" 2");
		pc.handleRequest("showMeal \"AS IT IS\"");
		pc.handleRequest("showMeal \"MOSTLY MODIFIED\"");
		Meal raclette = program.showMeal().get("Raclette");
		assertTrue(raclette.isPromotion());
		assertTrue(raclette.getSpecialPrice()==2);
		assertTrue(raclette.getPrice()==20);
		
	}
}
