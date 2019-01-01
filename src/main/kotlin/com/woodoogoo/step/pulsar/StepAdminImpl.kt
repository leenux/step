package com.woodoogoo.step.pulsar

import org.apache.pulsar.client.admin.PulsarAdmin
import org.apache.pulsar.client.api.url.URL
import org.apache.pulsar.client.impl.conf.ClientConfigurationData

class StepAdminImpl : StepAdmin {
    lateinit var config: ClientConfigurationData

    override var admin: PulsarAdmin
        get() = PulsarAdmin("http://localhost:8080", config)
        set(value) {}

    init {
        val authPluginClassName = "com.org.MyAuthPluginClass"
// Pass auth-param if auth-plugin class requires it
        val authParams = "param1=value1"
        val useTls = false
        val tlsAllowInsecureConnection = false
        val tlsTrustCertsFilePath: String? = null

//        val config = ClientConfiguration()
//        config.setAuthentication(authPluginClassName, authParams)
        config.isUseTls = useTls
        config.isTlsAllowInsecureConnection = tlsAllowInsecureConnection
        config.tlsTrustCertsFilePath = tlsTrustCertsFilePath
    }
}