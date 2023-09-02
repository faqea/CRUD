package dk.robomenden.Robomendenapp.controllers;


import dk.robomenden.Robomendenapp.csv.CSVGenerator;
import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.Robot;
import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.models.WinterTask;
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

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/winter")
public class WinterTaskController {

    private final WinterTaskService winterTaskService;
    private final RobotService robotService;
    private final BuyerService buyerService;
    private final CSVGenerator csvGenerator;
    private final RoleService roleService;
    private final UserService userService;
    private final BuildMarketService buildMarketService;

    @Autowired
    public WinterTaskController(WinterTaskService winterTaskService, RobotService robotService, BuyerService buyerService, CSVGenerator csvGenerator, RoleService roleService, UserService userService, BuildMarketService buildMarketService) {
        this.winterTaskService = winterTaskService;
        this.robotService = robotService;
        this.buyerService = buyerService;
        this.csvGenerator = csvGenerator;
        this.roleService = roleService;
        this.userService = userService;
        this.buildMarketService = buildMarketService;
    }

    //Страница со всеми winterTask'ами
    //Page with all winterTasks
    @GetMapping()
    public String taskTable(Model model, @RequestParam(value = "page", required = false) String page) {

        //Отображение всех  winterTask'ов на одной странице
        //Display all winterTasks on one page
        if (page == null) {
            //Добавление списка всех winterTask'ов в модель для отображения их на странице
            //Adding a list of all winterTasks to the model to display them on the page
            model.addAttribute("winterTaskList", winterTaskService.findAll());

        }

        //Отображения winterTask'ов распределенных по страницам
        //Displaying winterTasks spread over pages
        if (page != null) {
            //Добавление списка всех winterTask'ов распределенных по страницам
            //Adding a list of all winterTasks distributed on pages
            model.addAttribute("winterTaskList", winterTaskService.findAll(Integer.parseInt(page)));

            model.addAttribute("page", Integer.parseInt(page));
        }

        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        //Получение роли авторизированного пользователя
        //Getting an authorized user role
        Role role = roleService.findByRoleName(personDetails.getUser().getRole());
        //Проверка на доступ у пользователя к созданию нового winterTask'а и его редактирование
        //Checking for user access to create and edit a new winterTask
        model.addAttribute("winterTaskAddAndEdit", role.isWinterTaskAddAndEdit());
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
        //Размер массива для определения количества страниц для пагинации
        //The size of the array to determine the number of pages for pagination
        model.addAttribute("size", winterTaskService.findAll().size());

        //Формат отображения дат
        //Date display format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        model.addAttribute("formattedDate", formatter);

        return "winterService/winter";

    }

    //Метод для загрузки таблицы в CSV-файл
    //Method for loading a table into a CSV file
    @GetMapping(value = "/downloadWinter", produces = "text/csv")
    public ResponseEntity<String> getPersonsAsCsv() {
        // Получение данных о персонах из базы данных
        // Retrieving personal data from the database
        List<WinterTask> winterTasks = winterTaskService.findAll();

        // Создание CSV-строки с помощью метода generateCsvString
        // Creating a CSV string using generateCsvString method
        String csvString = csvGenerator.generateCsvStringWinterTask(winterTasks);

        // Возвращение CSV-строки в качестве ответа
        // Return a CSV string as a response
        String contentDisposition = "attachment; filename=winterTasks.csv";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(csvString);
    }

    //Метод для сохранения нового winterTask'а в таблицу
    //Method for saving a new winterTask to a table
    @PostMapping()
    @ResponseBody
    public ResponseEntity<?> createBuyer(@RequestBody WinterTask winterTask) {
        // Сохранение winterTask'а в базу данных
        // Saving the winterTask to the database
        winterTaskService.save(winterTask);
        // Возвращаем идентификатор созданной сущности в JSON-формате
        // Return the identifier of the created entity in JSON format
        return ResponseEntity.ok().body("{\"id\": " + winterTask.getId() + "}");
    }


    //Метод для удаления winterTask'а из таблицы
    //A method for removing winterTask from the table
    @PostMapping("/delete")
    public String deleteSelectedTasks(@RequestParam("deleteIds") int[] ids) {
        for (int id : ids) {
            Robot robot = winterTaskService.findById(id).getRobot();
            if (robot != null) {
                robot.setWinterTask(null);
            }
            winterTaskService.deleteById(id);
        }
        return "redirect:/winter";
    }

