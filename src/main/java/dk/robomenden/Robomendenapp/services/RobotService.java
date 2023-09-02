package dk.robomenden.Robomendenapp.services;


import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.MaxId;
import dk.robomenden.Robomendenapp.models.Robot;
import dk.robomenden.Robomendenapp.models.RobotMaxId;
import dk.robomenden.Robomendenapp.repositories.RobotMaxIdRepository;
import dk.robomenden.Robomendenapp.repositories.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RobotService {

    private final RobotRepository robotRepository;
    private final BuyerService buyerService;
    private final RobotMaxIdRepository robotMaxIdRepository;
    private int maxId = 127;

    @Autowired
    public RobotService(BuyerService buyerService, RobotRepository robotRepository, RobotMaxIdRepository robotMaxIdRepository) {
        this.buyerService = buyerService;
        this.robotRepository = robotRepository;
        this.robotMaxIdRepository = robotMaxIdRepository;
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
        maxId++;
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

    //Метод для получения максимального ID сущности
    //A method to get the maximum entity ID
    @Transactional
    public int getMaxId() {
        maxId = robotMaxIdRepository.findById(robotMaxIdRepository.getMaxId()).get().getMaxId() + 1;
        RobotMaxId maxId1 = new RobotMaxId();
        maxId1.setMaxId(maxId);
        robotMaxIdRepository.save(maxId1);
        return maxId1.getMaxId();
    }

    //Метод для удаления сущности по ID
    //Method to delete an entity by ID
    @Transactional
    public void deleteById(int id) {
        robotRepository.deleteById(id);
    }



}
