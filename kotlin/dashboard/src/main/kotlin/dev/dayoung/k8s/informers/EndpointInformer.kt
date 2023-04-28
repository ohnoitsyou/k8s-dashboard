package dev.dayoung.k8s.informers

import io.kubernetes.client.informer.ResourceEventHandler
import io.kubernetes.client.openapi.models.V1Endpoints
import io.kubernetes.client.openapi.models.V1EndpointsList
import io.micronaut.kubernetes.client.informer.Informer

@Informer(apiType = V1Endpoints::class, apiListType = V1EndpointsList::class)
class EndpointInformer : ResourceEventHandler<V1Endpoints> {
    override fun onAdd(obj: V1Endpoints?) {
    }

    override fun onUpdate(oldObj: V1Endpoints?, newObj: V1Endpoints?) {
    }

    override fun onDelete(obj: V1Endpoints?, deletedFinalStateUnknown: Boolean) {
    }

}
