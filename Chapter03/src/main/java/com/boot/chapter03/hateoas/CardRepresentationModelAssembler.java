package com.boot.chapter03.hateoas;

import com.boot.chapter03.controllers.CardController;
import com.boot.chapter03.entity.CardEntity;
import com.boot.chapter03.model.Card;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CardRepresentationModelAssembler  extends RepresentationModelAssemblerSupport<CardEntity, Card> {

    public CardRepresentationModelAssembler() {
        super(CardController.class, Card.class);
    }

    @Override
    public Card toModel(CardEntity entity) {
        String uid = Objects.nonNull(entity.getUser()) ? entity.getUser().getId().toString() : null;
        Card resource = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.id(entity.getId().toString()).cardNumber(entity.getNumber())
                .cvv(entity.getCvv()).expires(entity.getExpires()).userId(uid);

        try {
            resource.add(linkTo(methodOn(CardController.class).getCardById(entity.getId().toString())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resource;
    }

    public List<Card> toListModel(Iterable<CardEntity> entities) {
        if(Objects.nonNull(entities)) return List.of();
        return StreamSupport.stream(entities.spliterator(), false).map(this::toModel).collect(toList());
    }
}
