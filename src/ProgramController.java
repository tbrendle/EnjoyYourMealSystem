import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProgramController {
	private Program program;
	private Dispatcher dispatcher;
	private Method[] availableMethods;
	private Scanner input = new Scanner(System.in);
	
	public ProgramController(Program p){
		this.program=p;
		this.dispatcher = new Dispatcher();
		availableMethods = program.getClass().getDeclaredMethods();
	}
	
	public void handleRequest(String request) {
		String[] args = request.split(" ");
		if(args.length>0){
			String method = args[0].toLowerCase();
			try{
				Object result = handleMethod(method, args);
				dispatcher.dispatch(method,result);
			} catch(InvocationTargetException e){
			   dispatcher.dispatch("exception", e.getCause().getMessage());
			} catch(InvalidParameterException e){
			   dispatcher.dispatch("exception", e.getMessage());
			} catch (Exception e){
			   //Should never happen
			   System.out.println("EXCEPTION CATCHED");
			   dispatcher.dispatch("exception", e.getMessage());
			   e.printStackTrace();
			}
		}
	}
		
	private Object handleMethod(String method, String[] params) throws InvocationTargetException {
	   boolean foundOne = false;
       for (Method m : availableMethods){
    	   if(m.getName().toLowerCase().equals(method)){
    		   foundOne = true;
    		   if(m.getParameterTypes().length == params.length-1){
					try {
						boolean validMethod = true;
						ArrayList<Object> args = new ArrayList<Object>();
						for(int i = 0; i<params.length-1; i++){
							Object param = null;
							String inputParam = params[i+1];
							if(m.getParameterTypes()[i] == float.class){
								try{
									param = Float.parseFloat(inputParam);
								} catch (NumberFormatException e) {
									validMethod = false;
								}
							} else if(m.getParameterTypes()[i] == Integer.class){
								try{
									param = Integer.parseInt(params[i+1]);
								} catch (NumberFormatException e) {
									validMethod = false;
								}
							} else if(m.getParameterTypes()[i] == String.class){
								param = inputParam;
							} else if(m.getParameterTypes()[i] == boolean.class){
								String[] yes = {"yes", "y", "true"};
								String[] no = {"no", "n", "false"};
								if(Arrays.asList(yes).contains(inputParam.toLowerCase())){
									param = true;
								} else if(Arrays.asList(no).contains(inputParam.toLowerCase())){
									param = false;
								} else {
									validMethod = false;
								}
							} else {
								validMethod = false;
							}
							args.add(param);
						}
						if(validMethod){
							return m.invoke(program, args.toArray());
						}
							
					} catch(InvocationTargetException e){
						 throw e;
					}
					catch (Exception e) {
						//Should never happen
						e.printStackTrace();
						throw new InvalidParameterException("Invalid parameters");
					}
    		   }
    	   }
       }
       if(foundOne){
    	   throw new InvalidParameterException("Invalid parameters");
       }
	return null;
   }


	
	private void waitNewRequest() {
		handleRequest(input.nextLine());
	}
}
