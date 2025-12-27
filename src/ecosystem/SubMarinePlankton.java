
public class SubMarinePlankton extends MarineOrganism {

	
	public SubMarinePlankton(String nameOfOrganism, int avgAge, String size, String type, DietType dietType,
			ReproductionType reproductionType, double reproductionRate, Environment livingEnvironment) {
		super(nameOfOrganism, avgAge, size, type, dietType, reproductionType, reproductionRate, livingEnvironment);
	}
	

	public MovementType getMovementType() {
		return MovementType.CRAWLING;
	}


	
	

}
