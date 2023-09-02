package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.csv.CSVGenerator;
import dk.robomenden.Robomendenapp.models.Producer;
import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.models.WinterTask;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import dk.robomenden.Robomendenapp.services.ProducerService;
import dk.robomenden.Robomendenapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/producer")
public class ProducerController {

	private final ProducerService producerService;
	private final RoleService roleService;
	private final CSVGenerator csvGenerator;

	@Autowired
	public ProducerController(ProducerService producerService, RoleService roleService, CSVGenerator csvGenerator) {
		this.producerService = producerService;
		this.roleService = roleService;
		this.csvGenerator = csvGenerator;
	}

	@GetMapping()
	public String producersPage(Model model, @RequestParam(value = "page", required = false) String page) {
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
		// Передаем в модель список producer'ов
		// Pass the list of producers to the model
		model.addAttribute("producers", producerService.findAll());

		// Отображения winterTask'ов распределенных по страницам
		// Displaying winterTasks spread over pages
		if (page != null) {
			// Добавление списка всех buyer'ов распределенных по страницам
			// Adding a list of all buyers distributed on pages
			model.addAttribute("producers", producerService.findAll(Integer.parseInt(page)));
			model.addAttribute("page", Integer.parseInt(page));
		}

		// Размер массива для определения количества страниц для пагинации
		// The size of the array to determine the number of pages for pagination
		model.addAttribute("size", producerService.findAll().size());

		return "producer/producers";
	}

	// Метод для сохранения producer'а в базу данных
	// A method for saving the producer to the database
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> createProducer(@RequestBody Producer producer) {
		// сохранение покупателя в базу данных
		// saving the customer into the database
		producerService.save(producer);
		// возвращаем идентификатор созданной сущности в JSON-формате
		// return the identifier of the created entity in JSON format
		return ResponseEntity.ok().body("{\"id\": " + producer.getId() + "}");
	}

	// Метод для получения максимального ID
	// A method to get the maximum ID
	@GetMapping("/maxId")
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> getMaxId() {
		int maxId = producerService.getMaxId();
		Map<String, Integer> response = new HashMap<>();
		response.put("id", maxId);
		return ResponseEntity.ok(response);
	}

	// Метод для удаления producer'а
	// Method for removing the producer
	@PostMapping("/delete")
	public String delete(@RequestParam("deleteIds") int[] ids) {
		for (int i : ids) {
			producerService.delete(i);
		}
		return "redirect:/producer";
	}

	// Страница для редактирования producer'а
	// Page for editing the producer
	@GetMapping("/{id}/editProducer")
	public String editProducer(@PathVariable("id") int id, Model model) {
		model.addAttribute("editedProducer", producerService.findById(id));
		return "producer/edit";
	}

	// Метод для сохранения изменений
	// Method for saving changes
	@PostMapping("/{id}")
	public String edit(@ModelAttribute("producer") Producer producer, @PathVariable("id") int id) {
		producerService.edit(id, producer);
		return "redirect:/producer/" + id + "/editProducer";
	}

	// Метод для загрузки таблицы в CSV-файл
	// Method for loading a table into a CSV file
	@GetMapping(value = "/downloadProducer", produces = "text/csv")
	public ResponseEntity<String> getPersonsAsCsv() {
		// Получение данных о персонах из базы данных
		// Retrieving personal data from the database
		List<Producer> producers = producerService.findAll();

		// Создание CSV-строки с помощью метода generateCsvString
		// Creating a CSV string using generateCsvString method
		String csvString = csvGenerator.generateCsvStringProducer(producers);

		// Возвращение CSV-строки в качестве ответа
		// Return a CSV string as a response
		String contentDisposition = "attachment; filename=producer.csv";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(csvString);
	}

}
