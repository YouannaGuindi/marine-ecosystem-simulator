
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class MarineOrganism implements MarineMovement{
	
	 
	protected String nameOfOrganism;
	private int avgAge; /*AVERAGE AGE for each organism type */
	private String size; //small medium large 
	private String type; /*Fish corals plankton*/
	private DietType dietType; /*enum*/
	private ReproductionType reproductionType;/*enum*/
    private double reproductionRate;
    private Environment livingEnvironment;
    
	
	public MarineOrganism(String nameOfOrganism, int avgAge, String size, String type, DietType dietType, 
			ReproductionType reproductionType, double reproductionRate,Environment livingEnvironment) /*original Constructor */
	{
		this.nameOfOrganism = nameOfOrganism;
		this.avgAge = avgAge;
		this.size = size;
		this.type = type;
		this.dietType = dietType;
		this.reproductionType = reproductionType;
        this.reproductionRate = reproductionRate;
        this.livingEnvironment=livingEnvironment;
	}


	public String getNameOfOrganism() 
	{
		return nameOfOrganism;
	}

	public void setNameOfOrganism(String nameOfOrganism)
	{
		this.nameOfOrganism = nameOfOrganism;
	}

	public int getAvgAge() 
	{
		return avgAge;
	}

	public void setAvgAge(int avgAge) 
	{
		this.avgAge = avgAge;
	}

	public String getSize() 
	{
		return size;
	}

	public void setSize(String size) 
	{
		this.size = size;
	}


	public String getType() 
	{
		return type;
	}


	public void setType(String type) 
	{
		this.type = type;
	}


	public void setDietType(DietType dietType) 
	{
		this.dietType = dietType;
	}

	public DietType getDietType() 
	{
		return dietType;
	}

	public void setReproductionType(ReproductionType reproductionType) 
	{
			this.reproductionType = reproductionType;
	}
	
	public ReproductionType getReproductionType()
	{
		return reproductionType;
	}
 
	public double getReproductionRate() 
	{
     return reproductionRate;
    }

	public void setReproductionRate(double reproductionRate) 
	{
		this.reproductionRate = reproductionRate;
	}
	
	
	public abstract MovementType getMovementType();	
	 

    public void setLivingEnvironment(Environment livingEnvironment) 
    {
        this.livingEnvironment = livingEnvironment;
    }
	
    public Environment getLivingEnvironment() 
	{
        return livingEnvironment;
    }

	public String toString() {
		return "MarineOrganism [nameOfOrganism=" + nameOfOrganism + ", avgAge=" + avgAge + ", size=" + size + ", type="
				+ type + ", dietType=" + dietType + ", reproductionType=" + reproductionType + ", reproductionRate="
				+ reproductionRate + ", livingEnvironment=" + livingEnvironment + "]";
	}
	public static MarineOrganism getOrganismByName(String organismName, String filePath) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.trim().split("\\s+");
	            if (parts.length >= 8 && parts[0].equalsIgnoreCase(organismName)) {
	                String name = parts[0];
	                int avgAge = Integer.parseInt(parts[1]);
	                String size = parts[2];
	                String type = parts[3];
	                
	                // Parse DietType from String
	                DietType diet;
	                try {
	                    diet = DietType.valueOf(parts[4].toUpperCase());
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: Invalid diet type for organism: " + organismName);
	                    continue;
	                }
	                ReproductionType reproductionType;
	                try {
	                	reproductionType =  ReproductionType.valueOf(parts[5].toUpperCase());
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: Invalid diet type for organism: " + organismName);
	                    continue;
	                }

	                double growthRate = Double.parseDouble(parts[6]);
	                String habitat = parts[7];
	                int ID = Integer.parseInt(parts[8]);

	                Environment env = Environment.getEnvironmentByIDAndLocation(habitat.toLowerCase(), ID, "Environment.txt");

	                // Instantiate based on the type field
	                switch (type.toLowerCase()) {
	                    case "fish":
	                    	/*public SubMarineFish(String nameOfOrganism, int avgAge, String size, String type, DietType dietType,
			ReproductionType reproductionType, double reproductionRate, Environment livingEnvironment) {
		super(nameOfOrganism, avgAge, size, type, dietType, reproductionType, reproductionRate, livingEnvironment);
		
	}*/
	                        return new SubMarineFish(name, avgAge, size, type, diet,reproductionType, growthRate, env);
	                    case "plankton":
	                        return new SubMarinePlankton(name, avgAge, size, type, diet, reproductionType, growthRate, env);
	                    case "corals":
	                        return new SubMarineCorals(name, avgAge, size, type, diet, reproductionType, growthRate, env);
	                    default:
	                        throw new IllegalArgumentException("Unknown type: " + type);
	                }
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading Marine Organisms file: " + e.getMessage());
	    } catch (NumberFormatException e) {
	        System.out.println("Error parsing numeric value in Marine Organisms file: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	    return null;
	}



}
