package gt.com.clinica.clinicamedica.entity;

import java.util.Date;

public class MedicineEntity {
    private int idMedicine;
    private String name;
    private String lab;
    private String adminway;
    private String description;
    private Date expirationdate;
    private int lots;

    public int getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(int idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getAdminway() {
        return adminway;
    }

    public void setAdminway(String adminway) {
        this.adminway = adminway;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public int getLots() {
        return lots;
    }

    public void setLots(int lots) {
        this.lots = lots;
    }
}
