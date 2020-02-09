package com.woc.controller;

import com.woc.dto.AddressDTO;
import com.woc.dto.EmployeeDTO;
import com.woc.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Employee CRUD Operation")
public class EmployeeApi {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    @ApiOperation(value = "to create an employee in the system", notes = "This api is a valid restful service")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employee){
        EmployeeDTO employeeDTO = employeeService.createEmployee(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeNo}")
    @ApiOperation(value = "to fetch an employee by employeeNo", notes = "This api is a valid restful service")
    public ResponseEntity<?> fetchEmployeeByEmployeeNo(@PathVariable("employeeNo") String employeeNo){
        EmployeeDTO employeeDTO = employeeService.fetchEmployee(employeeNo);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/employeeByName")
    @ApiOperation(value = "to fetch all employee by employee name", notes = "This api is not a valid restful service")
    public ResponseEntity<?> fetchEmployeeByName(@RequestParam("name") String name){
        List<EmployeeDTO> employeeDTOS = employeeService.fetchEmployeeByName(name);
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @PutMapping("/employees")
    @ApiOperation(value = "to update an employee in the system", notes = "This api is a valid restful service")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employee){
        EmployeeDTO employeeDTO = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PatchMapping("/employees/{employeeNo}")
    @ApiOperation(value = "to update the email of an employee", notes = "This api is a valid restful service")
    public ResponseEntity<?> updateEmployeeEmail(@PathVariable("employeeNo") String employeeNo, @RequestBody String email){
        EmployeeDTO employeeDTO = employeeService.updateEmployeeEmail(employeeNo, email);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeNo}")
    @ApiOperation(value = "to delete an employee by employeeNo", notes = "This api is a valid restful service")
    public ResponseEntity<?> deleteEmployeeByEmployeeNo(@PathVariable("employeeNo") String employeeNo){
        employeeService.deleteEmployee(employeeNo);
        return new ResponseEntity<>("employeeNo="+employeeNo +", has been deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/employees/{employeeNo}/addresses")
    @ApiOperation(value = "to add the address of an employee by employeeNo", notes = "This api is a valid restful service, with HATEOAS")
    public ResponseEntity<?> addAddressForEmployee(@PathVariable("employeeNo") String employeeNo, @RequestBody AddressDTO address){
        AddressDTO addressDTO = employeeService.addAddressForEmployee(employeeNo, address);

        if (null == addressDTO){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addressDTO.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/employees/{employeeNo}/addresses")
    @ApiOperation(value = "to fetch all address of an employee by employeeNo", notes = "This api is a valid restful service")
    public ResponseEntity<?> fetchEmployeeAddresses(@PathVariable("employeeNo") String employeeNo){
        List<AddressDTO> addressDTOS = employeeService.fetchEmployeeAddresses(employeeNo);
        return new ResponseEntity<>(addressDTOS, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeNo}/addresses/{addressId}")
    @ApiOperation(value = "to fetch address of an employee by employeeNo and addressId", notes = "This api is a valid restful service")
    public ResponseEntity<?> fetchEmployeeAddresses(@PathVariable("employeeNo") String employeeNo, @PathVariable Integer addressId){
        AddressDTO addressDTO = employeeService.fetchEmployeeAddressByAddressId(employeeNo, addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

}
