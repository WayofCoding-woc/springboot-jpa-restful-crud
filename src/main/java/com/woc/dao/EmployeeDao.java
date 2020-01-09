package com.woc.dao;

import com.woc.dto.AddressDTO;
import com.woc.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeDao {
    EmployeeDTO createEmployee(EmployeeDTO e);
    EmployeeDTO fetchEmployee(String employeeNo);
    List<EmployeeDTO> fetchEmployeeByName(String name);
    EmployeeDTO updateEmployee(EmployeeDTO employee);
    EmployeeDTO updateEmployeeEmail(String employeeNo, String email);
    void deleteEmployee(String employeeNo);

    List<AddressDTO> fetchEmployeeAddresses(String employeeNo);
    AddressDTO addAddressForEmployee(String employeeNo, AddressDTO address);
}
