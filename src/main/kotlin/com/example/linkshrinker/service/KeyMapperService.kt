package com.example.linkshrinker.service

interface KeyMapperService
{
    interface Add
    {
        data class Success(val key: String, val link: String) : Add
        data class AlreadyExists(val key: String) : Add
    }

    interface Get
    {
        data class Link(val link: String) : Get
        data class NotFound(val key: String) : Get
    }

    fun addKey(key: String, link: String): Add
    fun getLink(link: String): Get
}