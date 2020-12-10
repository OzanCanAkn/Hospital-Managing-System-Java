public class Tests extends Decorator {
    public Tests(Examination e) {
        super(e);
    }
    public Integer addCost() {
        return 7+super.addCost();
    }

}
