package com.cca.payment.service.validateuserservice;

import com.cca.payment.constants.ApplicationConstants;
import com.cca.payment.constants.Constants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.entity.userauth.UserAuth;
import com.cca.payment.exception.ResponseException;
import com.cca.payment.repo.userauth.UserAuthRepository;
import com.cca.payment.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ValidateUserCredService {

    @Autowired
    private LogService logService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserAuthRepository userAuthRepository;
    private final String className = this.getClass().getSimpleName();


    public boolean validateUser(String userId, String password) {
        logService.printInfoLogs(httpServletRequest, className, "Initiating User Validation");
        String channel = httpServletRequest.getHeader("channel");
        return switch (channel) {
//            case ApplicationConstants.SYSTEM_PORTAL, ApplicationConstants.RMS_PORTAL ->
//                    roleMatrixIntegrationService.validateUser(userId, password);
            case ApplicationConstants.APP ->validateUserCred(userId,password);
            default -> false;
        };
    }


    public  boolean validateUserCred(String userId,String password){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        UserAuth userAuth=userAuthRepository.findByUsername(userId);

        if(ObjectUtils.isEmpty(userAuth) || ObjectUtils.isEmpty(userAuth.getPassword())  ||  !bCryptPasswordEncoder.matches(password,userAuth.getPassword())){
           throw new ResponseException(Constants.UNAUTHORIZED_LOGIN, ResponseConstant.UNAUTHORIZED_LOGIN);
        }
        return true;
    }
}
