package com.lmr.factory

interface IFactory<T, R> {
    fun create(data: T): R
}