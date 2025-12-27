
public class SubMarineFish extends MarineOrganism{
	
	
	public SubMarineFish(String nameOfOrganism, int avgAge, String size, String type, DietType dietType,
			ReproductionType reproductionType, double reproductionRate, Environment livingEnvironment) {
		super(nameOfOrganism, avgAge, size, type, dietType, reproductionType, reproductionRate, livingEnvironment);
		
	}

	public MovementType getMovementType() {
				return MovementType.SWIMMING;
	}
		

			/*public void move() //we replaced it with the interface 
			 {
			        switch (marineMovement) {
			            case SWIMMING:
			                System.out.println(nameOfOrganism + " swims through the water.");
			                break;
			            case FLOATING:
			                System.out.println(nameOfOrganism + " floats gently on the surface.");
			                break;
			            case DRIFTING:
			                System.out.println(nameOfOrganism + " drifts with the current.");
			                break;
			            case CRAWLING:
			                System.out.println(nameOfOrganism + " crawls along the ocean floor.");
			                break;
			            case STATIONARY:
			                System.out.println(nameOfOrganism + " is stationary and does not move.");
			                break;
			        }
			    }*/



}
