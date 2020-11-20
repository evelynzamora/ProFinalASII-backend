package gt.com.clinica.clinicamedica.service;

import gt.com.clinica.clinicamedica.entity.PersonEntity;

import java.util.List;

public interface ICrudPatient {
    public List<PersonEntity> listAll();
    public boolean deletepatient(int id);
    public boolean updatepatient(PersonEntity patient);
    public boolean addepatient(PersonEntity patient);
}
