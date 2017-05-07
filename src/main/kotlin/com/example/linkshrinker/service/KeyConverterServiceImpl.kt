package com.example.linkshrinker.service

import org.springframework.stereotype.Component

@Component
class KeyConverterServiceImpl : KeyConverterService
{
    val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray()
    val charsMap = (0..chars.size - 1)
            .map { i ->  Pair(chars[i], i)}
            .toMap()

    override fun idToKey(id: Long): String
    {
        var n = id
        val builder = StringBuilder()

        while (n != 0L)
        {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }

        return builder.reverse().toString()
    }

    override fun keyToId(key: String) = key
            .map { c -> charsMap[c]!! }
            .fold(0L, {a, b -> a * chars.size + b})
}