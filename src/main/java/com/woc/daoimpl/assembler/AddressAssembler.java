package com.woc.daoimpl.assembler;

import com.woc.dto.AddressDTO;
import com.woc.hibernate.model.Address;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class AddressAssembler {

    public List<Address> toDomain(List<AddressDTO> dtos) {
        if(null == dtos){
            return Collections.EMPTY_LIST;
        }
        List<Address> addresses = new LinkedList<>();
        for(AddressDTO dto : dtos){
            addresses.add(toDomain(dto));
        }
        return addresses;
    }

    public Address toDomain(AddressDTO dto) {
        Address address = new Address();
        if (dto.getId() != null) {
            address.setId(dto.getId());
        }

        address.setLine1(dto.getLine1());
        address.setLine2(dto.getLine2());
        address.setType(dto.getType());
        address.setPin(dto.getPin());

        return address;
    }

    public List<AddressDTO> fromDomain(List<Address> addresses) {
        List<AddressDTO> dtos = new LinkedList<>();
        for(Address address : addresses){
            dtos.add(fromDomain(address));
        }
        return dtos;
    }
    public AddressDTO fromDomain(Address address) {
        AddressDTO dto = new AddressDTO();
        if (address.getId() != null) {
            dto.setId(address.getId());
        }

        dto.setLine1(address.getLine1());
        dto.setLine2(address.getLine2());
        dto.setType(address.getType());
        dto.setPin(address.getPin());

        return dto;
    }

}
