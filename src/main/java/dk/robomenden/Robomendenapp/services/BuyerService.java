package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.Buyer;
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


	@Autowired
	public BuyerService(BuyerRepository buyerRepository) {
		this.buyerRepository = buyerRepository;
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
