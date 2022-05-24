package com.yaksonn.marveltutorial.mapper

/**
 * Base Remote Model Mapper
 * that maps [M] to [D]
 */
public interface RemoteModelMapper<in M, out D> {
    public fun mapFromModel(model: M): D

    public fun mapModelList(models: List<M>): List<D> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }
}