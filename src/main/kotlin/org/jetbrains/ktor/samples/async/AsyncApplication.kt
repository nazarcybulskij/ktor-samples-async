package org.jetbrains.ktor.samples.async

import kotlinx.coroutines.experimental.*
import kotlinx.html.*
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.features.*
import org.jetbrains.ktor.html.*
import org.jetbrains.ktor.logging.*
import org.jetbrains.ktor.pipeline.*
import org.jetbrains.ktor.routing.*
import java.util.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/time") {
            val start = System.currentTimeMillis()
            call.handleLongCalculation(start)
        }
    }
}

private suspend fun ApplicationCall.handleLongCalculation(start: Long) {
    val time = System.currentTimeMillis() - start
    respondHtml {
        head {
            title { +"Hello World" }
        }
        body {
            h1 {
                +"start ${time}"
            }
        }
    }
}

