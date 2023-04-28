package dev.dayoung.k8s.informers

import io.kubernetes.client.informer.ResourceEventHandler
import io.kubernetes.client.openapi.models.V1Service
import io.kubernetes.client.openapi.models.V1ServiceList
import io.micronaut.kubernetes.client.informer.Informer

@Informer(apiType = V1Service::class, apiListType = V1ServiceList::class)
class ServiceInformer : ResourceEventHandler<V1Service> {
    override fun onAdd(obj: V1Service?) {
    }

    override fun onUpdate(oldObj: V1Service?, newObj: V1Service?) {
    }

    override fun onDelete(obj: V1Service?, deletedFinalStateUnknown: Boolean) {
    }

}