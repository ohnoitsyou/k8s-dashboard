package dev.dayoung

import dev.dayoung.k8s.informers.InformerCache
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue

@Controller("/services")
class ServiceController(private val serviceInformer: InformerCache) {
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun serviceList(@QueryValue(defaultValue="default") namespace: String): String {
        return serviceInformer.getServices(namespace).toString()
    }
}