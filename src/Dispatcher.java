import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dispatcher {
   private OrderView orderView;
   private MealView mealView;
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
      pageNotFoundView = new PageNotFoundView(p);
      availableMethods = program.getClass().getDeclaredMethods();
      routes = new HashMap<String, AbstractView>();
      routes.put("landing", landingView);
      routes.put("login", loginView);
      routes.put("logout", loginView);
      //add routes here
      routes.put("createmeal", mealView);
      routes.put("landing", landingView);
      routes.put("landing", landingView);
      routes.put("landing", landingView);
      routes.put("landing", landingView);
      routes.put("landing", landingView);
      routes.put("landing", landingView);
      
   }
   
   private void handleMethod(String method, String[] params) throws InvocationTargetException {
	   boolean foundOne = false;
	   boolean invoked = false;
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
							System.out.println(param);
							args.add(param);
						}
						System.out.println(validMethod);
						if(validMethod){
							m.invoke(program, args.toArray());
							invoked = true;
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
       if(foundOne && !invoked ){
    	   throw new InvalidParameterException("Invalid parameters");
       }
   }

   public void dispatch(String request){
	   String[] args = request.split(" ");
	   if(args.length>0){
		   String method = args[0].toLowerCase();
		   AbstractView currentView = routes.get(method);
		   if(currentView == null)
			   pageNotFoundView.show();
		   else
			   try{
				   handleMethod(method, args);
				   currentView.show();
			   } catch(InvocationTargetException e){
				   exceptionView.show(e.getCause().getMessage());
				} catch (Exception e){
				   System.out.println("EXCEPTION CATCHED");
				   exceptionView.show(e.getMessage());
				   e.printStackTrace();
			   }
		   
	   }
	   
   }
}
