package com.woc.daoimpl.assembler;

import com.woc.dto.AddressDTO;
import com.woc.dto.EmployeeDTO;
import com.woc.hibernate.model.Address;
import com.woc.hibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeAssembler {
    @Autowired
    private AddressAssembler addressAssembler;

    public Employee toDomain(EmployeeDTO dto){
        Employee e = new Employee();
        if(dto.getId() != null){
            e.setId(dto.getId());
        }

        e.setName(dto.getName());
        e.setEmployeeNo(dto.getEmployeeNo());
        e.setEmail(dto.getEmail());
        e.setMobile(dto.getMobile());
        e.setDateOfJoining(dto.getDateOfJoining());
        e.setActive(dto.isActive());

        List<Address> addresses = addressAssembler.toDomain(dto.getAddressDTOS());
        e.setAddress(addresses);
        return e;
    }

    public List<EmployeeDTO> fromDomain(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new LinkedList<>();
        for(Employee e : employees){
            employeeDTOS.add(fromDomain(e));
        }
        return employeeDTOS;
    }

    public EmployeeDTO fromDomain(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        if(employee.getId() != null){
            dto.setId(employee.getId());
        }

        dto.setName(employee.getName());
        dto.setEmployeeNo(employee.getEmployeeNo());
        dto.setEmail(employee.getEmail());
        dto.setMobile(employee.getMobile());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setActive(employee.isActive());

        List<AddressDTO> addressDTOS = addressAssembler.fromDomain(employee.getAddress());
        dto.setAddressDTOS(addressDTOS);
        return dto;
    }
}
