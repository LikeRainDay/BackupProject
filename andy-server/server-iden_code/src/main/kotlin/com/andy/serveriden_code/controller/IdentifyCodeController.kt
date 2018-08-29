package com.andy.serveriden_code.controller

import com.andy.serveriden_code.service.IVCodeGenerationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class IdentifyCodeController {

    @Autowired
    @Qualifier(value = "EasyPicTextVC")
    private lateinit var ivCodeGenerationService: IVCodeGenerationService

    @GetMapping(value = ["/get"])
    fun getImage(reques: HttpServletRequest, response: HttpServletResponse): String {
        ivCodeGenerationService.randomGeneration(response.outputStream)
        return "请求成功"
    }

}