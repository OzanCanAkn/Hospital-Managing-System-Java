public class Measurement extends Decorator {
    public Measurement(Examination e) {
        super(e);
    }
    public Integer addCost() {
        return 5+super.addCost();
    }
}
