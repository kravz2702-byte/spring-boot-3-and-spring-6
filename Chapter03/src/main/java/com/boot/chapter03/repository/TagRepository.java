package com.boot.chapter03.repository;

import com.boot.chapter03.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
