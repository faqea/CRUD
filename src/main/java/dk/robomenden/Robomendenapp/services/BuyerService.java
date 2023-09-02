package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.BuyerMaxId;
import dk.robomenden.Robomendenapp.models.Task;
import dk.robomenden.Robomendenapp.models.TaskMaxId;
import dk.robomenden.Robomendenapp.repositories.BuyerMaxIdRepository;
import dk.robomenden.Robomendenapp.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BuyerService {

	private final BuyerRepository buyerRepository;
	private final BuyerMaxIdRepository buyerMaxIdRepository;
	private int maxId = 104;

	@Autowired
	public BuyerService(BuyerRepository buyerRepository, BuyerMaxIdRepository buyerMaxIdRepository) {
		this.buyerRepository = buyerRepository;
		this.buyerMaxIdRepository = buyerMaxIdRepository;
	}

	// Метод для получения списка buyer'ов
	// A method for obtaining a list of buyers
	public List<Buyer> findAll() {
		return buyerRepository.findAll();
	}

	// Метод для сохранения сущности
	// Method for saving the entity
	@Transactional
	public void save(Buyer buyer) {
		maxId++;
		buyerRepository.save(buyer);
	}

	// Метод для получения сущности по ID
	// A method to retrieve an entity by ID
	public Optional<Buyer> findById(int id) {
		return buyerRepository.findById(id);
	}

	// Метод для редактирования сущности
	// Method for editing an entity
	@Transactional
	public void edit(int id, Buyer buyer) {
		buyer.setId(id);
		buyerRepository.save(buyer);
	}

	// Метод для получения максимального ID сущности
	// A method to get the maximum entity ID
	@Transactional
	public int getMaxId() {
		maxId = buyerMaxIdRepository.findById(buyerMaxIdRepository.getMaxId()).get().getMaxId() + 1;
		BuyerMaxId maxId1 = new BuyerMaxId();
		maxId1.setMaxId(maxId);
		buyerMaxIdRepository.save(maxId1);
		return maxId1.getMaxId();
	}

	// Метод для удаления сущности по ID
	// Method to delete an entity by ID
	@Transactional
	public void delete(int id) {
		buyerRepository.deleteById(id);
	}

	// Метод для отображения сущностей по страницам
	// Method for displaying entities by page
	public List<Buyer> findAll(int page) {
		return buyerRepository.findAll(PageRequest.of(page, 2)).getContent();
	}

}
