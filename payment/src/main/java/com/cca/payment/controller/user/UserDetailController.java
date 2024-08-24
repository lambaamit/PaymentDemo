package com.cca.payment.controller.user;

import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.dto.userrecord.UserRecord;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.service.userservice.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Autowired
    private UserDetailService userDetailService;
    private final String className = this.getClass().getSimpleName();

    @PostMapping("/create-user")
    public ResponseDTO createUser(@RequestBody UserRecord userRecord) {
        logService.printInfoLogs(request, className, "createUser API started processing");
        return userDetailService.createUser(userRecord);

    }


}
