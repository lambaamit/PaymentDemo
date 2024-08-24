package com.cca.payment.dto.commondto;

import com.cca.payment.repo.userauth.UserAuthRepository;
import com.cca.payment.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepositoryDto {

    private UserAuthRepository userAuthRepository;

    private LogService logService;

    private HttpServletRequest request;

}
