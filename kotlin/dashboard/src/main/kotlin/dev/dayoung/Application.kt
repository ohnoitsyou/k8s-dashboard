package dev.dayoung

import io.micronaut.runtime.Micronaut.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
	val log: Logger = LoggerFactory.getLogger("main")
	run(*args)
	log.info("Running in Micronaut!")
}

