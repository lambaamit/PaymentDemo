package com.cca.paymentcore.dto.commondto;

import com.cca.paymentcore.repo.userauth.UserAuthRepository;
import com.cca.paymentcore.service.loggerservice.LogService;
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
