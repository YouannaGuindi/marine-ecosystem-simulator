
public class PotentialThreats2 {
	
	private double temp;//water conditions
	private double salinity;//water conditions
	private double depth; //water conditions (SubNaturalThreats)
	
	private String ebb;
	private MarineOrganism[] scannedOrganisms;//when reading from the file when i =4 we will open a loop that fills the array and checks for "end of organisms line" to break when it finds it 
	private double oilSpill; //Human Pollution (SubHumanPollution)
    private final double acceptableOilSpill = 0.2;//Human Pollution (SubHumanPollution)
    
    
    private double[] actualFishing; //Human Over fishing (SubHumanOverfishing)
    private SubMarineFish[] fishedFish; //Human Over fishing (SubHumanOverfishing)
    private Environment e;

	//StringBuilder threats = new StringBuilder();
    StringBuilder NaturalThreats = new StringBuilder ();
    StringBuilder HumanThreats = new StringBuilder ();
    
	
	//we need to create all objects of the subsub classes 
	

	public PotentialThreats2(double temp, double salinity, double depth, String ebb, MarineOrganism[] scannedOrganisms,
			double oilSpill, double[] actualFishing, SubMarineFish[] fishedFish, Environment e) {
		super();
		this.temp = temp;
		this.salinity = salinity;
		this.depth = depth;
		this.ebb = ebb;
		this.scannedOrganisms = scannedOrganisms;
		this.oilSpill = oilSpill;
		this.actualFishing = actualFishing;
		this.fishedFish = fishedFish;
		this.e = e;
	}
	public MarineOrganism[] getScannedOrganisms() {
		return scannedOrganisms;
	}

	public void setScannedOrganisms(MarineOrganism[] scannedOrganisms) {
		this.scannedOrganisms = scannedOrganisms;
	}

	public String getEbb() {
		return ebb;
	}

	public void setEbb(String ebb) {
		this.ebb = ebb;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getSalinity() {
		return salinity;
	}

	public void setSalinity(double salinity) {
		this.salinity = salinity;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public double getOilSpill() {
		return oilSpill;
	}

	public void setOilSpill(double oilSpill) {
		this.oilSpill = oilSpill;
	}

	public double getAcceptableOilSpill() {
		return acceptableOilSpill;
	}

	public double[] getActualFishing() {
		return actualFishing;
	}


	public void setActualFishing(double[] actualFishing) {
		this.actualFishing = actualFishing;
	}

	public SubMarineFish[] getFishedFish() {
		return fishedFish;
	}


	public void setFishedFish(SubMarineFish[] fishedFish) {
		this.fishedFish = fishedFish;
	}
	
	public Environment getE() {
		return e;
	}

	public void setE(Environment e) {
		this.e = e;
	}
	
	public String findNaturalThreat() {
		return null;
	}
	public String findHumanThreat() {
		return null;
	}
	

	public String findthreat(SubThreatsHuman h,SubThreatsHuman r, SubThreatsNatural n) {
	    // Ensure the objects passed are of the correct types
	    SubHumanPollution humanPollution = (SubHumanPollution) h;
	    SubHumanOverfishing humanOverfishing = (SubHumanOverfishing) r;
	    SubNaturalWaterConditions waterConditions = (SubNaturalWaterConditions) n;

	    StringBuilder threatsSummary = new StringBuilder();
	    try {
	        threatsSummary.append(h.findHumanThreat(humanPollution, humanOverfishing));
	        threatsSummary.append(n.findNaturalThreat(waterConditions));
	    } catch (Exception e) {
	        System.out.println("Error evaluating threats: " + e.getMessage());
	    }
	    return threatsSummary.toString().trim().replaceAll("\\.", ".\n");
	}
}
