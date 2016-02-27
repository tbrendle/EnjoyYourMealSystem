
public class Ingredient {
	private String name;
	private String personalization;
	public Ingredient(String name){
		this.name = name;
		this.personalization = "";
	}
	public Ingredient(String name, String personalization){
		this.name = name;
		this.personalization = personalization;
	}
	public String toString(){
		return name+" : "+personalization;
	}
	@Override
	public int hashCode(){
		return name.hashCode();
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Ingredient)
			return name.equals(((Ingredient) o).getName());
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonalization() {
		return personalization;
	}
	public void setPersonalization(String personalization) {
		this.personalization = personalization;
	}
}
