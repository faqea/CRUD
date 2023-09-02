package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.csv.CSVGenerator;
import dk.robomenden.Robomendenapp.models.BuildMarket;
import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import dk.robomenden.Robomendenapp.services.BuildMarketService;
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
@RequestMapping("/buildMarket")
public class BuildMarketController {

	private final BuildMarketService buildMarketService;
	private final CSVGenerator csvGenerator;
	private final RoleService roleService;

	@Autowired
	public BuildMarketController(BuildMarketService buildMarketService, CSVGenerator csvGenerator,
			RoleService roleService) {
		this.buildMarketService = buildMarketService;
		this.csvGenerator = csvGenerator;
		this.roleService = roleService;
	}

	@GetMapping()
	public String buildMarket(Model model, @RequestParam(value = "page", required = false) String page) {

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

		// Добавляем в модель список market'ов
		// Add a list of markets to the model
		model.addAttribute("marketsList", buildMarketService.findAll());

		// Отображения winterTask'ов распределенных по страницам
		// Displaying winterTasks spread over pages
		if (page != null) {
			// Добавление списка всех buyer'ов распределенных по страницам
			// Adding a list of all buyers distributed on pages
			model.addAttribute("marketsList", buildMarketService.findAll(Integer.parseInt(page)));
			model.addAttribute("page", Integer.parseInt(page));
		}

		// Размер массива для определения количества страниц для пагинации
		// The size of the array to determine the number of pages for pagination
		model.addAttribute("size", buildMarketService.findAll().size());

		return "buildMarket/buildMarkets";
	}

	// Страница для редактирования market'а
	// Page for editing the market
	@GetMapping("/{id}/editMarket")
	public String editMarket(@PathVariable("id") int id, Model model) {
		// Добавляем в модель market для редактирования
		// Add to the market model for editing
		model.addAttribute("editedMarket", buildMarketService.findById(id));
		return "buildMarket/edit";
	}

	// Метод для сохранения обновленных данных
	// Method for saving updated data
	@PostMapping("/{id}")
	public String edit(@PathVariable("id") int id, @ModelAttribute("editedMarket") BuildMarket buildMarket) {
		buildMarketService.edit(id, buildMarket);
		return "redirect:/buildMarket/" + id + "/editMarket";
	}

	// Метод для удаления market'а
	// Method for removing the market
	@PostMapping("/delete")
	public String delete(@RequestParam("deleteIds") int[] ids) {
		for (int i : ids) {
			buildMarketService.delete(i);
		}
		return "redirect:/buildMarket";
	}

	// Метод для создания нового market'а
	// Method for creating a new market
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> createBuyer(@RequestBody BuildMarket buildMarket) {
		// сохранение покупателя в базу данных
		// saving the customer into the database
		buildMarketService.save(buildMarket);
		// return the identifier of the created entity in JSON format
		return ResponseEntity.ok().body("{\"id\": " + buildMarket.getId() + "}");
	}

	// Метод для получения максимального ID
	// A method to get the maximum ID
	@GetMapping("/maxId")
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> getMaxId() {
		int maxId = buildMarketService.getMaxId();
		Map<String, Integer> response = new HashMap<>();
		response.put("id", maxId);
		return ResponseEntity.ok(response);
	}

	// Метод для загрузки таблицы в CSV-файл
	// Method for loading a table into a CSV file
	@GetMapping(value = "/downloadBuildMarket", produces = "text/csv")
	public ResponseEntity<String> getPersonsAsCsv() {
		// Получение данных о персонах из базы данных
		// Retrieving personal data from the database
		List<BuildMarket> buildMarkets = buildMarketService.findAll();

		// Создание CSV-строки с помощью метода generateCsvString
		// Creating a CSV string using generateCsvString method
		String csvString = csvGenerator.generateCsvStringBuildMarket(buildMarkets);

		// Возвращение CSV-строки в качестве ответа
		// Return a CSV string as a response
		String contentDisposition = "attachment; filename=buildMarket.csv";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(csvString);
	}
}
