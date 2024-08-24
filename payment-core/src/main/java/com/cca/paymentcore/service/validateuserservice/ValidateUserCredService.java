package com.cca.paymentcore.service.validateuserservice;

import com.cca.paymentcore.constants.ApplicationConstants;
import com.cca.paymentcore.constants.Constants;
import com.cca.paymentcore.constants.ResponseConstant;
import com.cca.paymentcore.entity.userauth.UserAuth;
import com.cca.paymentcore.exception.ResponseException;
import com.cca.paymentcore.repo.userauth.UserAuthRepository;
import com.cca.paymentcore.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ValidateUserCredService {

    private final String className = this.getClass().getSimpleName();
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserAuthRepository userAuthRepository;
    @Autowired
    private LogService logService;

    public boolean validateUser(String userId, String password) {
        logService.printInfoLogs(httpServletRequest, className, "Initiating User Validation");
        String channel = httpServletRequest.getHeader("channel");
        return switch (channel) {
//            case ApplicationConstants.SYSTEM_PORTAL, ApplicationConstants.RMS_PORTAL ->
//                    roleMatrixIntegrationService.validateUser(userId, password);
            case ApplicationConstants.APP -> validateUserCred(userId, password);
            default -> false;
        };
    }


    public boolean validateUserCred(String userId, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserAuth userAuth = userAuthRepository.findByUsername(userId);

        if (ObjectUtils.isEmpty(userAuth) || ObjectUtils.isEmpty(userAuth.getPassword()) || bCryptPasswordEncoder.matches(password, userAuth.getPassword())) {
            throw new ResponseException(Constants.UNAUTHORIZED_LOGIN, ResponseConstant.UNAUTHORIZED_LOGIN);
        }
        return true;
    }
}
