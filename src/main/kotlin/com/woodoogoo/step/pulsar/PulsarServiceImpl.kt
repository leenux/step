package com.woodoogoo.step.pulsar

import org.apache.pulsar.client.api.PulsarClient
import org.springframework.stereotype.Service

@Service
class PulsarServiceImpl : PulsarService {
    override var client: PulsarClient
        get() = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
//                .ioThreads(50)
//                .listenerThreads(50)
//                .maxNumberOfRejectedRequestPerConnection(500)
//                .maxLookupRequests(500)
//                .connectionsPerBroker(50)
//                .maxConcurrentLookupRequests(50)
                .build()
        set(value) {}

    init {

    }

    override fun getName(): String {
        return "asd"
    }
}