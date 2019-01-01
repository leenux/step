package com.woodoogoo.step.pulsar

import org.apache.pulsar.client.admin.PulsarAdmin

interface StepAdmin {
    var admin: PulsarAdmin
}