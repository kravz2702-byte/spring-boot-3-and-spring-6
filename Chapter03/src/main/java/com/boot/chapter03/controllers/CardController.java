package com.boot.chapter03.controllers;

import com.boot.chapter03.CardApi;
import com.boot.chapter03.hateoas.CardRepresentationModelAssembler;
import com.boot.chapter03.model.AddCardReq;
import com.boot.chapter03.model.Card;
import com.boot.chapter03.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

public class CardController implements CardApi {

    private final CardService service;
    private final CardRepresentationModelAssembler assembler;

    public CardController(CardService service, CardRepresentationModelAssembler assembler){
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Void> deleteCardById(String id) {
        service.deleteCardById(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<Card>> getAllCards() {
        return ok(assembler.toListModel(service.getAllCards()));
    }

    @Override
    public ResponseEntity<Card> getCardById(String id){
        return service.getCardById(id).map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<Card> registerCard(@Valid AddCardReq addCardReq){
        return  status(HttpStatus.CREATED).body(service.registerCard(addCardReq).map(assembler::toModel).get());
    }

}
