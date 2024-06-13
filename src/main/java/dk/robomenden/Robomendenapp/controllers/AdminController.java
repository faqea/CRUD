package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import dk.robomenden.Robomendenapp.services.PersonDetailsServices;
import dk.robomenden.Robomendenapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/bigBoss")
    public String admin(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());

        return "admin/admin";
    }

    @GetMapping("/installator")
    public String install(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
        return "admin/installator";
    }

    @GetMapping("/producent")
    public String producent(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
        return "admin/producent";
    }

    @GetMapping("/byggemarked")
    public String byggemarked(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
        return "admin/byggemarked";
    }

    @GetMapping("/forhandlerFormular")
    public String forhandlerFormular(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
        return "admin/forhandlerFormular";
    }

    @GetMapping("/superbruger")
    public String superbruger(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
        return "admin/superbruger";
    }


    @GetMapping("/rolesPage")
    public String rolesPage(Model model) {
        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());
        //Проверка на доступ у пользователя к странице с клиентами
        //Checking for user access to the clients page
        model.addAttribute("menuBarClient", role.isMenuBarClient());
        //Проверка на доступ у пользователя к странице с магазинами
        //Checking for user access to the stores page
        model.addAttribute("menuBarBuildMarket", role.isMenuBarBuildMarket());
        //Проверка на доступ у пользователя к странице с админкой
        //Checking for user access to the admin page
        model.addAttribute("menuBarAdmin", role.isMenuBarAdmin());
        //Проверка на доступ у пользователя к странице с производителями
        //Checking for user access to the manufacturers page
        model.addAttribute("menuBarProducer", role.isMenuBarProducer());
        //Проверка на доступ у пользователя к странице с пользователями
        //Checking for user access to the user page
        model.addAttribute("menuBarUser", role.isMenuBarUser());
        //Проверка на доступ у пользователя к странице с зимними заданиями
        //Checking for user access to the winter tasks page
        model.addAttribute("menuBarWinterTask", role.isMenuBarWinterTask());
        return "admin/rolesPage";
    }

    //Метод для установки доступа для создания task'ов
    //Method for setting access to create tasks
    @PostMapping("/admin1")
    public String updateValue1(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(1);

        role.setTaskCreate(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для удаления task'ов
    //Method for setting access to delete tasks
    @PostMapping("/admin2")
    public String updateValue2(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(1);

        role.setTaskDelete(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для открытия окна редактирования task'ов
    //A method for setting access to open the task edit window
    @PostMapping("/admin3")
    public String updateValue3(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setTaskOpenModalEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования task'ов
    //A method for setting access to edit tasks
    @PostMapping("/admin4")
    public String updateValue4(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setTaskEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для создания buyer'ов
    //A method for setting access to create buyers
    @PostMapping("/admin5")
    public String updateValue5(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientCreate(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для удаления buyer'ов
    //A method for setting access to delete buyers
    @PostMapping("/admin6")
    public String updateValue6(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientDelete(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования buyer'ов
    //A method for setting access to edit buyers
    @PostMapping("/admin7")
    public String updateValue7(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования buyer'ов
    //A method for setting access to edit buyers
    @PostMapping("/admin8")
    public String updateValue8(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientWriteComments(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для удаления комментариев buyer'ов
    //A method for setting access to delete comments
    @PostMapping("/admin9")
    public String updateValue9(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientCommentEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для добавления файлов buyer'у
    //A method to set access for adding files
    @PostMapping("/admin10")
    public String updateValue10(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientGalleryAddFiles(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для просмотра файлов buyer'а
    //A method for setting up access to view your files
    @PostMapping("/admin11")
    public String updateValue11(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientGalleryOpenFiles(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для удаления файлов buyer'а
    //A method for setting up access to delete files
    @PostMapping("/admin12")
    public String updateValue12(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setClientGalleryDeleteFiles(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для добавления роботов buyer'у
    //A method for setting access to add robots
    @PostMapping("/admin13")
    public String updateValue13(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setRobotClientAdd(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования роботов buyer'а
    //A method for setting access for editing robots
    @PostMapping("/admin14")
    public String updateValue14(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setRobotClientEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для удаления роботов buyer'а
    //A method for setting access for delete robots
    @PostMapping("/admin15")
    public String updateValue15(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setRobotClientDelete(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для создания и редактирования winterTask'а
    //A method for setting access to create and edit the winterTask
    @PostMapping("/admin16")
    public String updateValue16(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setWinterTaskAddAndEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа к странице с buyer'ами
    //A method for setting up access to the page with the buyers
    @PostMapping("/admin17")
    public String updateValue17(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setMenuBarClient(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа к странице с market'ами
    //A method for setting up access to the page with the markets
    @PostMapping("/admin18")
    public String updateValue18(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setMenuBarBuildMarket(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа к странице с producer'ами
    //A method for setting up access to the page with the producers
    @PostMapping("/admin19")
    public String updateValue19(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setMenuBarProducer(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа к странице с winterTask'ами
    //A method for setting up access to the page with the winterTasks
    @PostMapping("/admin20")
    public String updateValue20(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setMenuBarWinterTask(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа к странице с админкой
    //Method for setting access to the admin page
    @PostMapping("/admin21")
    public String updateValue21(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setMenuBarAdmin(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа к странице с user'ами
    //A method for setting up access to the page with the users
    @PostMapping("/admin22")
    public String updateValue22(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setMenuBarUser(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для добавления user'а
    //Method for setting access to add a user
    @PostMapping("/admin23")
    public String updateValue23(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setUserAdd(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования user'а
    //Method for setting access to edit a user
    @PostMapping("/admin24")
    public String updateValue24(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setUserEdit(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования пароля user'а
    //Method for setting access to edit the user's password
    @PostMapping("/admin25")
    public String updateValue25(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setUserEditPassword(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для редактирования роли user'а
    //Method for setting access to edit the user's role
    @PostMapping("/admin26")
    public String updateValue26(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setUserEditRole(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }

    //Метод для установки доступа для просмотра комментариев user'а
    //Method for setting access to view user's comments
    @PostMapping("/admin27")
    public String updateValue27(@RequestParam int id, @RequestParam Boolean isChecked) {

        Role role = roleService.findById(id);

        role.setCommentCheck(isChecked);
        roleService.edit(id, role);

        return "redirect:/admin";
    }
}
