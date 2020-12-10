public class Main {
    public static void main(String[] args) {
        DataAccessObject.createPatients();
        DataAccessObject.addMissionCreate();
        Application.input(args[0]);
    }
}
