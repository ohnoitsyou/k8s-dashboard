package dev.dayoung.k8s.informers

import io.kubernetes.client.openapi.models.V1Endpoints
import io.kubernetes.client.openapi.models.V1Ingress
import io.kubernetes.client.openapi.models.V1Service
import io.micronaut.kubernetes.client.informer.SharedIndexInformerFactory
import jakarta.inject.Singleton


@Singleton
class InformerCache(private val sharedIndexInformerFactory: SharedIndexInformerFactory) {

    fun getServices(namespace: String): List<V1Service> =
            sharedIndexInformerFactory.getExistingSharedIndexInformer(
                namespace,
                V1Service::class.java
            ).indexer.list()
    fun getEndpoints(namespace: String): List<V1Endpoints> =
        sharedIndexInformerFactory.getExistingSharedIndexInformer(
            namespace,
            V1Endpoints::class.java
        ).indexer.list()

    fun getIngresses(namespace: String = "default"): List<V1Ingress> =
        sharedIndexInformerFactory.getExistingSharedIndexInformer(
            namespace,
            V1Ingress::class.java
        ).indexer.list()
}