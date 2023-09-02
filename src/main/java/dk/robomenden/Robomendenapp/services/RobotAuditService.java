package dk.robomenden.Robomendenapp.services;

import javax.persistence.*;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RobotAuditService {

    private static final Logger logger = LoggerFactory.getLogger(RobotAuditService.class);

    private String initialRobotOwner;
    private String initialRobotName;
    private String initialRobotNumber;
    private String initialRobotPinCode;
    private String initialRobotDate;

    public String getInitialRobotOwner() {
        return initialRobotOwner;
    }

    public void setInitialRobotOwner(String initialRobotOwner) {
        this.initialRobotOwner = initialRobotOwner;
    }

    public String getInitialRobotName() {
        return initialRobotName;
    }

    public void setInitialRobotName(String initialRobotName) {
        this.initialRobotName = initialRobotName;
    }

    public String getInitialRobotNumber() {
        return initialRobotNumber;
    }

    public void setInitialRobotNumber(String initialRobotNumber) {
        this.initialRobotNumber = initialRobotNumber;
    }

    public String getInitialRobotPinCode() {
        return initialRobotPinCode;
    }

    public void setInitialRobotPinCode(String initialRobotPinCode) {
        this.initialRobotPinCode = initialRobotPinCode;
    }

    public String getInitialRobotDate() {
        return initialRobotDate;
    }

    public void setInitialRobotDate(String initialRobotDate) {
        this.initialRobotDate = initialRobotDate;
    }

    public String logFieldChange(String fieldName, String oldValue, String newValue) {
        String logMessage = String.format("Field %s has been changed from '%s' to '%s'", fieldName, oldValue, newValue);
        logger.info(logMessage);
        return logMessage;
    }
}
