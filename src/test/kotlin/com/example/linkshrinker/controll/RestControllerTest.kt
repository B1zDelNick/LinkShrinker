package com.example.linkshrinker.controll

import com.example.linkshrinker.service.KeyMapperService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class RestControllerTest
{
    @Autowired
    lateinit var contex: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service : KeyMapperService

    //val objectMapper: ObjectMapper = ObjectMapper()

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this)

        mockMvc = MockMvcBuilders.webAppContextSetup(contex).build()
    }

    private val LINK = "link"
    private val KEY = "key"

    @Test
    fun test1()
    {
        given(service.addLink(LINK)).willReturn(KEY)

        mockMvc.perform(post("/api/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper().writeValueAsString(RestController.AddRequest(LINK))))
                .andExpect(jsonPath("$.key", equalTo(KEY)))
                .andExpect(jsonPath("$.link", equalTo(LINK)))

    }

    @Test
    fun addLink_WhenPostingForm_ShouldShowResultHtml()
    {
        given(service.addLink(LINK)).willReturn(KEY)

        mockMvc.perform(post("/api/addHtml")
                    .param("link", LINK)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString(KEY)))
                .andExpect(content().string(containsString(LINK)))
    }
}