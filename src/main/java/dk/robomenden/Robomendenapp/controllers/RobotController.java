package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.listeners.RobotUpdateListener;
import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.Robot;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import dk.robomenden.Robomendenapp.services.BuyerService;
import dk.robomenden.Robomendenapp.services.RobotService;
import dk.robomenden.Robomendenapp.services.WinterTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/robot")
public class RobotController {

	private final RobotService robotService;
	private final BuyerService buyerService;
	private final RobotUpdateListener robotUpdateListener;
	private final WinterTaskService winterTaskService;

	@Autowired
	public RobotController(BuyerService buyerService, RobotUpdateListener robotUpdateListener,
			RobotService robotService, WinterTaskService winterTaskService) {
		this.robotService = robotService;
		this.robotUpdateListener = robotUpdateListener;
		this.buyerService = buyerService;
		this.winterTaskService = winterTaskService;
	}

	// Создание нового робота (через страницу с покупателями)
	// Creating a new robot (via the customer page)
	@PostMapping()
	public String newRobot(@RequestParam("personId") String id) {
		Robot robot = new Robot();
		// Присваиваем роботу покупателя
		// Assigning a robot to a customer
		robotService.setRobotOwner(Integer.parseInt(id), robot);
		robot.setOwner(id);
		robotService.save(robot);
		// возвращаем идентификатор созданной сущности в JSON-формате
		return "redirect:/";
	}


	// Создание нового робота (через страницу с зимними заданиями)
	// Creating a new robot (via the winter tasks page)
	@PostMapping("/winterTaskRobot")
	public String createBuyer(@RequestParam("winterId") String winterId) {

		Robot robot = new Robot();
		// Присваиваем роботу winterTask
		// Assigning a winterTask to the robot
		robot.setWinterTask(winterTaskService.findById(Integer.parseInt(winterId)));
		// сохранение покупателя в базу данных
		// saving the customer into the database
		robotService.save(robot);
		// Присваиваем winterTask'у робота
		// Assigning winterTask to the robot
		winterTaskService.findById(Integer.parseInt(winterId)).setRobot(robotService.findById(robot.getId()));
		// Сохраняем изменения
		// Saving changes
		winterTaskService.update(Integer.parseInt(winterId), winterTaskService.findById(Integer.parseInt(winterId)));

		// возвращаем идентификатор созданной сущности в JSON-формате
		// return the identifier of the created entity in JSON format
		return "redirect:/";
	}

	// Страница для редактирования робота
	// Robot editing page
	@GetMapping("/{id}/editRobot")
	public String editRobot(@PathVariable("id") int id, Model model) {

		// Формат отображения дат
		// Date display format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		model.addAttribute("formattedDate", formatter);

		model.addAttribute("buyersList", buyerService.findAll());

		// Добавляем в модель робота, которого нужно отредактировать
		// Add the robot you want to edit to the model
		model.addAttribute("editedRobot", robotService.findById(id));
		// Добавляем в модель id этого робота
		// Add the id of this robot to the model
		model.addAttribute("robotId", id);

		// Добавляем id покупателя в модель если он есть у робота
		// Add the customer id to the model if the robot has one
		if (robotService.findById(id).getBuyer() != null) {
			model.addAttribute("id1", robotService.findById(id).getBuyer().getId());
		}

		// Добавляем проверку на наличие зимнего задания у робота
		// Adding a check for the robot's winter task
		if (robotService.findById(id).getWinterTask() == null) {
			model.addAttribute("yes", true);
		}

		// Все та же проверка на наличие зимнего задания у робота, что и сверху
		// Still the same check for the winter task for the robot as above
		if (robotService.findById(id).getWinterTask() != null) {
			model.addAttribute("id2", robotService.findById(id).getWinterTask().getId());
			model.addAttribute("no", false);
		}

		// Добавляем в модель логи об изменении полей робота
		// Add to the model logs about changes in the robot's fields
		model.addAttribute("RobotsLogs", robotService.findById(id).getLogEntryList());

		return "robot/edit";
	}


