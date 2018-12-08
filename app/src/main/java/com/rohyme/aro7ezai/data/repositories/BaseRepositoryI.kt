package com.rohyme.aro7ezai.data.repositories

import com.rohyme.aro7ezai.data.cacheData.BaseCacheI
import com.rohyme.aro7ezai.data.remoteData.BaseRemoteI

// Created by Rohyme on 10/23/2018.
interface BaseRepositoryI {
    fun getRemote():BaseRemoteI?{
        return null
    }
    fun getCache(): BaseCacheI?{
        return null
    }
}