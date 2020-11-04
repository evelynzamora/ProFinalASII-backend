package gt.com.clinica.clinicamedica.service;

import gt.com.clinica.clinicamedica.entity.MedicineEntity;

import java.util.List;

public interface ICrudMedicine {
    public List<MedicineEntity> listAll();
    public int deletemedicine(int id);
    public int updatemedicine(MedicineEntity med);
    public int addmedicine(MedicineEntity med);
}
