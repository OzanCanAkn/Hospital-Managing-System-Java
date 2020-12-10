import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DataAccessObject {
    public static List<Patient> patients=new ArrayList<Patient>();
    public static List<Integer> IDS=new ArrayList<Integer>();
    public static List<AddMission> addmissions=new ArrayList<AddMission>();
    public static void createPatients(){
        String[] allPatients=readfile("patient.txt");
        for (int i=0;i<allPatients.length;i++){

            Patient p=new Patient(allPatients[i].split("\t")[0],allPatients[i].split("\t")[1].split(" ")[1],
                    allPatients[i].split("\t")[1].split(" ")[0],allPatients[i].split("\t")[3],allPatients[i].split("\t")[2]);
            patients.add(p);
            IDS.add(Integer.parseInt(allPatients[i].split("\t")[0]));
        }
        try
        {
            File f= new File("patient.txt");           //file to be delete
            f.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void addMissionCreate() {
        String[] missions = readfile("admission.txt");
        int patientNo=0;
        int addmissionNo=0;
        ArrayList<ArrayList<String>> s = new ArrayList<ArrayList<String>>();
        int counter=0;
        for (int j = 0; j < missions.length; j++) {

            if (isInteger(missions[j].split("\t")[0])) {
                patientNo=Integer.parseInt(missions[j].split("\t")[1]);
                addmissionNo=Integer.parseInt(missions[j].split("\t")[0]);


            } else {
                s.add(new ArrayList<String>());
                s.get(counter).add(missions[j]);
            }if(j+1==missions.length) {

                AddMission aa=new AddMission(s.get(counter),addmissionNo,patientNo);

                addmissions.add(aa);
                counter++;
                break;
            }if(isInteger(missions[j+1].split("\t")[0])){

                AddMission aa=new AddMission(s.get(counter),addmissionNo,patientNo);

                addmissions.add(aa);
                counter++;

            }
        }
        try
        {
            File f= new File("admission.txt");           //file to be delete
            f.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void addMissionUpdate(int addmissionNum,int patientNum){
        List<String> list=new ArrayList<String>();
        AddMission add=new AddMission(list,addmissionNum,patientNum);

        for(AddMission adm:addmissions){
            if(adm.addmissionNo>addmissionNum){
                addmissions.add(addmissions.indexOf(adm),add);
                break;
            }else if (adm.addmissionNo == addmissions.get(addmissions.size()-1).addmissionNo){
                addmissions.add(addmissions.indexOf(adm),add);
                break;
            }

        }
    }
    public static void addExamination(int addmissionNum,String line){
        for(AddMission adm:addmissions){
            if(adm.addmissionNo==addmissionNum){
                adm.Addmissions.add(line);
            }
        }
    }
    public static AddMission getAddmission(int addmissionNum){
        AddMission send=null;
        for(AddMission adm:addmissions){

            if(adm.addmissionNo==addmissionNum){
                send=adm;

            }
        }
        return send;
    }

    public static void update(Patient patient){
          int x=Integer.parseInt(patient.patient_id);
          for(int s:IDS){
              if(IDS.get(IDS.size()-1)==s){
                  patients.add(patient);
                  IDS.add(x);
                  break;
              }
              else if(x<s) {
                  patients.add(IDS.indexOf(s), patient);
                  IDS.add(IDS.indexOf(s), x);
                  break;
              }
          }
    }
    public static Patient delete(int removeN){
        Patient w=null;
        for(Patient p:patients){
            if(Integer.parseInt(p.patient_id)==removeN){
                IDS.remove(IDS.indexOf(Integer.parseInt(p.patient_id)));
                patients.remove(p);
                w= p;
                break;
            }
        }
        List<AddMission> cet=new ArrayList<AddMission>();
        for(AddMission add:addmissions){
            if(add.patientNo==removeN){
                continue;
            }else{
                cet.add(add);
            }
        }
        addmissions.clear();
        addmissions=cet;
        return w;
    }
    public static void write(String Data,String path) {
        try {

            FileWriter writer = new FileWriter(path, true);

            writer.write(Data);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void UpdateFiles(){
        ArrayList<Integer> con=new ArrayList<>();
        String write="";
        for(Patient p:patients){
           write(p.patient_id+"\t"+p.patient_name+" "+p.patient_surname+"\t"+p.patient_phone_number+"\t"+p.patient_address,"patient.txt");
        }
        for(AddMission b:addmissions) {
            con.add(b.addmissionNo);
        }
        Collections.sort(con);
        for (Integer c:con) {

            for (AddMission a : addmissions) {
                if (c==a.addmissionNo) {
                    write(a.addmissionNo + "\t" + a.patientNo, "admission.txt");
                    for (String q : a.Addmissions) {

                        write += q;
                        if (q != a.Addmissions.get(a.Addmissions.size() - 1)) {
                            write += "\n";
                        } else {
                            write(write, "admission.txt");
                        }
                    }
                    write = "";
                }
            }
        }
    }
    public static String[] readfile(String path) {
        try {
            int i = 0;
            int len = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[len];
            for(String line :Files.readAllLines(Paths.get(path))){
                results[i++]=line;
            }
            return results;
        }catch (IOException e){
            e.printStackTrace();
            return  null;
        }
    }
    public static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
