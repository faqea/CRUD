package dk.robomenden.Robomendenapp.services;


import dk.robomenden.Robomendenapp.models.Robot;
import dk.robomenden.Robomendenapp.repositories.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RobotService {

    private final RobotRepository robotRepository;
    private final BuyerService buyerService;

    @Autowired
    public RobotService(BuyerService buyerService, RobotRepository robotRepository) {
        this.buyerService = buyerService;
        this.robotRepository = robotRepository;
    }

    //Метод для получения списка robot'ов
    //A method for obtaining a list of robots
    public List<Robot> findAll() {
        return robotRepository.findAll();
    }

    //Метод для сохранения сущности
    //Method for saving the entity
    @Transactional
    public void save(Robot robot) {
        robotRepository.save(robot);
    }

    //Метод для установки buyer'а роботу
    //Method for installing a buyer to a robot
    @Transactional
    public void setRobotOwner(int owner_id, Robot robot) {
        robot.setBuyer(buyerService.findById(owner_id).get());
    }

    //Метод для получения сущности по ID
    //A method to retrieve an entity by ID
    public Robot findById(int id) {
        return robotRepository.findById(id).get();
    }

    //Метод для редактирования сущности
    //Method for editing an entity
    @Transactional
    public void edit(int id, Robot robot) {
        robot.setId(id);
        robotRepository.save(robot);
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        robotRepository.deleteById(id);
    }



}
