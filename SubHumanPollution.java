public class SubHumanPollution extends SubThreatsHuman {

    public SubHumanPollution(double temp, double salinity, double depth, String ebb, MarineOrganism[] scannedOrganisms,
                             double oilSpill, double[] actualFishing, SubMarineFish[] fishedFish, Environment e) {
        super(temp, salinity, depth, ebb, scannedOrganisms, oilSpill, actualFishing, fishedFish, e);
    }

    public String checkPollution() {
        StringBuilder pollutionThreats = new StringBuilder();
        if (getOilSpill() <= getAcceptableOilSpill()) {
        	System.out.println("\n");
            pollutionThreats.append("Oil spill within accepted range.\n");
        } else {
        	System.out.println("\n");
            pollutionThreats.append("Excessive oil spill.\n");
        }
        return pollutionThreats.toString();
    }
}