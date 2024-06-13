package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.csv.CSVGenerator;
import dk.robomenden.Robomendenapp.models.*;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

	private final BuyerService buyerService;
	private final FileService fileService;
	private final WinterTaskService winterTaskService;
	private final CSVGenerator csvGenerator;
	private final RoleService roleService;
	private final RobotService robotService;

	@Autowired
	public BuyerController(FileService fileService, BuyerService buyerService, WinterTaskService winterTaskService,
			 CSVGenerator csvGenerator, RoleService roleService, RobotService robotService) {
		this.fileService = fileService;
		this.buyerService = buyerService;
		this.winterTaskService = winterTaskService;
		this.csvGenerator = csvGenerator;
		this.roleService = roleService;
		this.robotService = robotService;
	}

	// Страница со всеми buyer'ами
	// Page with all the buyers
	@GetMapping()
	public String buyers(Model model, @RequestParam(value = "page", required = false) String page) {

		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());

		// Получение имя пользователя у авторизированного пользователя
		// Getting the username from an authorized user
		model.addAttribute("person_username", personDetails.getUsername());
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
		// Проверка на доступ у пользователя к странице с пользователями
		// Checking for user access to the user page
		model.addAttribute("menuBarUser", role.isMenuBarUser());
		// Проверка на доступ у пользователя к странице с зимними заданиями
		// Checking for user access to the winter tasks page
		model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());

		// Проверка на доступ у пользователя к созданию нового buyer'а
		// Checking for user access to create a new buyer
		model.addAttribute("clientCreate",
				roleService.findByRoleName(personDetails.getUser().getRole()).isClientCreate());
		// Проверка на доступ у пользователя к удалению buyer'а
		// Checking for user access to delete buyer
		model.addAttribute("clientDelete",
				roleService.findByRoleName(personDetails.getUser().getRole()).isClientDelete());
		// Проверка на доступ у пользователя к редактированию buyer'а
		// Checking for user access to edit buyer
		model.addAttribute("clientEdit", roleService.findByRoleName(personDetails.getUser().getRole()).isClientEdit());
		// Передаем в модель список buyer'ов
		// Passing the list of buyers to the model
		model.addAttribute("buyersList", buyerService.findAll());

		// Отображения winterTask'ов распределенных по страницам
		// Displaying winterTasks spread over pages
		if (page != null) {
			// Добавление списка всех buyer'ов распределенных по страницам
			// Adding a list of all buyers distributed on pages
			model.addAttribute("buyersList", buyerService.findAll(Integer.parseInt(page)));
			model.addAttribute("page", Integer.parseInt(page));
		}

		// Размер массива для определения количества страниц для пагинации
		// The size of the array to determine the number of pages for pagination
		model.addAttribute("size", buyerService.findAll().size());

		return "buyer/buyers";
	}

	// Метод для сохранения покупателя в winterTask'ах
	// Method for saving the buyer in the winterTask
	@PostMapping("/winterBuyer")
	public String createWinterBuyer(@RequestParam("winterTaskId") String winterTaskId) {
		Buyer buyer = new Buyer();
		// сохранение покупателя в базу данных
		// saving the customer into the database
		buyerService.save(buyer);
		// Устанавливаем buyer'а winterTask'у
		// Setting up the winterTask
		winterTaskService.findById(Integer.parseInt(winterTaskId)).setClient("" + buyer.getId());
		// Обновляем данные winterTask'а
		// Updating winterTask data
		winterTaskService.update(Integer.parseInt(winterTaskId),
				winterTaskService.findById(Integer.parseInt(winterTaskId)));
		// Возвращаем идентификатор созданной сущности в JSON-формате
		// Return the identifier of the created entity in JSON format
		return "redirect:/";
	}

	// Создание нового покупателя
	// Creating a new buyer
	@PostMapping()
	public String createBuyer() {
		Buyer buyer = new Buyer();
		// сохранение покупателя в базу данных
		// saving the customer into the database
		buyerService.save(buyer);
		return "redirect:/buyer";
	}

	// Страница для редактировния buyer'а
	// Page for editing the buyer
	@GetMapping("/{id}/editBuyer")
	public String editBuyer(@PathVariable("id") int id, Model model) {

		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

		// Формат отображения дат
		// Date display format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		model.addAttribute("formattedDate", formatter);

		// Получение имя пользователя у авторизированного пользователя
		// Getting the username from an authorized user
		model.addAttribute("person_username", personDetails.getUsername());

		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());

		// Проверка на доступ у пользователя к созданию нового комментария
		// Checking for user access to create a new comment
		model.addAttribute("clientCommentWrite", role.isClientWriteComments());

		// Проверка на доступ у пользователя к удалению комментариев
		// Checking for user access to delete a comment
		model.addAttribute("clientCommentEdit", role.isClientCommentEdit());

		// Проверка на доступ у пользователя к добавлению файлов
		// Checking for user access to add files
		model.addAttribute("clientGalleryAddFiles", role.isClientGalleryAddFiles());

		// Проверка на доступ у пользователя к просмотру файлов
		// Checking for user access to view files
		model.addAttribute("clientGalleryOpenFiles", role.isClientGalleryOpenFiles());

		// Проверка на доступ у пользователя к удалению файлов
		// Checking for user access to delete files
		model.addAttribute("clientGalleryDeleteFiles", role.isClientGalleryDeleteFiles());

		// Проверка на доступ у пользователя к добавлению робота
		// Checking for user access to add a robot
		model.addAttribute("robotClientAdd", role.isRobotClientAdd());

		// Проверка на доступ у пользователя к редактированию робота
		// Checking for user access to edit the robot
		model.addAttribute("robotClientEdit", role.isRobotClientEdit());

		// Проверка на доступ у пользователя к удалению робота
		// Checking for user access to delete the robot
		model.addAttribute("robotClientDelete", role.isRobotClientDelete());

		// Проверка на доступ у пользователя к просмотру комментария
		// Checking for user access to view a comment
		model.addAttribute("commentCheck", role.isCommentCheck());

		// Получаем покупателя по ID
		// Getting a Buyer by ID
		Buyer buyer = buyerService.findById(id).get();



		// Получаем файлы buyer'а и передаем его в модель
		// Get the buyer's files and transfer them to the model
		model.addAttribute("files", buyer.getFiles());
		// Получаем робота buyer'а и передаем его в модель
		// We get the buyer's robot and transfer it to the model
		model.addAttribute("buyersRobotList", buyer.getRobots());
		// Передаем id buyer'а в модель
		// Passing the buyer's id to the model
		model.addAttribute("buyerId", id);
		// Передаем в модель buyer'а для редактирования
		// Passing to the buyer's model for editing
		model.addAttribute("editedBuyer", buyer);
		// Передаем в модель комментарии buyer'а
		// Passing the buyer's comments to the model
		model.addAttribute("comments", buyer.getComments());
		return "buyer/edit";
	}

	// Страница для редактировния buyer'а
	// Page for editing the buyer
	@GetMapping("/task/{id}/editBuyer")
	public String taskEditBuyer(@PathVariable("id") int id, Model model) {

		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

		// Формат отображения дат
		// Date display format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		model.addAttribute("formattedDate", formatter);

		// Получение имя пользователя у авторизированного пользователя
		// Getting the username from an authorized user
		model.addAttribute("person_username", personDetails.getUsername());

		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());

		// Проверка на доступ у пользователя к созданию нового комментария
		// Checking for user access to create a new comment
		model.addAttribute("clientCommentWrite", role.isClientWriteComments());

		// Проверка на доступ у пользователя к удалению комментариев
		// Checking for user access to delete a comment
		model.addAttribute("clientCommentEdit", role.isClientCommentEdit());

		// Проверка на доступ у пользователя к добавлению файлов
		// Checking for user access to add files
		model.addAttribute("clientGalleryAddFiles", role.isClientGalleryAddFiles());

		// Проверка на доступ у пользователя к просмотру файлов
		// Checking for user access to view files
		model.addAttribute("clientGalleryOpenFiles", role.isClientGalleryOpenFiles());

		// Проверка на доступ у пользователя к удалению файлов
		// Checking for user access to delete files
		model.addAttribute("clientGalleryDeleteFiles", role.isClientGalleryDeleteFiles());

		// Проверка на доступ у пользователя к добавлению робота
		// Checking for user access to add a robot
		model.addAttribute("robotClientAdd", role.isRobotClientAdd());

		// Проверка на доступ у пользователя к редактированию робота
		// Checking for user access to edit the robot
		model.addAttribute("robotClientEdit", role.isRobotClientEdit());

		// Проверка на доступ у пользователя к удалению робота
		// Checking for user access to delete the robot
		model.addAttribute("robotClientDelete", role.isRobotClientDelete());

		// Проверка на доступ у пользователя к просмотру комментария
		// Checking for user access to view a comment
		model.addAttribute("commentCheck", role.isCommentCheck());

		// Получаем покупателя по ID
		// Getting a Buyer by ID
		Buyer buyer = buyerService.findById(id).get();


		// Получаем файлы buyer'а и передаем его в модель
		// Get the buyer's files and transfer them to the model
		model.addAttribute("files", buyer.getFiles());
		// Получаем робота buyer'а и передаем его в модель
		// We get the buyer's robot and transfer it to the model
		model.addAttribute("buyersRobotList", buyer.getRobots());
		// Передаем id buyer'а в модель
		// Passing the buyer's id to the model
		model.addAttribute("buyerId", id);
		// Передаем в модель buyer'а для редактирования
		// Passing to the buyer's model for editing
		model.addAttribute("editedBuyer", buyer);
		// Передаем в модель комментарии buyer'а
		// Passing the buyer's comments to the model
		model.addAttribute("comments", buyer.getComments());
		return "taskBuyer/edit";
	}


	// Метод для сохранения обновленных данных
	// Method for saving updated data
	@PostMapping("/{id}")
	public String edit(@PathVariable("id") int id, @ModelAttribute("buyer") Buyer buyer,
			@RequestParam(value = "taskId", required = false) String task_id) {

		buyerService.edit(id, buyer);
		return "redirect:/buyer/" + id + "/editBuyer";
	}

	// Метод для загрузки файла для buyer'а
	// Method for downloading a file for the buyer
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("clientId") String clientId,
			RedirectAttributes redirectAttributes) {

		// Проверяем, что файл был загружен
		// Check that the file has been uploaded
		if (file.isEmpty()) {
			return "redirect:/buyer/" + Integer.parseInt(clientId) + "/editBuyer";
		}

		try {
			// Сохраняем файл на сервере
			// Save the file on the server
			byte[] bytes = file.getBytes();
			Path path = Paths.get("C:\\CRUD-master\\CRUD-master\\src\\main\\java\\dk\\robomenden\\Robomendenapp\\uploads" + file.getOriginalFilename());
			Files.write(path, bytes);

			// Сохраняем информацию о файле и клиенте в базе данных
			// Save file and client information in the database
			Buyer client = buyerService.findById(Integer.parseInt(clientId)).get();

			File uploadedFile = new File();
			uploadedFile.setFile_name(file.getOriginalFilename());
			uploadedFile.setPath(path.toString());
			uploadedFile.setOwner(client);
			fileService.save(uploadedFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/buyer/" + Integer.parseInt(clientId) + "/editBuyer";
	}

	// Метод для удаления покупателя
	// Method for removing the buyer
	@PostMapping("/delete")
	public String delete(@RequestParam("deleteIds") int[] ids) {

		for (int i : ids) {

			Optional<WinterTask> winterTask = winterTaskService.findByClient("" + i);


			for (Robot robot : buyerService.findById(i).get().getRobots()) {
				robot.setBuyer(null);
				robot.setWinterTask(null);
				robotService.edit(robot.getId(), robot);
			}

			if (winterTask.isPresent()) {
				winterTask.get().setClient("0");
			}

			buyerService.delete(i);
		}

		return "redirect:/buyer";
	}

	// Метод для загрузки таблицы в CSV-файл
	// Method for loading a table into a CSV file
	@GetMapping(value = "/downloadBuyer", produces = "text/csv")
	public ResponseEntity<String> getPersonsAsCsv() {
		// Получение данных о персонах из базы данных
		// Retrieving personal data from the database
		List<Buyer> buyers = buyerService.findAll();

		// Создание CSV-строки с помощью метода generateCsvString
		// Creating a CSV string using generateCsvString method
		String csvString = csvGenerator.generateCsvStringBuyer(buyers);

		// Возвращение CSV-строки в качестве ответа
		// Return a CSV string as a response
		String contentDisposition = "attachment; filename=buyers.csv";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(csvString);
	}

}
