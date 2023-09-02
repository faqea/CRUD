package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.BuildMarket;
import dk.robomenden.Robomendenapp.models.BuildMarketMaxId;
import dk.robomenden.Robomendenapp.models.Buyer;
import dk.robomenden.Robomendenapp.models.BuyerMaxId;
import dk.robomenden.Robomendenapp.repositories.BuildMarketMaxIdRepository;
import dk.robomenden.Robomendenapp.repositories.BuildMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BuildMarketService {

	private final BuildMarketRepository buildMarketRepository;
	private final BuildMarketMaxIdRepository buildMarketMaxIdRepository;
	private int maxId = 104;

	@Autowired
	public BuildMarketService(BuildMarketRepository buildMarketRepository,
			BuildMarketMaxIdRepository buildMarketMaxIdRepository) {
		this.buildMarketRepository = buildMarketRepository;
		this.buildMarketMaxIdRepository = buildMarketMaxIdRepository;
	}

	// Метод для получения списка market'ов
	// A method for obtaining a list of markets
	public List<BuildMarket> findAll() {
		return buildMarketRepository.findAll();
	}

	// Метод для получения сущности по ID
	// A method to retrieve an entity by ID
	public BuildMarket findById(int id) {
		return buildMarketRepository.findById(id).get();
	}

	// Метод для редактирования сущности
	// Method for editing an entity
	@Transactional
	public void edit(int id, BuildMarket buildMarket) {
		buildMarket.setId(id);
		buildMarketRepository.save(buildMarket);
	}

	// Метод для сохранения сущности
	// Method for saving the entity
	@Transactional
	public void save(BuildMarket buildMarket) {
		maxId++;
		buildMarketRepository.save(buildMarket);
	}

	// Метод для получения максимального ID сущности
	// A method to get the maximum entity ID
	@Transactional
	public int getMaxId() {
		maxId = buildMarketMaxIdRepository.findById(buildMarketMaxIdRepository.getMaxId()).get().getMaxId() + 1;
		BuildMarketMaxId maxId1 = new BuildMarketMaxId();
		maxId1.setMaxId(maxId);
		buildMarketMaxIdRepository.save(maxId1);
		return maxId1.getMaxId();
	}

	// Метод для удаления сущности по ID
	// Method to delete an entity by ID
	@Transactional
	public void delete(int id) {
		buildMarketRepository.deleteById(id);
	}

	// Метод для отображения сущностей по страницам
	// Method for displaying entities by page
	public List<BuildMarket> findAll(int page) {
		return buildMarketRepository.findAll(PageRequest.of(page, 2)).getContent();
	}
}
