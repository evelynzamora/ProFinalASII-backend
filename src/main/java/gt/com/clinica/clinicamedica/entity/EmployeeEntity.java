package gt.com.clinica.clinicamedica.entity;

public class EmployeeEntity extends PersonEntity{
    private int idEmpmloyee;
    private int idJob;
    private String job;

    public int getIdEmpmloyee() {
        return idEmpmloyee;
    }

    public void setIdEmpmloyee(int idEmpmloyee) {
        this.idEmpmloyee = idEmpmloyee;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;

    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
