package com.example.linkshrinker.model

import org.springframework.data.repository.Repository
import java.util.*

interface LinkRepository: Repository<Link, Long>
{
    fun findOne(id: Long?): Optional<Link>
    fun save(link: Link): Link
}