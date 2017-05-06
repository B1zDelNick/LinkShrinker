package com.example.linkshrinker.service

interface RedirectService
{
    interface Add
    {
        data class Success(val key: String, val link: String): Add
    }

    interface Get
    {
        data class Link(val link: String): Get
    }
}