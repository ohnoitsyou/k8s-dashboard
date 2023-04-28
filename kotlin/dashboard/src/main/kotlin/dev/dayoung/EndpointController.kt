package dev.dayoung

import dev.dayoung.k8s.informers.InformerCache
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/endpoints")
class EndpointController(private val serviceInformer: InformerCache) {

    @Get
    @Produces
    fun allEndpoints() =
        serviceInformer.getEndpoints("default")
}