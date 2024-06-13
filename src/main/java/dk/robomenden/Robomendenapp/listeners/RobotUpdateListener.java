package dk.robomenden.Robomendenapp.listeners;

import dk.robomenden.Robomendenapp.models.LogEntry;
import dk.robomenden.Robomendenapp.models.Robot;
import dk.robomenden.Robomendenapp.services.LogEntryService;
import dk.robomenden.Robomendenapp.services.LogService;
import dk.robomenden.Robomendenapp.services.RobotAuditService;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class RobotUpdateListener {

	private final RobotAuditService robotAuditService;
	private final EntityManager entityManager;

	private final LogEntryService logEntryService;

	@Autowired
	public RobotUpdateListener(RobotAuditService robotAuditService, LogEntryService logEntryService,
			EntityManager entityManager, LogService logService) {
		this.entityManager = entityManager;
		this.logEntryService = logEntryService;
		this.robotAuditService = robotAuditService;
	}

	@PostUpdate
	public void onRobotUpdate(Robot robot) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String oldOwner = entityManager.find(Robot.class, robot.getId()).getOwner();
		String oldRobotName = entityManager.find(Robot.class, robot.getId()).getRobotName();
		long oldRobotNumber = entityManager.find(Robot.class, robot.getId()).getRobotNumber();
		long oldPinCode = entityManager.find(Robot.class, robot.getId()).getPinCode();
		LocalDate oldRobotDate = entityManager.find(Robot.class, robot.getId()).getRobotDate();

		String newOwner = robot.getOwner();
		String newRobotName = robot.getRobotName();
		long newRobotNumber = robot.getRobotNumber();
		long newPinCode = robot.getPinCode();
		LocalDate newRobotDate = robot.getRobotDate();

		if (!Objects.equals(oldOwner, newOwner)) {
			LogEntry logEntry = new LogEntry();
			String text = robotAuditService.logFieldChange("owner", oldOwner, newOwner);
			logEntry.setMessage(text);
			logEntry.setOwner_robot(robot);
			logEntryService.save(logEntry);
		}

		if (!Objects.equals(oldRobotName, newRobotName)) {
			LogEntry logEntry = new LogEntry();
			String text = robotAuditService.logFieldChange("robot_name", oldRobotName, newRobotName);
			logEntry.setMessage(text);
			logEntry.setOwner_robot(robot);
			logEntryService.save(logEntry);
		}

		if (!Objects.equals(oldRobotNumber, newRobotNumber)) {
			LogEntry logEntry = new LogEntry();
			String text = robotAuditService.logFieldChange("robot_number", oldRobotNumber + "", newRobotNumber + "");
			logEntry.setMessage(text);
			logEntry.setOwner_robot(robot);
			logEntryService.save(logEntry);
		}

		if (!Objects.equals(oldPinCode, newPinCode)) {
			LogEntry logEntry = new LogEntry();
			String text = robotAuditService.logFieldChange("pin_code", oldPinCode + "", newPinCode + "");
			logEntry.setMessage(text);
			logEntry.setOwner_robot(robot);
			logEntryService.save(logEntry);
		}

		if (!Objects.equals(oldRobotDate, newRobotDate)) {
			LogEntry logEntry = new LogEntry();
			String text = robotAuditService.logFieldChange("robot_date", oldRobotDate + "", "" + newRobotDate);
			logEntry.setMessage(text);
			logEntry.setOwner_robot(robot);
			logEntryService.save(logEntry);
		}
	}

}
