package com.woc.daoimpl;

import com.woc.dao.EmployeeDao;
import com.woc.daoimpl.assembler.AddressAssembler;
import com.woc.daoimpl.assembler.EmployeeAssembler;
import com.woc.dto.AddressDTO;
import com.woc.dto.EmployeeDTO;
import com.woc.exception.EmployeeNotFoundException;
import com.woc.hibernate.model.Address;
import com.woc.hibernate.model.Employee;
import com.woc.jpa.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeDaoImpl implements EmployeeDao {
    private final static Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeAssembler employeeAssembler;
    @Autowired
    private AddressAssembler addressAssembler;

    private static final String EMPLOYEE_NO_PREFIX = "woc-";

    @Transactional
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeAssembler.toDomain(employeeDTO);
        employeeRepository.save(employee);
        employee.setEmployeeNo(EMPLOYEE_NO_PREFIX + employee.getId());
        return employeeAssembler.fromDomain(employee);
    }

    @Override
    public EmployeeDTO fetchEmployee(String employeeNo) {
        Employee employee = getEmployee(employeeNo);
        EmployeeDTO employeeDTO = employeeAssembler.fromDomain(employee);
        return employeeDTO;
    }

    private Employee getEmployee(String employeeNo) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        if (null == employee) {
            throw new EmployeeNotFoundException("No such employee found for [employeeNo=" + employeeNo + "]");
        }
        return employee;
    }

    @Override
    public List<EmployeeDTO> fetchEmployeeByName(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        List<EmployeeDTO> employeeDTOS = employeeAssembler.fromDomain(employees);
        return employeeDTOS;
    }

    @Transactional
    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeDTO.getEmployeeNo());
        if(null == employee){
            return createEmployee(employeeDTO);
        }

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setMobile(employeeDTO.getMobile());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        employee.setActive(employeeDTO.isActive());
        employee.setAddress(addressAssembler.toDomain(employeeDTO.getAddressDTOS()));
        return employeeAssembler.fromDomain(employee);
    }

    @Transactional
    @Override
    public EmployeeDTO updateEmployeeEmail(String employeeNo, String email) {
        Employee employee = getEmployee(employeeNo);
        employee.setEmail(email);
        return employeeAssembler.fromDomain(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(String employeeNo) {
        Employee employee = getEmployee(employeeNo);
        employeeRepository.delete(employee);
    }

    @Override
    public List<AddressDTO> fetchEmployeeAddresses(String employeeNo) {
        Employee employee = getEmployee(employeeNo);
        List<Address> address = employee.getAddress();
        List<AddressDTO> addressDTOS = addressAssembler.fromDomain(address);
        return addressDTOS;
    }

    @Transactional
    @Override
    public AddressDTO addAddressForEmployee(String employeeNo, AddressDTO requestedAddressDTO) {
        Employee employee = getEmployee(employeeNo);
        Address address = addressAssembler.toDomain(requestedAddressDTO);
        employee.getAddress().add(address);

        Employee savedEmployee = employeeRepository.save(employee);

        Address newlyAddedAddress = savedEmployee.getAddress().get(savedEmployee.getAddress().size()-1);

        AddressDTO newlyAddedAddressDTO = addressAssembler.fromDomain(newlyAddedAddress);
        return newlyAddedAddressDTO;
    }

}
