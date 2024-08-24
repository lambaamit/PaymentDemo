package com.cca.payment.controller.token;

import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.refreshtokenservice.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RefreshTokenController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/refresh-token")
    public ResponseDTO refreshToken() {
        logService.printInfoLogs(request, className, "refreshToken API started processing");
        return refreshTokenService.refreshToken();
    }
}
