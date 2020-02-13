package com.woc.service;

import com.woc.dao.EmployeeDao;
import com.woc.dto.AddressDTO;
import com.woc.dto.EmployeeDTO;
import com.woc.exception.AddressNotFoundException;
import com.woc.exception.PartialInputDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        validateEmployee(employee);
        return employeeDao.createEmployee(employee);
    }

    private void validateEmployee(EmployeeDTO employee) {
        String email = employee.getEmail();
        if(null == email || email.trim().length() == 0){
            throw new PartialInputDataException("email is mandatory for creating an employee", employee);
        }

        boolean isValidEmail = Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", email);
        if(!isValidEmail){
            throw new IllegalArgumentException("invalid email="+email+" for creating an employee, please provide the correct format of email id");
        }

        Long mobile = employee.getMobile();
        if(null == mobile){
            throw new PartialInputDataException("mobile is mandatory for creating an employee", employee);
        }

        boolean isValidTenDigitsMobile = Pattern.matches("[9876]{1}[0-9]{9}", String.valueOf(mobile));
        if(!isValidTenDigitsMobile){
            throw new IllegalArgumentException("invalid mobile number="+mobile+" for creating an employee, " +
                    "please provide ten digits mobile number starting with either 9 or 8 or 7 or 6");
        }

    }

    public EmployeeDTO fetchEmployee(String employeeNo){
        return employeeDao.fetchEmployee(employeeNo);
    }

    public List<EmployeeDTO> fetchEmployeeByName(String name){
        List<EmployeeDTO> employeeDTOS = employeeDao.fetchEmployeeByName(name);
        return employeeDTOS;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        return employeeDao.updateEmployee(employee);
    }

    public EmployeeDTO updateEmployeeEmail(String employeeNo, String email) {
        return employeeDao.updateEmployeeEmail(employeeNo, email);
    }

    public void deleteEmployee(String employeeNo) {
        employeeDao.deleteEmployee(employeeNo);
    }

    public List<AddressDTO> fetchEmployeeAddresses(String employeeNo) {
        List<AddressDTO> addressDTOS = employeeDao.fetchEmployeeAddresses(employeeNo);
        return addressDTOS;
    }

    public AddressDTO fetchEmployeeAddressByAddressId(String employeeNo, Integer addressId) {
        List<AddressDTO> addressDTOS = employeeDao.fetchEmployeeAddresses(employeeNo);
        AddressDTO addressDTO = addressDTOS.stream().filter(a -> addressId.equals(a.getId())).findFirst().orElseThrow(()->new AddressNotFoundException(addressId));
        return addressDTO;
    }

    public AddressDTO addAddressForEmployee(String employeeNo, AddressDTO address) {
        AddressDTO addressDTO = employeeDao.addAddressForEmployee(employeeNo, address);
        return addressDTO;
    }

}
