package com.boot.chapter03.service;

import com.boot.chapter03.entity.CardEntity;
import com.boot.chapter03.model.AddCardReq;
import jakarta.validation.Valid;

import java.util.Optional;

public interface CardService {
    void deleteCardById(String id);
    Iterable<CardEntity> getAllCards();
    Optional<CardEntity> getCardById(String id);
    Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq);
}
