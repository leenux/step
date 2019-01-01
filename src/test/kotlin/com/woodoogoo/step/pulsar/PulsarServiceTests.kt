package com.woodoogoo.step.pulsar

import kotlinx.coroutines.*
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.SubscriptionInitialPosition
import org.apache.pulsar.client.api.SubscriptionType
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit


@RunWith(SpringRunner::class)
@SpringBootTest
class PulsarServiceTests {
    @Autowired
    val pulsarService: PulsarService? = null

    @Autowired
    val stepAdmin: StepAdmin? = null

    @Test
    fun testGetName() {
        runBlocking {
            var cnt = 200
            var aa = arrayOfNulls<Deferred<Unit?>>(2 * cnt + 1)
            for (i in cnt..2 * cnt) {
                aa[i] = async {
                    val stringProducer = pulsarService?.client
                            ?.newProducer(Schema.STRING)
                            ?.producerName("hello0")
                            ?.topic("non-persistent://cherry/common/pay-try-$i")
                            ?.create()
                    stringProducer?.send("hi-$i")
                    println(">>>>>>>>>>>>hi-$i")
                    stringProducer?.flush()
                    stringProducer?.close()
                }
            }
            for (i in cnt..2 * cnt) {
                aa[i]?.await()
            }

//            Thread.sleep(5 * 60 * 1000)

//        val bb = Array<Deferred<Unit>>(10)
            var bb = arrayOfNulls<Deferred<Unit?>>(2 * cnt + 1)
            for (i in cnt..2 * cnt) {
                bb[i] = async {
                    println("==========================")
                    val consumer = pulsarService?.client?.newConsumer()
                            ?.topic("non-persistent://cherry/common/pay-try-$i")
                            ?.subscriptionName("hello$i")
                            ?.consumerName("hello$i")
                            ?.subscriptionType(SubscriptionType.Exclusive)
                            ?.subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                            ?.subscribe()
                    val msg = consumer?.receive(5, TimeUnit.MILLISECONDS)
                    println("<<<<<<$i<<<<<<<${msg?.data?.let { String(it) }}")

//                    println("+++++++++++Message received: ${String(msg?.data!!)}")
//        println("Message received: %s", String(msg?.data!!))

                    // Acknowledge the message so that it can be deleted by the message broker
                    if (msg != null) {
                        consumer.acknowledge(msg)
                        println("--------$i---------")
                        Assert.assertEquals("hi-$i", msg.data?.let { String(it) })
                    } else
                        Assert.assertEquals(null, msg?.data?.let { String(it) })
                    consumer?.close()
                    stepAdmin?.admin?.topics()?.delete("paytry$i", true)
                }
            }
            for (i in cnt..2 * cnt) {
                bb[i]?.await()
            }
        }
        pulsarService?.client?.close()
    }
}
