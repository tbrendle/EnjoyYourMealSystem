package clui;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import core.Program;

public class ProgramController {
	private Program program;
	private Dispatcher dispatcher;
	private Method[] availableMethods;
	
	public ProgramController(Program p){
		this.program=p;
		this.dispatcher = new Dispatcher();
		availableMethods = program.getClass().getDeclaredMethods();
	}
	
	public String[] parse(String request){
		ArrayList<String> argList = new ArrayList<String>();
		String word = "";
		boolean block = false;
		for(int i = 0; i<request.length(); i++){
			if(request.charAt(i)==" ".charAt(0) && !block){
				if(word.length()>0){
					argList.add(word);
					word = "";
				}
			} else if (request.charAt(i)=="\"".charAt(0)){
				block = !block;
			} else {
				word+=request.charAt(i);
			}
		}
		argList.add(word);
		return argList.toArray(new String[0]);
	}
	
	public void handleRequest(String request) {
		String[] args = parse(request);
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
}
