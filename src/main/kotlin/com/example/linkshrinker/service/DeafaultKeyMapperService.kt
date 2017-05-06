package com.example.linkshrinker.service

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class DeafaultKeyMapperService : KeyMapperService
{
    private val map : MutableMap<String, String> = ConcurrentHashMap()

    override fun addKey(key: String, link: String): KeyMapperService.Add
    {
        if (map.containsKey(key))
            return KeyMapperService.Add.AlreadyExists(key)

        map.put(key, link)

        return KeyMapperService.Add.Success(key, link)
    }

    override fun getLink(key: String): KeyMapperService.Get
    {
        if (map.containsKey(key))
            return KeyMapperService.Get.Link(map[key]!!)

        return KeyMapperService.Get.NotFound(key)
    }
}