package com.cca.paymentcore.dto.commondto;

import com.cca.paymentcore.service.loggerservice.LogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartResolver;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommonElementDto {
    private LogService logService;
    private MultipartResolver multipartResolver;
    private HttpServletRequest request;

}
