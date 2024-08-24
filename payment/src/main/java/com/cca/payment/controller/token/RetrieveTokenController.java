package com.cca.payment.controller.token;

import com.cca.payment.dto.logindto.LoginRequestDTO;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.tokengenerationservice.TokenGenerationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RetrieveTokenController {


    @Autowired
    private TokenGenerationService tokenGenerationService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();

    @PostMapping("/retrieve-token")
    public ResponseDTO retrieveToken(@RequestBody LoginRequestDTO loginRequestDTO) {

        logService.printInfoLogs(request, className, "retrieveToken API started processing");
        return tokenGenerationService.generateToken(loginRequestDTO);
    }
}