package com.boot.chapter03.service;

import com.boot.chapter03.entity.AddressEntity;
import com.boot.chapter03.model.AddAddressReq;

import java.util.Optional;

public interface AddressService {
    Optional<AddressEntity> createAddress(AddAddressReq addAddressReq);

    void deleteAddressesById(String id);

    Optional<AddressEntity> getAddressesById(String id);

    Iterable<AddressEntity> getAllAddresses();
}
