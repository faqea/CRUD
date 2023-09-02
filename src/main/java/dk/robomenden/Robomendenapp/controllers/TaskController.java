package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.csv.CSVGenerator;
import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.Robot;
import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.models.Task;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import dk.robomenden.Robomendenapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;
	private final CSVGenerator csvGenerator;
	private final RoleService roleService;
	private final BuildMarketService buildMarketService;
	private final BuyerService buyerService;
	private final ProducerService producerService;
	private final RobotService robotService;
	private final UserService userService;

	@Autowired
	public TaskController(TaskService taskService, CSVGenerator csvGenerator, RoleService roleService,
			BuildMarketService buildMarketService, BuyerService buyerService, ProducerService producerService,
			RobotService robotService, UserService userService) {
		this.taskService = taskService;
		this.csvGenerator = csvGenerator;
		this.roleService = roleService;
		this.buildMarketService = buildMarketService;
		this.buyerService = buyerService;
		this.producerService = producerService;
		this.robotService = robotService;
		this.userService = userService;
	}

	@GetMapping()
	public String taskList(Model model, @RequestParam(value = "page", required = false) String page) {

		model.addAttribute("size", taskService.findAll().size());
		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());
		// Проверка на доступ у пользователя к созданию нового task'а
		// Checking for user access to create new task
		model.addAttribute("taskCreate", role.isTaskCreate());
		// Проверка на доступ у пользователя к удалению task'а
		// Checking for user access to delete task
		model.addAttribute("taskDelete", role.isTaskDelete());
		// Проверка на доступ у пользователя к открытию модального окна с
		// редактированием
		// Checking for user access to open edit modal window
		model.addAttribute("taskOpenEditModalWindow", role.isTaskOpenModalEdit());
		// Проверка на доступ у пользователя к редактированию task'а
		// Checking for user access to edit task'а
		model.addAttribute("taskEdit", role.isTaskEdit());
		// Получение имя пользователя у авторизированного пользователя
		// Getting the username from an authorized user
		model.addAttribute("person_username", personDetails.getUsername());
		// Проверка на доступ у пользователя к странице с зимними заданиями
		// Checking for user access to the winter tasks page
		model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
		// Проверка на доступ у пользователя к странице с пользователями
		// Checking for user access to the user page
		model.addAttribute("menuBarUser", role.isMenuBarUser());
		// Проверка на доступ у пользователя к странице с клиентами
		// Checking for user access to the clients page
		model.addAttribute("menuBarClient", role.isMenuBarClient());
		// Проверка на доступ у пользователя к странице с магазинами
		// Checking for user access to the stores page
		model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
		// Проверка на доступ у пользователя к странице с админкой
		// Checking for user access to the admin page
		model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
		// Проверка на доступ у пользователя к странице с производителями
		// Checking for user access to the manufacturers page
		model.addAttribute("menuBarProducer", role.isMenuBarProducer());
		// Список task'ов
		// Task list
		model.addAttribute("taskList", taskService.findAll());

		if (page != null) {
			// Список task'ов
			// Task list
			model.addAttribute("taskList", taskService.findAll(Integer.parseInt(page)));

			model.addAttribute("page", Integer.parseInt(page));
		}

		// Формат отображения дат
		// Date display format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		model.addAttribute("formattedDate", formatter);

		return "task/task";

	}

	// Метод для сохранения task'а
	// Method for saving the task
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> createBuyer(@RequestBody Task task, Model model) {

		// Устанавливаем текущую дату
		// Set the current date
		task.setOriginalDate(LocalDateTime.now());

		// сохранение task'а в базу данных
		// saving the task to the database
		taskService.save(task);
		// возвращаем идентификатор созданной сущности в JSON-формате
		// return the identifier of the created entity in JSON format
		return ResponseEntity.ok().body("{\"id\": " + task.getId() + "}");
	}

	// Метод для удаления task'а по ID
	// Method for deleting a task by ID
	@PostMapping("/delete")
	public String delete(@RequestParam("deleteIds") int[] ids) {
		for (int i : ids) {

			Robot robot = taskService.findById(i).getTask_robot();
			Buyer buyer = taskService.findById(i).getBuyer();
			if (robot != null) {
				robot.setTask(null);
			}

			if (buyer != null) {
				buyer.setTask(null);
			}

			taskService.deleteById(i);
		}
		return "redirect:/task";
	}

	// Метод для редактирования task'а по его ID
	// A method for editing a task by its ID
	@GetMapping("/{id}/editTask")
	public String editPage(Model model, @PathVariable("id") int id) {

		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

		// Получение имя пользователя у авторизированного пользователя
		// Getting the username from an authorized user
		model.addAttribute("person_username", personDetails.getUsername());

		// Получаем task по его ID
		// Get the task by its ID
		Task task = taskService.findById(id);

		// Добавляем в модель работников, чтобы присваивать их имена через в select
		model.addAttribute("installatorsList", userService.findAll());
		// Добавляем в модель список buildMarket'ов
		// Add a list of buildMarkets to the model
		model.addAttribute("buildMarketsList", buildMarketService.findAll());

		// Добавляем в модель список buyer'ов
		// Add a list of buyers to the model
		model.addAttribute("buyersList", buyerService.findAll());

		// Добавляем в модель список producer'ов
		// Add a list of producers to the model
		model.addAttribute("producersList", producerService.findAll());

		model.addAttribute("buyerList", buyerService.findAll());

		// Добавляем в модель список robot'ов
		// Add a list of robots to the model
		model.addAttribute("robotList", robotService.findAll());

		// Формат отображения дат
		// Date display format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		model.addAttribute("formattedDate", formatter);

		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());
		// Проверка на доступ у пользователя к редактированию task'а
		// Checking for user access to edit task
		model.addAttribute("taskEdit", role.isTaskEdit());
		// Проверка на доступ у пользователя к просмотру комментария task'а
		// Checking whether the user has access to view the task's comment
		model.addAttribute("commentCheck", role.isCommentCheck());

		// Передаем в модель task для редактирования
		// Passing the task to the model for editing
		model.addAttribute("editTask", task);
		// Передаем в модель ID task'а
		// Pass the task ID to the model
		model.addAttribute("id", id);
		// Получаем комментарии task'а
		// Getting the task comments
		model.addAttribute("comments", task.getComments());

		// Редактирование робота и клиента
		// Editing the robot and the client
		if (task.getTask_robot() != null && task.getBuyer() != null) {
			model.addAttribute("clientInfoY", true);
			model.addAttribute("viewAll", true);
			// Получаем робота, который принадлежит task'у
			// We get a robot that belongs to the task
			model.addAttribute("robot", task.getTask_robot());
			// Получаем buyer'а, который принадлежит task'у
			// We get the buyer, which belongs to the task
			model.addAttribute("buyer", task.getBuyer());
			model.addAttribute("buyerId", task.getBuyer().getId());
			model.addAttribute("robotId", task.getTask_robot().getId());
		}

		// Редактирование клиента и добавление робота
		// Editing the client and adding a robot
		if (task.getTask_robot() == null && task.getBuyer() != null) {
			model.addAttribute("clientInfoY", true);
			model.addAttribute("addRobot", true);
			// Получаем buyer'а, который принадлежит task'у
			// We get the buyer, which belongs to the task
			model.addAttribute("buyer", task.getBuyer());
			model.addAttribute("buyerId", task.getBuyer().getId());
		}

		// Редактирование робота и добавление клиента
		// Editing a robot and adding a client
		if (task.getTask_robot() != null && task.getBuyer() == null) {
			model.addAttribute("clientInfoN", true);
			model.addAttribute("viewRobot", true);
			// Получаем робота, который принадлежит task'у
			// We get a robot that belongs to the task
			model.addAttribute("robot", task.getTask_robot());
			model.addAttribute("robotId", task.getTask_robot().getId());

		}

		// Создание клиента и робота
		// Creating a client and a robot
		if (task.getBuyer() == null && task.getTask_robot() == null) {
			model.addAttribute("clientInfoN", true);
			model.addAttribute("addAll", true);
		}

		return "task/edit";
	}

	// Метод, который принимает данные task'а для его обновления
	// A method that takes the data of a task to update it
	@PostMapping("/{id}")
	public String edit(@ModelAttribute("editTask") Task task, @PathVariable("id") int id) {

		Task trueTask = taskService.findById(id);
		
		System.out.println("Я ТУТ: " + task.getClient());
		
		
		// Устанавливаем robot'а для task'а
		// set the robot for the task
		if (task.getRobot() != null && !task.getRobot().equals("")) {
			if (trueTask.getTask_robot() != null) {
				trueTask.getTask_robot().setTask(null);
			}

			// Находим робота по его ID переданного из select'а
			Robot robot1 = robotService.findById(Integer.parseInt(task.getRobot()));

			// Устанавливаем task для robot'а
			robot1.setTask(task);
			// Сохраняем изменения для robot'а
			robotService.edit(Integer.parseInt(task.getRobot()), robot1);
			// Устанавливаем имя robot'а для task'а
			task.setRobot(robot1.getId() + "");
			
		}
		

		// Устанавливаем client'а для task'а
		// set the client for the task
		if (task.getClient() != null && !task.getClient().equals("")) {
			System.out.println("Я TRUE: " + trueTask.getBuyer());
			System.out.println("Я ТУТ: " + task.getBuyer());

			if (trueTask.getBuyer() != null) {

				trueTask.getBuyer().setTask(null);
				trueTask.setBuyer(null);

			}

			// Получаем buyer'а по ID, который пришел нам из select'а
			Buyer buyer = buyerService.findById(Integer.parseInt(task.getClient())).get();
			// Устанавливаем клиента для task'а
			task.setClient(buyer.getId() + "");
			// Устанавливаем buyer'а для task'а
			task.setBuyer(buyer);
			// Устанавливаем ID task'а для buyer'а
			buyer.setTask(task);
			// Сохраняем изменения
			buyerService.edit(buyer.getId(), buyer);
			
		}
		
		if (task.getClient().equals("") && trueTask.getBuyer() != null) {
			trueTask.getBuyer().setTask(null);
		}
		
		if (task.getRobot().equals("") && trueTask.getTask_robot() != null) {
			trueTask.getTask_robot().setTask(null);
		}
		
		taskService.edit(id, task);
		return "redirect:/task/" + id + "/editTask";
	}

	// Метод для получения максимального ID user'а
	// A method to get the maximum ID of the user
	@GetMapping("/maxId")
	public ResponseEntity<Integer> getMaxId() {
		return ResponseEntity.ok(taskService.getMaxId());
	}

	// Метод для загрузки таблицы в CSV-файл
	// Method for loading a table into a CSV file
	@GetMapping(value = "/downloadTask", produces = "text/csv")
	public ResponseEntity<String> getPersonsAsCsv() {
		// Получение данных о персонах из базы данных
		// Retrieving personal data from the database
		List<Task> tasks = taskService.findAll();

		// Создание CSV-строки с помощью метода generateCsvString
		// Creating a CSV string using generateCsvString method
		String csvString = csvGenerator.generateCsvStringTask(tasks);

		// Возвращение CSV-строки в качестве ответа
		// Return a CSV string as a response
		String contentDisposition = "attachment; filename=tasks.csv";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(csvString);
	}

}
