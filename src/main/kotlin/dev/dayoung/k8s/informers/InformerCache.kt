package dev.dayoung.k8s.informers

import io.kubernetes.client.openapi.models.V1Endpoints
import io.kubernetes.client.openapi.models.V1Ingress
import io.kubernetes.client.openapi.models.V1Namespace
import io.kubernetes.client.openapi.models.V1Service
import io.micronaut.kubernetes.client.informer.SharedIndexInformerFactory
import jakarta.inject.Singleton


@Singleton
class InformerCache(private val sharedIndexInformerFactory: SharedIndexInformerFactory) {
    fun getIngresses(): List<V1Ingress> =
        sharedIndexInformerFactory.getExistingSharedIndexInformer(
            "",
            V1Ingress::class.java
        ).indexer.list()
}