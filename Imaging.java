public class Imaging extends Decorator {
    public Imaging(Examination e) {
    super(e);
    }
    public Integer addCost() {
        return 10+super.addCost();
    }
}
