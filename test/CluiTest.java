import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import clui.ProgramController;
import core.Meal;
import core.Program;
import core.ScorableMeal;

public class CluiTest {
	
	private Program program = new Program("testScenario_1_input", "testScenario_1_output_clui");	

	@Test
	public void testFidelityCard() {
		File file = new File("test1.txt");
		Scanner input = new Scanner(System.in);
        try {
			input = new Scanner(file);
			ProgramController controller = new ProgramController(program);
			controller.handleRequest("landing");
			while(input.hasNextLine()){
				String line = input.nextLine();
				System.out.println(line);
				controller.handleRequest(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	//We verify program.saveData works by re importing datas from the output
  		Program verifProgram = new Program("testScenario_1_output_clui");
  		//We verify that raclette is still as it was
  		Meal raclette = verifProgram.showMeal().get("Raclette");
  		assertTrue(raclette.isPromotion());
  		assertTrue(raclette.getSpecialPrice()==2);
  		assertTrue(raclette.getPrice()==20);
  		assertEquals(verifProgram.showMeal("AS IT IS").get(0).getName(), "Raclette");
  		assertTrue(verifProgram.showMeal("AS IT IS").size() == 1);
  		assertTrue(verifProgram.showMeal("AS IT IS").get(0).getScore()==3);
  		assertEquals(verifProgram.showMeal("MOSTLY MODIFIED").get(0).getName(), "Raclette");
  		assertTrue(verifProgram.showMeal("MOSTLY MODIFIED").get(0).getScore()==1);
	
	}

}
