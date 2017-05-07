package com.example.linkshrinker.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class LinkRepositoryJpaTest
{
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var repositoryUnderTest: LinkRepository

    @Test
    fun findOne_WhenExisting_ShouldReturnLink()
    {
        val persisted = entityManager.persistFlushFind(Link(text = "http://www.wiw1.ru"))
        val found = repositoryUnderTest.findOne(persisted.id)

        assertThat(found.isPresent).isTrue()
        assertThat(found.get().text).isEqualTo(persisted.text)
    }
}