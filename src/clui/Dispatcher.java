package clui;
import java.util.HashMap;

/**
 * Dispatcher class, also known as Router : select the appropriate view and "redirects" the user to it
 */
public class Dispatcher {
   private AbstractView pageNotFoundView;
   private HashMap<String, AbstractView> routes;
   
   /**
    * Dispatcher constructor : creates the 404 page, and add all the routes of our program
    */
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
      addRoute("insertOffer", "saveMeal");
      addRoute("putInSpecialOffer", "saveMeal");
      addRoute("removeFromSpecialOffer", "saveMeal");
      addRoute("insertChef", "confirmSub");
      addRoute("registerclient", "confirmSub");
      addRoute("associateCard", "customerData");
      addRoute("associateAgreement", "customerData");
      addRoute("addContactInfo", "customerData");
      addRoute("showMeal");
      addRoute("selectmeal", "order");
      addRoute("personalizemeal", "order");
      addRoute("saveorder", "order");
      addRoute("listIngredients");
      addRoute("notifyBirthday", "notify");
      addRoute("notifyAd", "notify");
      addRoute("exception");
   }
   
   /**
    * Add a route to the dispatcher
    * @param method the name of the route
    */
   public void addRoute(String method){
	   addRoute(method, method);
   }
   
   /**
    * Add a route to the dispatcher
    * @param method the name of the route
    * @param view the view associated with the route
    */
   public void addRoute(String method, String view){
	   routes.put(method.toLowerCase(), ViewFactory.create(view));
   }
   
   /**
    * Dispatch : redirects the user to the appropriate view
    * @param method the name of the route
    * @param result the informations to be passed to the view for it to display them
    */
   public void dispatch(String method, Object result){
	   AbstractView currentView = routes.get(method);
	   if(currentView == null)
		   pageNotFoundView.show(null);
	   else
		   currentView.show(result);
   }
}
