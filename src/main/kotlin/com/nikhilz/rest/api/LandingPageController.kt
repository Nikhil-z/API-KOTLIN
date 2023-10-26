package com.nikhilz.rest.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LandingPageController {
    @GetMapping("/")
    fun landingPage(): String {
        return "index" // This corresponds to the index.html file in the templates directory
    }
}
