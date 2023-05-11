package dev.dayoung

import dev.dayoung.k8s.informers.InformerCache
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.MediaType

@Controller("/ingresses")
class IngressController(private val serviceInformer: InformerCache) {
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun allEndpointURL(): List<String?> =
        serviceInformer.getIngresses().filter { ing ->
            val hasLabel = ing.metadata?.annotations?.contains("dev.dayoung.dashboard/include") ?: false
            val hasValue = ing.metadata?.annotations?.getOrDefault("dev.dayoung.dashboard/include", "false") == "true"
            hasLabel && hasValue
        }.flatMap { ing ->
            ing.spec?.rules?.map { rule -> rule.host } ?: listOf()
        }
}