package gt.com.clinica.clinicamedica.service;

import gt.com.clinica.clinicamedica.entity.EmployeeEntity;

import java.util.List;

public interface ICrudEmployee {
    public List<EmployeeEntity> listAll();
    public boolean deleteemployee(int id);
    public boolean updateemployee(EmployeeEntity emp);
    public boolean addemployee(EmployeeEntity emp);
}
