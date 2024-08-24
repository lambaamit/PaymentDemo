package com.cca.payment.encryption;

import com.cca.payment.service.loggerservice.LogService;
import jakarta.persistence.AttributeConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("java:S6813")
public class PIIAttributeConverter implements AttributeConverter<String, String> {

    private final OclDfsPIIEncryption oclDfsPIIEncryption;
    private final String className = getClass().getSimpleName();


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    @Value("${pii.required}")
    private boolean piiEncryptionFlag;

    @Autowired
    public PIIAttributeConverter(OclDfsPIIEncryption oclDfsPIIEncryption) {
        this.oclDfsPIIEncryption = oclDfsPIIEncryption;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            if (ObjectUtils.isNotEmpty(piiEncryptionFlag)
                    && piiEncryptionFlag
                    && StringUtils.isNotEmpty(attribute)) {
//        logService.printInfoLogs(
//            tracerDetails.getSpanId(),
//            tracerDetails.getTraceId(),
//            request,
//            className,
//            "PII KEY Conversion : ");
                return OclDfsPIIEncryption.encrypt(attribute);
            }
            return attribute;
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return attribute;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            if (ObjectUtils.isNotEmpty(piiEncryptionFlag)
                    && piiEncryptionFlag
                    && StringUtils.isNotEmpty(dbData)) {
                return OclDfsPIIEncryption.decrypt(dbData);
            }
            return dbData;
        } catch (Exception e) {
            logService.printErrorLogs(request, className, e);
            return dbData;
        }
    }
}