	// Страница для редактирования робота в winterTask'ах
	// Page for editing a robot in winterTasks
	@GetMapping("/winterTask/{id}/editRobot")
	public String editWinterTaskRobot(@PathVariable("id") int id, Model model) {

		model.addAttribute("buyersList", buyerService.findAll());

		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Добавляем в модель робота которого нужно отредактировать
		// Add the robot you want to edit to the model
		model.addAttribute("editedRobot", robotService.findById(id));

		// Получение имя пользователя у авторизированного пользователя
		// Getting the username from an authorized user
		model.addAttribute("person_username", personDetails.getUsername());
		// Формат отображения дат
		// Date display format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		model.addAttribute("formattedDate", formatter);

		// Добавляем id покупателя в модель если он есть у робота
		// Add the customer id to the model if the robot has one
		if (robotService.findById(id).getBuyer() != null) {
			model.addAttribute("id1", robotService.findById(id).getBuyer().getId());
		}

		// Все та же проверка на наличие зимнего задания у робота, что и сверху
		// Still the same check for the winter task for the robot as above
		if (robotService.findById(id).getWinterTask() != null) {
			model.addAttribute("id2", robotService.findById(id).getWinterTask().getId());
		}
		// Добавляем в модель логи робота
		// Adding robot logs to the model
		model.addAttribute("RobotsLogs", robotService.findById(id).getLogEntryList());

		return "winterRobot/edit";
	}

	@PostMapping("/{id}")
	public String edit(@PathVariable("id") int id, 
			@ModelAttribute("editedRobot") Robot robot,
			@RequestParam("ownerId") String owner_id, 
			@RequestParam("winterId") String winter_id,
			@RequestParam("taskId") String task_id) {

		if (robot.getBuyer() != null) {

			try {
				// Устанавливаем client'а для task'а
				// set the client for the task
				Optional<Buyer> buyer = buyerService.findById(Integer.parseInt(robot.getOwner()));

				if (buyer.isPresent()) {
					robot.setOwner(buyer.get().getId() + "");
					robot.setBuyer(buyer.get());
				}
			} catch (NumberFormatException e) {

			}
		}
		
		if (robot.getOwner() != null && !robot.getOwner().equals("")) {
			robot.setBuyer(buyerService.findById(Integer.parseInt(robot.getOwner())).get());
		}

		// Добавляем роботу зимнее задание
		// Adding a winter task to the robot
		if (!winter_id.equals("")) {
			robot.setWinterTask(winterTaskService.findById(Integer.parseInt(winter_id)));
		}

		// Логи робота
		// Robot Logs
		robotUpdateListener.onRobotUpdate(robot);

		

		// Редактирование робота
		// Editing the robot
		robotService.edit(id, robot);

		return "redirect:/robot/" + id + "/editRobot";
	}


	@PostMapping("/winterTask/{id}")
	public String winterTaskRobotEdit(@PathVariable("id") int id, @ModelAttribute("editedRobot") Robot robot,
			@RequestParam("ownerId") String owner_id, @RequestParam("winterId") String winter_id,
			@RequestParam("taskId") String task_id) {

		// Устанавливаем winterTask для робота
		// Setting the winterTask for the robot
		robot.setWinterTask(winterTaskService.findById(Integer.parseInt(winter_id)));

		if (robot.getOwner() != null) {

			try {
				// Устанавливаем client'а для task'а
				// set the client for the task
				Optional<Buyer> buyer = buyerService.findById(Integer.parseInt(robot.getOwner()));

				if (buyer.isPresent()) {
					robot.setOwner(buyer.get().getId() + "");
					robot.setBuyer(buyer.get());
				}
			} catch (NumberFormatException e) {

			}
		}

		// Добавляем роботу покупателя
		// Adding a customer robot
		if (!owner_id.equals("")) {
			robot.setBuyer(buyerService.findById(Integer.parseInt(owner_id)).get());
		}

		// Добавляем роботу зимнее задание
		// Adding a winter task to the robot
		if (!winter_id.equals("")) {
			robot.setWinterTask(winterTaskService.findById(Integer.parseInt(winter_id)));
		}

		// Логи робота
		// Robot Logs
		robotUpdateListener.onRobotUpdate(robot);

		// Логи робота
		// Robot Logs
		robotUpdateListener.onRobotUpdate(robot);
		// Редактирование робота
		// Editing the robot
		robotService.edit(id, robot);

		return "redirect:/robot/winterTask/" + id + "/editRobot";
	}

	// Метод для удаления робота
	// Method for removing the robot
	@PostMapping("/{id}/deleteRobot")
	public String delete(@PathVariable("id") int id, @RequestParam("clientId") String client_id) {

		Robot robot = robotService.findById(id);
		robot.setBuyer(null);
		robot.setOwner(null);
		robot.setWinterTask(null);

		robotService.deleteById(id);

		return "redirect:/buyer/" + Integer.parseInt(client_id) + "/editBuyer";
	}
}
