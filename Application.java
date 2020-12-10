import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

public class Application {
    public static void input(String x){
        String[] inputs=DataAccessObject.readfile(x);
        for(String k:inputs){
            if(k.split(" ")[0].equals("AddPatient")){
                String[] list= k.split(" ");
                String Addres="";
                for(int m=5;m<list.length;m++){
                    Addres=Addres+list[m]+" ";
                }
                Patient p=new Patient(k.split(" ")[1],k.split(" ")[3],k.split(" ")[2],Addres,k.split(" ")[4]);
                DataAccessObject.update(p);
                DataAccessObject.write("Patient "+k.split(" ")[1]+" "+k.split(" ")[2]+" added","output.txt");
            }else if(k.split(" ")[0].equals("RemovePatient")){
                Patient j=DataAccessObject.delete(Integer.parseInt(k.split(" ")[1]));
                DataAccessObject.write("Patient "+j.patient_id+" "+j.patient_name+" removed","output.txt");
            }else if(k.split(" ")[0].equals("CreateAdmission")){
                DataAccessObject.addMissionUpdate(Integer.parseInt(k.split(" ")[1]),Integer.parseInt(k.split(" ")[2]));
                DataAccessObject.write("Admission "+k.split(" ")[1]+" created","output.txt");
            }else if(k.split(" ")[0].equals("AddExamination")){
                String[] list= k.split(" ");
                String regs=list[2]+"\t";
                for(int m=3;m<list.length;m++){
                    regs=regs+list[m]+" ";
                }
                String jegs=list[2]+" ";
                for(int q=3;q<list.length;q++){
                    jegs=jegs+list[q]+" ";
                }
                DataAccessObject.addExamination(Integer.parseInt(k.split(" ")[1]),regs);
                DataAccessObject.write(jegs+"added to admission "+k.split(" ")[1],"output.txt");
            }else if(k.split(" ")[0].equals("TotalCost")){
                AddMission adm=DataAccessObject.getAddmission(Integer.parseInt(k.split(" ")[1]));
                List<String> q=new ArrayList<String>();
                DataAccessObject.write("Total cost for admission "+k.split(" ")[1],"output.txt");
                int total=0;
                int allTotal=0;
                for (String t:adm.Addmissions){
                    for(String m:t.split("\\s+"))
                    {
                        q.add(m);
                    }
                    if(q.size()==3){
                        if (q.get(0).equals("Inpatient")){
                            if(q.contains("doctorvisit")&& q.contains("tests")){
                                Examination ex=new Tests(new DoctorVisit(new Inpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                                System.out.println("girdi "+total);
                            }else if(q.contains("doctorvisit")&& q.contains("measurements")){
                                Examination ex=new Measurement(new DoctorVisit(new Inpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("doctorvisit")&& q.contains("imaging")){
                                Examination ex=new Imaging(new DoctorVisit(new Inpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("measurements")&& q.contains("tests")){
                                Examination ex=new Measurement(new Tests(new Inpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("measurements")&& q.contains("imaging")){
                                Examination ex=new Imaging(new Measurement(new Inpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("imaging")&& q.contains("tests")){
                                Examination ex=new Tests(new Imaging(new Inpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }
                        }else if (q.get(0).equals("Outpatient")){
                            if(q.contains("doctorvisit")&& q.contains("tests")){
                                Examination ex=new Tests(new DoctorVisit(new Outpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("doctorvisit")&& q.contains("measurements")){
                                Examination ex=new Measurement(new DoctorVisit(new Outpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("doctorvisit")&& q.contains("imaging")){
                                Examination ex=new Imaging(new DoctorVisit(new Outpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(q.contains("measurements")&& q.contains("tests")){

                                Examination ex=new Measurement(new Tests(new Outpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                                System.out.println(ex.addCost());

                            }else if(q.contains("measurements")&& q.contains("imaging")){
                                Examination ex=new Imaging(new Measurement(new Outpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();

                            }else if(q.contains("imaging")&& q.contains("tests")){
                                Examination ex=new Tests(new Imaging(new Outpatient()));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }
                        }

                    }else if(q.size()==2) {
                        if (q.get(0).equals("Inpatient")) {
                            if (q.contains("doctorvisit")) {
                                Examination ex = new DoctorVisit(new Inpatient());
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            } else if (q.contains("measurements")) {
                                Examination ex = new Measurement(new Inpatient());
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            } else if (q.contains("tests")) {
                                Examination ex = new Tests(new Inpatient());
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            } else if (q.contains("imaging")) {
                                Examination ex = new Imaging(new Inpatient());
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }
                        } else if (q.get(0).equals("Outpatient")) {
                            if (q.get(0).equals("Inpatient")) {
                                if (q.contains("doctorvisit")) {
                                    Examination ex = new DoctorVisit(new Outpatient());
                                    DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                    total+=ex.addCost();
                                } else if (q.contains("measurements")) {
                                    Examination ex = new Measurement(new Outpatient());
                                    DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                    total+=ex.addCost();
                                } else if (q.contains("tests")) {
                                    Examination ex = new Tests(new Outpatient());
                                    DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                    total+=ex.addCost();
                                } else if (q.contains("imaging")) {
                                    Examination ex = new Imaging(new Outpatient());
                                    DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                    total+=ex.addCost();
                                }
                            }
                        }
                    }else if(q.size()==4){
                        if (q.get(0).equals("Inpatient")){
                            if(!q.contains("doctorvisit")){
                                Examination ex=new Tests(new Imaging(new Measurement(new Inpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(!q.contains("measurements")){
                                Examination ex=new DoctorVisit(new Imaging(new Tests(new Inpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(!q.contains("tests")){
                                Examination ex=new DoctorVisit(new Imaging(new Measurement(new Inpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(!q.contains("imaging")){
                                Examination ex=new DoctorVisit(new Tests(new Measurement(new Inpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }
                        }else if (q.get(0).equals("Outpatient")) {
                            if(!q.contains("doctorvisit")){
                                Examination ex=new Tests(new Imaging(new Measurement(new Outpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(!q.contains("measurements")){
                                Examination ex=new DoctorVisit(new Imaging(new Tests(new Outpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(!q.contains("tests")){
                                Examination ex=new DoctorVisit(new Imaging(new Measurement(new Outpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }else if(!q.contains("imaging")){
                                Examination ex=new DoctorVisit(new Tests(new Measurement(new Outpatient())));
                                DataAccessObject.write("\t"+t+" "+Integer.toString(ex.addCost())+"$","output.txt");
                                total+=ex.addCost();
                            }

                        }
                    }
                    q.clear();
                }
                DataAccessObject.write("\t"+"total: "+total+"$","output.txt");
                total=0;
            }else if(k.split(" ")[0].equals("ListPatients")){
                DataAccessObject.write("Patient List:","output.txt");
                List<String> names=new ArrayList<String>();
                for(int m=0;m<DataAccessObject.patients.size();m++){
                    names.add(DataAccessObject.patients.get(m).patient_name);
                }
                Collections.sort(names);
                for(String str:names){
                    for(int o=0;o<DataAccessObject.patients.size();o++){
                        if(DataAccessObject.patients.get(o).patient_name.equals(str)){
                            DataAccessObject.write(DataAccessObject.patients.get(o).patient_id+" "+DataAccessObject.patients.get(o).patient_name+" "+
                                    DataAccessObject.patients.get(o).patient_surname+" "+DataAccessObject.patients.get(o).patient_phone_number+" "+DataAccessObject.patients.get(o).patient_address
                            ,"output.txt");
                            break;
                        }
                    }
                }
            }
        }
        DataAccessObject.UpdateFiles();
    }
}
