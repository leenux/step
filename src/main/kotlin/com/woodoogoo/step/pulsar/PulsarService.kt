package com.woodoogoo.step.pulsar

import org.apache.pulsar.client.api.PulsarClient

interface PulsarService {
    var client: PulsarClient
    fun getName(): String
}