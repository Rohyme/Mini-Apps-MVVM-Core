package com.rohyme.aro7ezai.domain.base

// Created by Rohyme on 10/24/2018.
interface BaseMapper<E, T> {
    fun fromEntity(e: E): T
    fun toEntity(t: T): E
}