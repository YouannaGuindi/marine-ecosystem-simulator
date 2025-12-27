public class SubNaturalWaterConditions extends SubThreatsNatural {

    public SubNaturalWaterConditions(double temp, double salinity, double depth, String ebb,
                                     MarineOrganism[] scannedOrganisms, double oilSpill, double[] actualFishing, SubMarineFish[] fishedFish,
                                     Environment e) {
        super(temp, salinity, depth, ebb, scannedOrganisms, oilSpill, actualFishing, fishedFish, e);
    }

    public boolean findTempThreat() {
        return getTemp() <= getE().getTemp() - 10 || getTemp() >= getE().getTemp() + 10;
    }

    public boolean findSalinityThreat() {
        return getSalinity() <= getE().getSalinity() - 10 || getSalinity() >= getE().getSalinity() + 10;
    }

    public String wrongLocationThreat() {
        StringBuilder wrongLocation = new StringBuilder();
        for (MarineOrganism organism : getScannedOrganisms()) {
            if (!organism.getLivingEnvironment().equals(getE())) {
                wrongLocation.append("Organism " + organism.getNameOfOrganism() + " found in wrong location, Its suitable environment is "
                        + organism.getLivingEnvironment().getLocation() + " (ID: " + organism.getLivingEnvironment().getEnvID() + ").\n");
            }
        }
        return wrongLocation.toString().trim();
    }

    public boolean tideThreat() {
        return getEbb().equals("extremely above average") || getEbb().equals("extremely below average");
    }

    public String findWaterConditionsthreat() {
        StringBuilder waterConditionsThreats = new StringBuilder();

        if (findTempThreat()) {
        	System.out.println("\n");
            waterConditionsThreats.append("\nTemperature not suitable.\n");
        }
        if (findSalinityThreat()) {
        	System.out.println("\n");
            waterConditionsThreats.append("\nSalinity not suitable.\n");
        }
        String wrongLocation = wrongLocationThreat();
        if (!wrongLocation.isEmpty()) {
        	System.out.println("\n");
            waterConditionsThreats.append(wrongLocation);
        }
        if (tideThreat()) {
        	System.out.println("\n");
            waterConditionsThreats.append("\nEbb or flow conditions are abnormal.\n");
        }
        if (waterConditionsThreats.length() == 0) {
        	System.out.println("\n");
            waterConditionsThreats.append("\nWater conditions are suitable.\n");
        }
        return waterConditionsThreats.toString();
    }
}
