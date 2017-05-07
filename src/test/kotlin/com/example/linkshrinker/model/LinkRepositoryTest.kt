package com.example.linkshrinker.model

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
class LinkRepositoryTest : AbstractRepositoryTest()
{
    @Autowired
    lateinit var repositoryUnderTest: LinkRepository

    private val EXISTING_ID = 100500L

    @Test
    fun findOne_WhenExisting_ShouldReturnLink()
    {
        val link: Optional<Link> = repositoryUnderTest.findOne(EXISTING_ID)

        assertThat(link.isPresent).isTrue()
    }

    companion object
    {
        const val DATASET = "classpath:data/link-table.xml"
    }
}