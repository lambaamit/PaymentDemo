package com.cca.paymentcore.dto.apierror;

import com.cca.paymentcore.dto.responsedto.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError extends ResponseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String debugMessage;

    private transient Object errors;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(String status) {
        this();
        this.httpStatus = status;
    }

    ApiError(String status, Throwable ex) {
        this();
        this.httpStatus = status;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
