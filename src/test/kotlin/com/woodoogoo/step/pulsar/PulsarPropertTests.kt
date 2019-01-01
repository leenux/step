package com.woodoogoo.step.pulsar

import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.SubscriptionInitialPosition
import org.apache.pulsar.client.api.SubscriptionType
import org.apache.pulsar.shade.com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.TimeUnit

@RunWith(SpringRunner::class)
@SpringBootTest
class PulsarPropertTests {
    @Autowired
    val pulsarService: PulsarService? = null

    @Test
    fun testGetName() {
        var cnt = 20000
        val stringProducer = pulsarService?.client
                ?.newProducer(Schema.STRING)
                ?.producerName("hello0")
                ?.topic("persistent://cherry/common/pay-try")
                ?.create()

        val consumer = pulsarService?.client?.newConsumer()
                ?.topic("persistent://cherry/common/pay-try")
                ?.subscriptionName("hello0")
                ?.consumerName("hello0")
                ?.subscriptionType(SubscriptionType.Exclusive)
                ?.subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                ?.subscribe()

        while (stringProducer?.isConnected != true) {
            Thread.sleep(20)
            println("++++++++++++++++++++++++++++++")
        }

        while (consumer?.isConnected != true) {
            Thread.sleep(20)
            println("++++++++++++++++++++++++++++++")
        }

        for (i in 1000..cnt) {
            stringProducer.newMessage()?.property("kk-$i", "hh-$i")
                    ?.value("$i")?.key("$i")?.send()
            println(">>>>>>>>>>>>hh-$i")
        }
        stringProducer.close()

        for (i in 1000..cnt + 10) {
            val msg = consumer.receive(50, TimeUnit.MILLISECONDS)
            println("<<<<<<<$i==${msg?.getProperty("kk-$i")}<<<<<<")

            if (msg != null) {
                consumer.acknowledge(msg)
                println("-----------------")
            }
        }
        consumer.close()

    }
}
