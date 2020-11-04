package gt.com.clinica.clinicamedica.service;

import gt.com.clinica.clinicamedica.entity.EmployeeEntity;

import java.util.List;

public interface ICrudEmployee {
    public List<EmployeeEntity> listAll();
    public int deleteemployee(int id);
    public int updateemployee(EmployeeEntity emp);
    public int addemployee(EmployeeEntity emp);
}