    //Страница с формой редактирования, которая отображается с помощью js кода в модальном окне
    //Page with an edit form that is displayed using js code in a modal window
    @GetMapping("/{id}/editTask")
    public String editTaskPage(Model model, @PathVariable("id") int id,
                               @RequestParam(value = "id", required = false) String robot_id) {

        //Получения данных об авторизированном пользователе
        //Retrieving data about an authorized user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        //Получение имя пользователя у авторизированного пользователя
        //Getting the username from an authorized user
        model.addAttribute("person_username", personDetails.getUsername());

        //Получаем winterTask по его ID
        //Get winterTask by its ID
        WinterTask winterTask = winterTaskService.findById(id);

        //WinterTask, который нужно редактировать
        //WinterTask to be edited
        model.addAttribute("editWinterTask", winterTask);

        //Формат отображения дат
        //Date display format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        model.addAttribute("formattedDate", formatter);

        //Проверка на наличие ID робота для назначения робота winterTask'у
        //Checking for robot ID to assign a robot to the winterTask
        if (robot_id != null && !robot_id.equals("null") && !robot_id.equals("")) {

            //Проверка на наличие winterTask'а у робота
            //Checking for winterTask in the robot
            if (robotService.findById(Integer.parseInt(robot_id)).getWinterTask() == null) {

                //Если все условия true, то назначаем winterTask'у робота, покупателя
                //If all conditions are true, we assign a robot, a buyer to the winterTask

                //Получаем робота по его ID
                //Get the robot by its ID
                Robot robot = robotService.findById(Integer.parseInt(robot_id));
                //Получаем покупателя, которому принадлежит робот
                //Get the customer who owns the robot
                Buyer buyer = robot.getBuyer();

                //Устанавливаем winterTask роботу
                //Installing winterTask for the robot
                robot.setWinterTask(winterTask);
                //Устанавливаем робота winterTask'у
                //Setting up a robot for the winterTask
                winterTask.setRobot(robot);

                if (buyer != null) {

                    //Устанавливаем покупателя winterTask'у
                    //Setting up a buyer for the winterTask
                    winterTask.setClient("" + robot.getBuyer().getId());
                    //Устанавливаем имя покупателя winterTask'у
                    //Setting up a buyer name for the winterTask
                    winterTask.setClientName(buyer.getName());
                    //Устанавливаем почтовый индекс покупателя winterTask'у
                    //Setting up a buyer post number for the winterTask
                    if (buyer.getPost_index() != null && !buyer.getPost_index().equals("")) {
                        winterTask.setPostNumber(Long.parseLong(buyer.getPost_index()));
                    }
                    //Устанавливаем город покупателя winterTask'у
                    //Setting up a buyer post number for the winterTask
                    winterTask.setCity(buyer.getCity());
                    //Устанавливаем номер телефона покупателя winterTask'у
                    //Setting up a buyer phone number for the winterTask
                    winterTask.setClientPhoneNumber(buyer.getPhone_number());
                    //ID покупателя
                    //Buyer ID
                    model.addAttribute("id1", buyer.getId());
                }
                //Сохраняем winterTask
                //Saving the winterTask
                winterTaskService.save(winterTask);

            }
        }


        //Просмотр клиента и робота
        //View client and robot

        //Если есть клиент и есть робот, то появляются кнопки со страницами для их редактирования
        //If there is a client and there is a robot, then there are buttons with pages to edit them
        if (winterTask.getClient() != null) {
            Optional<Buyer> buyer = buyerService.findById(Integer.parseInt(winterTask.getClient()));
            if (winterTask.getClient() != null && winterTask.getRobot() != null) {
                if (buyer.isPresent()) {

                    //Устанавливаем телефон, имя и почту клиента winterTask'у
                    winterTask.setClientName(buyer.get().getName());
                    winterTask.setClientPhoneNumber(buyer.get().getPhone_number());
                    winterTask.setClientEmail(buyer.get().getEmail());

                    //Сохраняем изменения
                    winterTaskService.update(id, winterTask);

                    model.addAttribute("defaultCondition", true);
                    //Получаем робота, который принадлежит winterTask'у
                    //We get a robot that belongs to winterTask
                    model.addAttribute("robot", winterTask.getRobot());
                    //ID winterTask'а
                    //WinterTask ID
                    model.addAttribute("winterId", id);
                    //Получаем buyer'а через winterTask
                    //Getting the buyer from winterTask

                    model.addAttribute("buyer", buyerService.findById(Integer.parseInt(winterTask.getClient())).get());

                    //Получаем логи робота
                    //Getting the robot logs
                    model.addAttribute("logs", winterTask.getRobot().getLogEntryList());
                }

                if (!buyer.isPresent()) {
                    winterTask.setClient(null);
                }

            }
        }


        //Просмотр клиента и создание робота
        //Viewing a client and creating a robot
        if (winterTask.getClient() != null) {
            Optional<Buyer> buyer = buyerService.findById(Integer.parseInt(winterTask.getClient()));
            if (winterTask.getClient() != null && winterTask.getRobot() == null) {
                if (buyer.isPresent()) {

                    //Устанавливаем телефон, имя и почту клиента winterTask'у
                    winterTask.setClientName(buyer.get().getName());
                    winterTask.setClientPhoneNumber(buyer.get().getPhone_number());
                    winterTask.setClientEmail(buyer.get().getEmail());

                    //Сохраняем изменения
                    winterTaskService.update(id, winterTask);

                    //Получаем buyer'а через winterTask
                    //Getting the buyer from winterTask
                    model.addAttribute("buyer", buyerService.findById(Integer.parseInt(winterTask.getClient())).get());

                    model.addAttribute("buyerWindowOnly", true);
                }

                if (!buyer.isPresent()) {
                    winterTask.setClient(null);
                }
            }
        }

        //Просмотр робота и создание клиента
        //Viewing a robot and creating a client
        if (winterTask.getClient() == null && winterTask.getRobot() != null) {
            model.addAttribute("addBuyer", true);
            //Получаем робота, который принадлежит winterTask'у
            //We get a robot that belongs to winterTask
            model.addAttribute("robot", winterTask.getRobot());
            //Получаем логи робота
            //Getting the robot logs
            model.addAttribute("logs", winterTask.getRobot().getLogEntryList());
            //ID winterTask'а
            //WinterTask ID
            model.addAttribute("id", id);
        }

        //Создание робота и клиента
        //Creating a robot and a client
        if (winterTask.getClient() == null && winterTask.getRobot() == null) {
            model.addAttribute("addAll", true);
            //ID winterTask'а
            //WinterTask ID
            model.addAttribute("id", id);
        }

        //Добавляем список buildMarket'ов в модель для использования в select'е
        model.addAttribute("buildMarketList", buildMarketService.findAll());
        //Комментарии winterTask'а
        //WinterTask Comments
        model.addAttribute("comments", winterTask.getComments());
        //Проверка на доступ к комментарию у роли пользователя
        //Checking for access to the comment by the user role
        model.addAttribute("commentCheck", roleService.findByRoleName(personDetails.getUser().getRole()).isCommentCheck());
        //Добавляем в модель работников, чтобы присваивать их имена через в select
        model.addAttribute("installatorsList", userService.findAll());

        return "winterService/edit";
    }

    //Метод для редактирования winterTask'а
    //A method for editing the winterTask
    @PostMapping("/{id}")
    public String editTask(@PathVariable("id") int id, @ModelAttribute("winterTask") WinterTask winterTask) {

        //При нажатии на кнопку save id клиента приравнивается к "", пока что пофиксил так мб потом что-нибудь придумаю
        if (winterTask.getClient().equals("")) {
            winterTask.setClient(null);
        }

        if (winterTask.getClient() != null) {
            Buyer buyer = buyerService.findById(Integer.parseInt(winterTask.getClient())).get();
            buyer.setName(winterTask.getClientName());
            buyer.setPhone_number(winterTask.getClientPhoneNumber());
            buyer.setEmail(winterTask.getClientEmail());
        }



        winterTaskService.update(id, winterTask);
        return "redirect:/winter/" + id + "/editTask";
    }


    //Метод для получения максимального ID winterTask'а
    //A method to get the maximum ID of the winterTask
    @GetMapping("/maxId")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getMaxId() {
        int maxId = winterTaskService.getMaxId();
        Map<String, Integer> response = new HashMap<>();
        response.put("id", maxId);
        return ResponseEntity.ok(response);
    }


}
