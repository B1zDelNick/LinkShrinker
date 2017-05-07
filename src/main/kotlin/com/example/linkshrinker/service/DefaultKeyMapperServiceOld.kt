package com.example.linkshrinker.service

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class DefaultKeyMapperServiceOld : KeyMapperServiceOld
{
    private val map : MutableMap<String, String> = ConcurrentHashMap()

    override fun addKey(key: String, link: String): KeyMapperServiceOld.Add
    {
        if (map.containsKey(key))
            return KeyMapperServiceOld.Add.AlreadyExists(key)

        map.put(key, link)

        return KeyMapperServiceOld.Add.Success(key, link)
    }

    override fun getLink(key: String): KeyMapperServiceOld.Get
    {
        if (map.containsKey(key))
            return KeyMapperServiceOld.Get.Link(map[key]!!)

        return KeyMapperServiceOld.Get.NotFound(key)
    }
}