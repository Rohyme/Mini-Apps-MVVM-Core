package com.rohyme.core.domain.base

// Created by Rohyme on 10/23/2018.
interface BaseRepositoryI {
    fun getRemote(): BaseRemoteI?{
        return null
    }
    fun getCache(): BaseCacheI?{
        return null
    }
}