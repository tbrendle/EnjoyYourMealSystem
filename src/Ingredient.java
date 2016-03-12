
public class Ingredient {
	private String name;
	private Integer quantity;
	private Integer personalization;
	private float costPerUnit;
	public Ingredient(String name){
		this.name = name;
		this.personalization = 0;
		this.setCostPerUnit(1);
	}
	public Ingredient(String name, Integer personalization){
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
	public float getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(float costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setPersonalization(Integer personalization){
		this.personalization = personalization;
	}
	public Integer getPersonalization(){
		return this.personalization;
	}
	
}
