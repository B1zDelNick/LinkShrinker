package com.example.linkshrinker.controll

import com.example.linkshrinker.service.KeyMapperService
import com.example.linkshrinker.service.KeyMapperService.Get.Link
import com.example.linkshrinker.service.KeyMapperService.Get.NotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class RedirectController
{
    @Autowired
    lateinit var keyMapperService: KeyMapperService

    companion object
    {
        private const val HEADER_LOCATION = "Location"
    }

    @RequestMapping(value = "/{value}", method = arrayOf(RequestMethod.GET))
    fun redirect(@PathVariable value: String, respone: HttpServletResponse)
    {
        //println("Key:$value")

        val result = keyMapperService.getLink(value)

        //println("Result:$result")

        when (result)
        {
            is Link -> {
                respone.setHeader(HEADER_LOCATION, result.link)
                respone.status = 302
            }
            is NotFound -> {
                respone.status = 404
            }
        }
    }

    @GetMapping(value = "/add/{key}/{link}")
    fun addLink(@PathVariable key: String, @PathVariable link: String)
    {
        keyMapperService.addKey(key, link)
    }
}