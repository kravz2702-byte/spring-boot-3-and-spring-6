package com.boot.chapter03.controllers;

import com.boot.chapter03.AddressApi;
import com.boot.chapter03.hateoas.AddressRepresentationModelAssembler;
import com.boot.chapter03.model.AddAddressReq;
import com.boot.chapter03.model.Address;
import com.boot.chapter03.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

public class AddressController implements AddressApi {
    private final AddressService service;
    private final AddressRepresentationModelAssembler assembler;

    public AddressController(AddressService addressService, AddressRepresentationModelAssembler assembler) {
        this.service = addressService;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Address> createAddress(@Valid AddAddressReq addAddressReq) {
        return status(HttpStatus.CREATED).body(service.createAddress(addAddressReq)
                .map(assembler::toModel).get());
    }

    @Override
    public ResponseEntity<Void> deleteAddressesById(String id) {
        service.deleteAddressesById(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Address> getAddressesById(String id) {
        return service.getAddressesById(id).map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ok(assembler.toListModel(service.getAllAddresses()));
    }
}
