package com.dbl.healthcheck.health

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RequestMapping("/v1/saude")
@RestController
class HealthController {

    @Autowired
    lateinit var javamail: JavaMailSender

    @Value("\${spring.mail.username}")
    lateinit var mail: String

    @GetMapping
    fun estaSaudevel(): String {
        return "Estou vivo na data ${LocalDateTime.now()}"
    }

    @PutMapping
    fun sendMail() {
        var simpleMessage = SimpleMailMessage()
        simpleMessage.from = mail
        simpleMessage.setTo(mail)
        simpleMessage.subject = "Super urgente"
        javamail.send(
            simpleMessage
        )
    }
}