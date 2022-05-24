package com.yaksonn.marveltutorial.core.cache.mapper.base

/**
 * Base Entity Mapper interface
 * that maps [E] to [T] or vice versa.
 */
interface EntityMapper<E, T> {

    fun mapFromEntity(entity: E): T

    fun mapToEntity(type: T): E

    fun mapTypeList(entities: List<E>?): List<T>? {
        return entities?.mapTo(mutableListOf(), ::mapFromEntity)
    }

    fun mapToEntityList(types: List<T>?): List<E>? {
        return types?.mapTo(mutableListOf(), ::mapToEntity)
    }
}