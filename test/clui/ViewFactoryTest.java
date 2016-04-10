package clui;
import static org.junit.Assert.*;

import org.junit.Test;

import clui.AbstractView;
import clui.LoginView;
import clui.ViewFactory;

/**
 * Test class for View Factory
 */
public class ViewFactoryTest {
	private ViewFactory factory = new ViewFactory();
	
	/**
	 * Test for creating a LoginView
	 */
	@Test
	public void LoginViewTest() {
		LoginView lv = (LoginView) factory.create("Login");
		LoginView lu = (LoginView) factory.create("login");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a ConfirmSubView
	 */
	@Test
	public void ConfirmSubViewTest() {
		ConfirmSubView lv = (ConfirmSubView) factory.create("ConfirmSub");
		ConfirmSubView lu = (ConfirmSubView) factory.create("confirmSub");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a CreateMealView
	 */
	@Test
	public void CreateMealViewTest() {
		CreateMealView lv = (CreateMealView) factory.create("CreateMeal");
		CreateMealView lu = (CreateMealView) factory.create("createMeal");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a CustomerDataView
	 */
	@Test
	public void CustomerDataViewTest() {
		CustomerDataView lv = (CustomerDataView) factory.create("CustomerData");
		CustomerDataView lu = (CustomerDataView) factory.create("customerData");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a ExceptionView
	 */
	@Test
	public void ExceptionViewTest() {
		ExceptionView lv = (ExceptionView) factory.create("Exception");
		ExceptionView lu = (ExceptionView) factory.create("exception");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a LandingView
	 */
	@Test
	public void LandingViewTest() {
		LandingView lv = (LandingView) factory.create("Landing");
		LandingView lu = (LandingView) factory.create("landing");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a ListIngredientsView
	 */
	@Test
	public void ListIngredientsViewTest() {
		ListIngredientsView lv = (ListIngredientsView) factory.create("ListIngredients");
		ListIngredientsView lu = (ListIngredientsView) factory.create("listIngredients");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a NotifyView
	 */
	@Test
	public void NotifyViewTest() {
		NotifyView lv = (NotifyView) factory.create("Notify");
		NotifyView lu = (NotifyView) factory.create("notify");
		assertNotNull(lu);
		assertNotNull(lv);
	}
	
	/**
	 * Test for creating a OrderView
	 */
	@Test
	public void OrderViewTest() {
		OrderView lv = (OrderView) factory.create("Order");
		OrderView lu = (OrderView) factory.create("order");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a PageNotFoundView
	 */
	@Test
	public void PageNotFoundViewTest() {
		PageNotFoundView lv = (PageNotFoundView) factory.create("PageNotFound");
		PageNotFoundView lu = (PageNotFoundView) factory.create("pageNotFound");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a SaveMealView
	 */
	@Test
	public void SaveMealViewTest() {
		SaveMealView lv = (SaveMealView) factory.create("SaveMeal");
		SaveMealView lu = (SaveMealView) factory.create("saveMeal");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a ShowMealView
	 */
	@Test
	public void ShowMealViewTest() {
		ShowMealView lv = (ShowMealView) factory.create("ShowMeal");
		ShowMealView lu = (ShowMealView) factory.create("showMeal");
		assertNotNull(lu);
		assertNotNull(lv);
	}

	/**
	 * Test for creating a View that doesn't exist
	 */
	@Test
	public void UnknownViewTest() {
		AbstractView v = factory.create("UNNKNOWWN VIEW");
		assertNull(v);
	}
}
