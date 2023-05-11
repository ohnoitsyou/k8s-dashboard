package dev.dayoung

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/")
class StaticController {

    @Get("/")
    @Produces(MediaType.TEXT_HTML)
    fun serveStatic(): String {
        return StaticController::class.java.getResource("/html/index.html")?.readText() ?: ""
    }
}