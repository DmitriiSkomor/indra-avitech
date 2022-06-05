package com.indra.avitech.producerconsumer.service;

public interface IProducer {
    void add(Long userId, String userGUID, String userName);
    void printAll();
    void deleteAll();
}
