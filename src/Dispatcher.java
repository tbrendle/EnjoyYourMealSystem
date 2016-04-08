import java.util.HashMap;

public class Dispatcher {
   private AbstractView pageNotFoundView;
   private HashMap<String, AbstractView> routes;
   
   public Dispatcher(){
	  //This view is mandatory
      pageNotFoundView = ViewFactory.create("pageNotFound");
      //Create routing matching functions names with views
      routes = new HashMap<String, AbstractView>();
      addRoute("landing");
      addRoute("login");
      addRoute("logout", "login");
      addRoute("createMeal");
      addRoute("addIngredient", "createMeal");
      addRoute("currentMeal", "createMeal");
      addRoute("saveMeal", "saveMeal");
      addRoute("insertChef", "landing");
      addRoute("registerclient", "landing");
      addRoute("selectmeal", "order");
      addRoute("personalizemeal", "order");
      addRoute("saveorder", "order");
      addRoute("exception");
   }
   
   public void addRoute(String method){
	   addRoute(method, method);
   }
   public void addRoute(String method, String view){
	   routes.put(method.toLowerCase(), ViewFactory.create(view));
   }
   
   public void dispatch(String method, Object result){
	   AbstractView currentView = routes.get(method);
	   System.out.println("Page requested: " + method);
	   if(currentView == null)
		   pageNotFoundView.show(null);
	   else
		   currentView.show(result);
   }
}
