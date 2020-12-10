
import java.util.ArrayList;
import java.util.List;

public class AddMission {
    List<String> Addmissions=new ArrayList<String>();
    int addmissionNo;
    int patientNo;

    public AddMission(List<String> addmissions, int addmissionNo, int patientNo) {
        Addmissions = addmissions;
        this.addmissionNo = addmissionNo;
        this.patientNo = patientNo;
    }
}
