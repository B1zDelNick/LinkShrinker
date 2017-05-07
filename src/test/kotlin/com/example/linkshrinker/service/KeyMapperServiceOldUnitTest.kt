package com.example.linkshrinker.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

//@RunWith(SpringRunner::class)
//@SpringBootTest
class KeyMapperServiceOldUnitTest
{
    val serviceUnderTest: KeyMapperServiceOld = DefaultKeyMapperServiceOld()

    @get:Rule
    val thrown : ExpectedException = ExpectedException.none()

    @Test
    fun addKey_ShouldReturnSuccess()
    {
        assertThat(serviceUnderTest.addKey(KEY, LINK)).isEqualTo(KeyMapperServiceOld.Add.Success(KEY, LINK))
    }

    @Test
    fun getLink_ShouldReturnLink()
    {
        serviceUnderTest.addKey(KEY, LINK)
        assertThat(serviceUnderTest.getLink(KEY)).isEqualTo(KeyMapperServiceOld.Get.Link(LINK))
    }

    @Test
    fun addKey_WhenDuplicateKey_ShouldReturnAlreadyExists()
    {
        serviceUnderTest.addKey(KEY, LINK)
        assertThat(serviceUnderTest.addKey(KEY, NEW_LINK)).isEqualTo(KeyMapperServiceOld.Add.AlreadyExists(KEY))
        assertThat(serviceUnderTest.getLink(KEY)).isNotEqualTo(KeyMapperServiceOld.Get.Link(NEW_LINK))
        assertThat(serviceUnderTest.getLink(KEY)).isEqualTo(KeyMapperServiceOld.Get.Link(LINK))
    }

    @Test
    fun getLink_WhenKeyIsNotPresent_ShouldReturnNotFound()
    {
        assertThat(serviceUnderTest.getLink(KEY)).isEqualTo(KeyMapperServiceOld.Get.NotFound(KEY))
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