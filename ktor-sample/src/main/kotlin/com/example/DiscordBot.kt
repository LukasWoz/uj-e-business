package com.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.json.*
import kotlinx.serialization.*
import kotlin.time.Duration.Companion.seconds

@Serializable
private data class GatewayPayload(
    val op: Int,
    val d: JsonElement?,
    val s: Int? = null,
    val t: String? = null
)

class DiscordBot(private val app: Application) {
    private val gatewayUrl = "wss://gateway.discord.gg/?v=10&encoding=json"
    
    private val json = Json { ignoreUnknownKeys = true }

    private val token by lazy {
        System.getenv("DISCORD_BOT_TOKEN")
            ?: error("ENV DISCORD_BOT_TOKEN not set")
    }

    private val client = HttpClient(CIO) {
        install(WebSockets) {
            pingInterval = 15.seconds
        }
        install(ContentNegotiation) {
            json(json)
        }
    }

    suspend fun start() = client.webSocket(gatewayUrl) {
        var lastSeq: Int? = null

        val helloPayload = json.decodeFromString<GatewayPayload>(
            (incoming.receive() as Frame.Text).readText()
        )
        val hbInterval = helloPayload.d!!.jsonObject["heartbeat_interval"]!!.jsonPrimitive.long

        val identify = buildJsonObject {
            put("op", 2)
            putJsonObject("d") {
                put("token", token)
                put("intents", 33281)
                putJsonObject("properties") {
                    put("\$os", System.getProperty("os.name"))
                    put("\$browser", "ktor-bot")
                    put("\$device", "ktor-bot")
                }
            }
        }
        outgoing.send(Frame.Text(identify.toString()))

        launch {
            while (true) {
                delay(hbInterval)
                outgoing.send(Frame.Text(buildJsonObject {
                    put("op", 1)
                    put("d", lastSeq?.let { JsonPrimitive(it) } ?: JsonNull)
                }.toString()))
            }
        }

        incoming.consumeEach { frame ->
            if (frame is Frame.Text) {
                val payload = json.decodeFromString<GatewayPayload>(frame.readText())
                lastSeq = payload.s ?: lastSeq

                when (payload.t) {
                    "MESSAGE_CREATE" -> {
                        val obj = payload.d!!.jsonObject
                        val channelId = obj["channel_id"]!!.jsonPrimitive.content
                        val content = obj["content"]!!.jsonPrimitive.content

                        if (content.startsWith("!echo ")) {
                            sendMessage(channelId, "Echo: ${content.removePrefix("!echo ")}")
                        }
                    }
                }
            }
        }
    }

    private suspend fun sendMessage(channelId: String, message: String) {
        client.post("https://discord.com/api/v10/channels/$channelId/messages") {
            header(HttpHeaders.Authorization, "Bot $token")
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject { put("content", message) })
        }
    }
}