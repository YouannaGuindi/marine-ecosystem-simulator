
public interface MarineMovement {
	MovementType getMovementType();

    default void move(String organismName) /*DEFAULT implements methods of interface in classes without needing to override them*/
    {
        switch (getMovementType()) {
            case SWIMMING:
                System.out.println(organismName + " swims through the water.");
                break;
                
            case CRAWLING:
                System.out.println(organismName + " crawls along the ocean floor.");
                break;
                
            case STATIONARY:
                System.out.println(organismName + " is stationary and does not move.");
                break;  
                
            default:
                System.out.println(organismName + " has an unknown movement type.");
        }
    }
}
