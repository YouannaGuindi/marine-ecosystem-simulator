public class SubThreatsNatural extends PotentialThreats2 {

    public SubThreatsNatural(double temp, double salinity, double depth, String ebb, MarineOrganism[] scannedOrganisms,
                             double oilSpill, double[] actualFishing, SubMarineFish[] fishedFish, Environment e) {
        super(temp, salinity, depth, ebb, scannedOrganisms, oilSpill, actualFishing, fishedFish, e);
    }

    public String findNaturalThreat(SubNaturalWaterConditions w) {
        return w.findWaterConditionsthreat();
    }
}
