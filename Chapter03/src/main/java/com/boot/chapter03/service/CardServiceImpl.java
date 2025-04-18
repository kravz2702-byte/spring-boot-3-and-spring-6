package com.boot.chapter03.service;

import com.boot.chapter03.entity.CardEntity;
import com.boot.chapter03.entity.UserEntity;
import com.boot.chapter03.model.AddCardReq;
import com.boot.chapter03.repository.CardRepository;
import com.boot.chapter03.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final UserRepository userRepo;

    public CardServiceImpl(CardRepository repository, UserRepository userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    @Override
    public void deleteCardById(String id){
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Iterable<CardEntity> getAllCards(){
        return repository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardById(String id){
        return repository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq) {
        return Optional.of(repository.save(toEntity(addCardReq)));
    }

    private CardEntity toEntity(AddCardReq m) {
        CardEntity e = new CardEntity();
        Optional<UserEntity> user = userRepo.findById(UUID.fromString(m.getUserId()));
        user.ifPresent(e::setUser);
        return  e.setNumber(m.getCardNumber()).setCvv(m.getCvv())
                .setExpires(m.getExpires());
    }

}
