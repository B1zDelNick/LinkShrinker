package com.example.linkshrinker.controll

import com.example.linkshrinker.service.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping("/api")
class RestController
{
    @Value("\${web.prefix}")
    private lateinit var prefix: String

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addRest(@RequestBody request: AddRequest): ResponseEntity<AddResponce>
    {
        //println("Request:$request")

        return ResponseEntity.ok(
                AddResponce(
                        request.link,
                        service.addLink(request.link)))
    }

    @RequestMapping(value = "/addHtml", method = arrayOf(RequestMethod.POST))
    fun addHtml(model: ModelAndView, @RequestParam(value = "link", required = true) link: String) : ModelAndView
    {
        val result = add(link)

        model.viewName = "result"
        model.addObject("link", result.link)
        model.addObject("keyed", prefix + result.key)

        return model
    }

    data class AddRequest(val link: String)
    data class AddResponce(val link: String, val key: String)

    private fun add(link: String) = AddResponce(link, service.addLink(link))
}
