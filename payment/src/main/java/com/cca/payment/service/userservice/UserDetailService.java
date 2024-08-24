package com.cca.payment.service.userservice;

import com.cca.payment.builder.ResponseBuilderFactory;
import com.cca.payment.constants.ApplicationConstants;
import com.cca.payment.constants.Constants;
import com.cca.payment.constants.ResponseConstant;
import com.cca.payment.dto.responsedto.ResponseDTO;
import com.cca.payment.dto.userrecord.UserRecord;
import com.cca.payment.entity.userauth.UserAuth;
import com.cca.payment.enums.Channel;
import com.cca.payment.repo.userauth.UserAuthRepository;
import com.cca.payment.service.loggerservice.LogService;
import com.cca.payment.utility.Utility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private UserAuthRepository userAuthRepository;

    public ResponseDTO createUser(UserRecord userRecord) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean isExist= userAuthRepository.existsByUsername(userRecord.username());
            if(isExist){
                return ResponseBuilderFactory.getResponse(ApplicationConstants.USER_ALREADY_EXIST, ResponseConstant.FAILURE);
            }
            logService.printInfoLogs(request, className, "createUser API started processing");
            UserAuth userAuth = UserAuth.builder()
                    .username(userRecord.username())
                    .password(bCryptPasswordEncoder.encode(userRecord.password()))
                    .deviceIid("1234567")
                    .deviceType("android")
                    .channel(Channel.APP.name())
                    .merchantId(Utility.generateMerchantId())
                    .build();

            userAuthRepository.save(userAuth);

            return ResponseBuilderFactory.getResponse(Constants.SUCCESS_STATUS, ResponseConstant.SUCCESS);
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return ResponseBuilderFactory.getResponse(Constants.FAILURE_STATUS, ResponseConstant.FAILURE);
        }

    }

}
