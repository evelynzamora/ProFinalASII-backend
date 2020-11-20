package gt.com.clinica.clinicamedica.service;

import gt.com.clinica.clinicamedica.entity.MedicineEntity;

import java.util.List;

public interface ICrudMedicine {
    public List<MedicineEntity> listAll();
    public boolean deletemedicine(int id);
    public boolean updatemedicine(MedicineEntity med);
    public boolean addmedicine(MedicineEntity med);
}
