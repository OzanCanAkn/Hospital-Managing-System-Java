public class Decorator implements Examination {
    private Examination ee;

    public Decorator(Examination ee) {
        this.ee = ee;
    }

    public Integer addCost() {
        return ee.addCost();
    }
}
