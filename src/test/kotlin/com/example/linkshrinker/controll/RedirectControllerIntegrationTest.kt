package com.example.linkshrinker.controll

import com.example.linkshrinker.service.KeyMapperService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class RedirectControllerIntegrationTest
{
    private val KEY: String = "aaAAbbBB"
    private val BAD_KEY: String = "aaAAbbBB"
    private val PATH: String = "/api/$KEY"
    private val BAD_PATH: String = "/api/$BAD_KEY"
    private val REDIRECT_STATUS: Int = 302
    private val NOT_FOUND: Int = 404
    private val HEADER_NAME: String = "Location"
    private val HEADER_VALUE: String = "http://www.eveonline.com"

    @Autowired
    lateinit var contex: WebApplicationContext

    lateinit var mockMvc: MockMvc

    //@Autowired
    //@InjectMocks
    //lateinit var controller : RedirectController

    //@Mock
    @MockBean
    lateinit var service : KeyMapperService

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this)

        mockMvc = webAppContextSetup(contex).build()
    }

    @Test
    fun controller_WhenRequestSuccessfull_ShouldRedirectUs()
    {
        given(service.getLink(KEY)).willReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        //Mockito.`when`(service.getLink(KEY)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))

        mockMvc.perform(get(PATH))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
                //.andExpect(forwardedUrl(HEADER_VALUE))
    }

    @Test
    fun controller_WhenBadKey_ShouldReturnNotFound()
    {
        given(service.getLink(BAD_KEY)).willReturn(KeyMapperService.Get.NotFound(BAD_KEY))

        mockMvc.perform(get(BAD_PATH))
                .andExpect(status().`is`(NOT_FOUND))
    }

    @Test
    fun controller_WhenHomeCalled_ShouldReturnHomeView()
    {
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"))
    }
}
