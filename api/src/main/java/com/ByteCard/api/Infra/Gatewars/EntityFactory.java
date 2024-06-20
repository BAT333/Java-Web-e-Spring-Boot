package com.ByteCard.api.Infra.Gatewars;

public interface EntityFactory<T,E> {
    T toDomain(E e);
    E toEntity(T t);
}
