public class DoctorVisit extends  Decorator {
    public DoctorVisit(Examination e) {
        super(e);
    }
    public Integer addCost() {
        return 15+super.addCost();
    }
}
