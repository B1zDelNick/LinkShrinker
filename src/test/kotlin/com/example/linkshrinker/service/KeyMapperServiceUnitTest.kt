package com.example.linkshrinker.service

import com.example.linkshrinker.model.Link
import com.example.linkshrinker.model.LinkRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Matchers.anyLong
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class KeyMapperServiceUnitTest
{
    @InjectMocks
    val serviceUnderTest : KeyMapperService = DefaultKeyMapperService()

    @Mock
    lateinit var converter : KeyConverterService

    @Mock
    lateinit var repository : LinkRepository

    private val LINK_A = "http://www.google.com"
    private val LINK_B = "http://www.wow.com"

    @Before
    fun setup()
    {
        MockitoAnnotations.initMocks(this)
    }

    private val KEY_A = "abc"
    private val ID_A = 1000000L
    private val KEY_B = "zxc"
    private val ID_B = 1000001L

    @Test
    fun addLink_WhenSuccess_ShouldReturnKey()
    {
        given(converter.keyToId(KEY_A)).willReturn(ID_A)
        given(converter.idToKey(ID_A)).willReturn(KEY_A)

        given(converter.keyToId(KEY_B)).willReturn(ID_B)
        given(converter.idToKey(ID_B)).willReturn(KEY_B)

        given(repository.findOne(anyLong())).willReturn(Optional.empty())
        given(repository.save(Link(text = LINK_A))).willReturn(Link(ID_A, LINK_A))
        given(repository.save(Link(text = LINK_B))).willReturn(Link(ID_B, LINK_B))
        given(repository.findOne(ID_A)).willReturn(Optional.of(Link(ID_A, LINK_A)))
        given(repository.findOne(ID_B)).willReturn(Optional.of(Link(ID_B, LINK_B)))

        val keyA = serviceUnderTest.addLink(LINK_A)
        assertThat(serviceUnderTest.getLink(keyA)).isEqualTo(KeyMapperService.Get.Link(LINK_A))

        val keyB = serviceUnderTest.addLink(LINK_B)
        assertThat(serviceUnderTest.getLink(keyB)).isEqualTo(KeyMapperService.Get.Link(LINK_B))

        assertThat(keyA).isNotEqualTo(keyB)
    }
}