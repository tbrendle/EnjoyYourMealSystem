import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dispatcher {
   private OrderView orderView;
   private MealView mealView;
   private CreateMealView createMealView;
   private LoginView loginView;
   private LandingView landingView;
   private ExceptionView exceptionView;
   private PageNotFoundView pageNotFoundView;
   private HashMap<String, AbstractView> routes;
   private Program program;
   private Method[] availableMethods;
   
   public Dispatcher(Program p){
	   
	  program = p;
      orderView = new OrderView(p);
      mealView = new MealView(p);
      loginView = new LoginView(p);
      landingView = new LandingView(p); 
      exceptionView = new ExceptionView(p);
      createMealView = new CreateMealView(p);
      pageNotFoundView = new PageNotFoundView(p);
      availableMethods = program.getClass().getDeclaredMethods();
      routes = new HashMap<String, AbstractView>();
      routes.put("landing", landingView);
      routes.put("login", loginView);
      routes.put("logout", loginView);
      routes.put("createmeal", createMealView);
      routes.put("addingredient", createMealView);
      routes.put("currentmeal", createMealView);
      routes.put("savemeal", createMealView);
      routes.put("insertchef", landingView);
      routes.put("registerclient", landingView);
      routes.put("selectmeal", orderView);
      routes.put("personalizemeal", orderView);
      routes.put("saveorder", orderView);
      //add routes here
      
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

   public void dispatch(String request){
	   String[] args = request.split(" ");
	   if(args.length>0){
		   String method = args[0].toLowerCase();
		   AbstractView currentView = routes.get(method);
		   System.out.println("Page requested: " + method);
		   if(currentView == null)
			   pageNotFoundView.show();
		   else
			   try{
				   Object result = handleMethod(method, args);
				   if(result == null)
					   currentView.show();
				   else
					   currentView.show(result);
			   } catch(InvocationTargetException e){
				   exceptionView.show(e.getCause().getMessage());
			   } catch(InvalidParameterException e){
				   exceptionView.show(e.getMessage());
			   } catch (Exception e){
				   //Should never happen
				   System.out.println("EXCEPTION CATCHED");
				   exceptionView.show(e.getMessage());
				   e.printStackTrace();
			   }
	   }
	   
   }
}
