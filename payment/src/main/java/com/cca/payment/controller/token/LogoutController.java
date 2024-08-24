package com.cca.payment.controller.token;

import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.logoutservice.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LogoutController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseDTO logout() {
        logService.printInfoLogs(request, className, "logout API started processing");
        return logoutService.logout();
    }
}
