public class SubHumanOverfishing extends SubThreatsHuman {

    public SubHumanOverfishing(double temp, double salinity, double depth, String ebb,
                               MarineOrganism[] scannedOrganisms, double oilSpill, double[] actualFishing, SubMarineFish[] fishedFish,
                               Environment e) {
        super(temp, salinity, depth, ebb, scannedOrganisms, oilSpill, actualFishing, fishedFish, e);
    }

    public String checkFishing() {
        StringBuilder overfishingThreats = new StringBuilder();
        for (int i = 0; i < getFishedFish().length; i++) {
            double actualRate = getActualFishing()[i];
            double reproductionRate = getFishedFish()[i].getReproductionRate();
            if (actualRate < reproductionRate) {
            	System.out.println("\n");
                overfishingThreats.append("\nOverfishing occurred, " + getFishedFish()[i].getNameOfOrganism() + " is endangered.\n");
            } else {
            	System.out.println("\n");
                overfishingThreats.append("\nNormal fishing rate for " + getFishedFish()[i].getNameOfOrganism() + ".\n");
            }
        }
        return overfishingThreats.toString().trim();
    }
}