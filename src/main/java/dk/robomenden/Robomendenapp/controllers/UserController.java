package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.csv.CSVGenerator;
import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.models.User;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import dk.robomenden.Robomendenapp.services.RoleService;
import dk.robomenden.Robomendenapp.services.UserService;
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
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final RoleService roleService;
	private final CSVGenerator csvGenerator;

	@Autowired
	public UserController(UserService userService, RoleService roleService, CSVGenerator csvGenerator) {
		this.userService = userService;
		this.roleService = roleService;
		this.csvGenerator = csvGenerator;
	}

	// Метод для отображения списка user'ов
	// Method for displaying the list of users
	@GetMapping()
	public String users(Model model, @RequestParam(value = "page", required = false) String page) {
		// Добавляем в модель списков user'ов для отображения
		// Add user lists to the model to display
		model.addAttribute("userList", userService.findAll());
		// Получения данных об авторизированном пользователе
		// Adding a list of all winterTasks distributed on pages
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());
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
		// Проверка на доступ к добавлению пользователя у роли
		// Checking for access to add a user to a role
		model.addAttribute("userAdd", role.isUserAdd());
		// Проверка на доступ к редактированию пользователя у роли
		// Checking for editing access for a user on a role
		model.addAttribute("userEdit", role.isUserEdit());

		// Отображения winterTask'ов распределенных по страницам
		// Displaying winterTasks spread over pages
		if (page != null) {
			// Добавление списка всех buyer'ов распределенных по страницам
			// Adding a list of all buyers distributed on pages
			model.addAttribute("userList", userService.findAll(Integer.parseInt(page)));
			model.addAttribute("page", Integer.parseInt(page));
		}

		// Размер массива для определения количества страниц для пагинации
		// The size of the array to determine the number of pages for pagination
		model.addAttribute("size", userService.findAll().size());

		return "user/users";
	}

	// Метод для редактирования user'а
	// Method for editing a user
	@GetMapping("/{id}/edit")
	public String editUser(@ModelAttribute("id") User user, Model model) {
		// User которого нужно отредактировать
		// User to edit
		model.addAttribute("editedUser", user);
		// Получения данных об авторизированном пользователе
		// Retrieving data about an authorized user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		// Получение роли авторизированного пользователя
		// Getting an authorized user role
		Role role = roleService.findByRoleName(personDetails.getUser().getRole());

		// Передаю в модель список ролей для select'a
		model.addAttribute("roleList", roleService.findAll());

		// Проверка на доступ к редактированию пароля пользователя у роли
		// Checking for editing access for a user password on a role
		model.addAttribute("userEditPassword", role.isUserEditPassword());
		// Проверка на доступ к редактированию роли пользователя у роли
		// Checking for editing access for a user role on a role
		model.addAttribute("userEditRole", role.isUserEditRole());
		return "user/edit";
	}

	// Метод который, принимает данные user'а, чтобы сохранить изменения после
	// редактирования
	// A method that takes the user's data to save the changes after editing
	@PostMapping("/{id}")
	public String userEdit(@ModelAttribute("id") User user, @PathVariable("id") int id) {

		userService.edit(id, user);
		return "redirect:/user/" + id + "/edit";
	}

	// Метод для сохранения user'а
	// Method for saving a user
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> createBuyer(@RequestBody User user) {
		// сохранение покупателя в базу данных
		// saving the buyer into the database
		userService.save(user);
		// возвращаем идентификатор созданной сущности в JSON-формате
		// return the identifier of the created entity in JSON format
		return ResponseEntity.ok().body("{\"id\": " + user.getId() + "}");
	}

	// Метод для получения максимального ID user'а
	// A method to get the maximum ID of the user
	@GetMapping("/maxId")
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> getMaxId() {
		int maxId = userService.getMaxId();
		Map<String, Integer> response = new HashMap<>();
		response.put("id", maxId);
		return ResponseEntity.ok(response);
	}

	// Метод для загрузки таблицы в CSV-файл
	// Method for loading a table into a CSV file
	@GetMapping(value = "/downloadUsers", produces = "text/csv")
	public ResponseEntity<String> getPersonsAsCsv() {
		// Получение данных о персонах из базы данных
		// Retrieving personal data from the database
		List<User> users = userService.findAll();

		// Создание CSV-строки с помощью метода generateCsvString
		// Creating a CSV string using generateCsvString method
		String csvString = csvGenerator.generateCsvStringUser(users);

		// Возвращение CSV-строки в качестве ответа
		// Return a CSV string as a response
		String contentDisposition = "attachment; filename=user.csv";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(csvString);
	}

	// Метод для удаления user'а
	// Метод для удаления user'а
	@PostMapping("/delete")
	public String delete(@RequestParam("deleteIds") int[] ids) {

		for (int id : ids) {
			userService.deleteById(id);
		}
		return "redirect:/user";
	}

}
