package com.example.linkshrinker.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class KeyConverterServiceUnitTest
{
    val serviceUnderTest: KeyConverterService = KeyConverterServiceImpl()

    @Test
    fun convertInBothWays_ShouldBeSuccessfull()
    {
        val rnd = Random()

        for (i in 1..1000L)
        {
            val initialId = Math.abs(rnd.nextLong())
            val key = serviceUnderTest.idToKey(initialId)
            val id = serviceUnderTest.keyToId(key)

            assertThat(id).isEqualTo(initialId)
        }
    }
}