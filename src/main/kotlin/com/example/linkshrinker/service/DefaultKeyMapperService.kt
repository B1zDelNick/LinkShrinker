package com.example.linkshrinker.service

import com.example.linkshrinker.model.Link
import com.example.linkshrinker.model.LinkRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultKeyMapperService : KeyMapperService
{
    @Autowired
    private lateinit var converter : KeyConverterService

    @Autowired
    private lateinit var repository: LinkRepository

    override fun addLink(link: String) =
        converter.idToKey(repository.save(Link(text = link)).id)

    override fun getLink(key: String): KeyMapperService.Get
    {
        val result = repository.findOne(converter.keyToId(key))

        return if (result.isPresent)
        {
            KeyMapperService.Get.Link(result.get().text)
        }
        else
        {
            KeyMapperService.Get.NotFound(key)
        }
    }
}