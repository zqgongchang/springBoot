package com.atisz.springBoot.mapper;

import com.atisz.springBoot.entity.UserEntity;

import java.util.List;

public interface UserMapper_xml {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);
}
