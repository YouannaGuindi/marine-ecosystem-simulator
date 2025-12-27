public class SubThreatsHuman extends PotentialThreats2 {

    public SubThreatsHuman(double temp, double salinity, double depth, String ebb, MarineOrganism[] scannedOrganisms,
                           double oilSpill, double[] actualFishing, SubMarineFish[] fishedFish, Environment e) {
        super(temp, salinity, depth, ebb, scannedOrganisms, oilSpill, actualFishing, fishedFish, e);
    }

    public String findHumanThreat(SubHumanPollution p, SubHumanOverfishing o) {
        return p.checkPollution() + o.checkFishing();
    }
}
