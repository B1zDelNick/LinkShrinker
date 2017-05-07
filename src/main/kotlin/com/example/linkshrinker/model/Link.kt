package com.example.linkshrinker.model

import javax.persistence.*

@Entity
@Table(name = "LINKS")
data class Link(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
        @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq")
        var id: Long = 0,
        var text: String = "")