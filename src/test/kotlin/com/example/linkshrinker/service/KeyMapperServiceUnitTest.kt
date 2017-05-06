package com.example.linkshrinker.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class KeyMapperServiceUnitTest
{
    val serviceUnderTest: KeyMapperService = DeafaultKeyMapperService()

    @get:Rule
    val thrown : ExpectedException = ExpectedException.none()

    @Test
    //fun addKey_WhenKeyAndLinkOk_ShouldReturnSuccess()
    fun addKey_ShouldReturnSuccess()
    {
        assertThat(serviceUnderTest.addKey(KEY, LINK)).isEqualTo(KeyMapperService.Add.Success(KEY, LINK))
    }

    @Test
    fun getLink_ShouldReturnLink()
    {
        serviceUnderTest.addKey(KEY, LINK)
        assertThat(serviceUnderTest.getLink(KEY)).isEqualTo(KeyMapperService.Get.Link(LINK))
    }

    @Test
    fun addKey_WhenDuplicateKey_ShouldReturnAlreadyExists()
    {
        serviceUnderTest.addKey(KEY, LINK)
        assertThat(serviceUnderTest.addKey(KEY, NEW_LINK)).isEqualTo(KeyMapperService.Add.AlreadyExists(KEY))
        assertThat(serviceUnderTest.getLink(KEY)).isNotEqualTo(KeyMapperService.Get.Link(NEW_LINK))
        assertThat(serviceUnderTest.getLink(KEY)).isEqualTo(KeyMapperService.Get.Link(LINK))
    }

    @Test
    fun getLink_WhenKeyIsNotPresent_ShouldReturnNotFound()
    {
        assertThat(serviceUnderTest.getLink(KEY)).isEqualTo(KeyMapperService.Get.NotFound(KEY))
    }

    @Test
    fun addKey_WhenKeyOrLinkIsNull_ShouldThrowException()
    {

    }

    companion object
    {
        private const val KEY = "key1"
        private const val LINK = "link1"
        private const val NEW_LINK = "link2"
    }
}