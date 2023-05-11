package dev.dayoung.k8s.informers

import io.kubernetes.client.informer.ResourceEventHandler
import io.kubernetes.client.openapi.models.V1Ingress
import io.kubernetes.client.openapi.models.V1IngressList
import io.micronaut.kubernetes.client.informer.Informer

@Informer(apiType = V1Ingress::class, apiListType = V1IngressList::class, namespace = Informer.ALL_NAMESPACES)
class IngressInformer : ResourceEventHandler<V1Ingress> {
    override fun onAdd(obj: V1Ingress?) { }

    override fun onUpdate(oldObj: V1Ingress?, newObj: V1Ingress?) { }

    override fun onDelete(obj: V1Ingress?, deletedFinalStateUnknown: Boolean) { }

}
