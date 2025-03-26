package com.boot.chapter03.hateoas;

import com.boot.chapter03.controllers.AddressController;
import com.boot.chapter03.entity.AddressEntity;
import com.boot.chapter03.model.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AddressRepresentationModelAssembler extends RepresentationModelAssemblerSupport<AddressEntity, Address> {

    public AddressRepresentationModelAssembler() {
        super(AddressController.class, Address.class);
    }

    @Override
    public Address toModel(AddressEntity entity) {
        Address resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setId(entity.getId().toString());

        resource.add(
                linkTo(methodOn(AddressController.class).getAddressesById(entity.getId().toString()))
                        .withSelfRel());
        return resource;
    }

    public List<Address> toListModel(Iterable<AddressEntity> entities) {
        if(Objects.isNull(entities)){
            return List.of();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(this::toModel).collect(toList());
    }
}
