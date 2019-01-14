package fr.allemandg.freezercatalogAPI.food;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * FoodService is a service creating a link between the public Controller of the API and the corresponding Repository
 * 
 * 
 * @author Guillaume Allemand
 */
@Service
public class FoodService {
	
	/**
	 * The repository corresponding to the <code>Food</code> entity
	 */
	@Autowired
	private FoodRepository repository;
	
	/**
	 * The repository implementing the search by criteria
	 */
	@Autowired
	private FoodCriteriaRepositoryImpl criteriaRepo;
	
	public FoodService () {}
	
	
	/**
	 * Searches the database, using the appropriate repository, for the Food item with the given id.
	 * 
	 * @param id the id of the item to search
	 * @return the item with the appropriate id returned by the repository or null if no such item was found.
	 */
	public Food getById (Long id) {
		Food f = null;
		Optional<Food> of;
		of = repository.findById(id);
		if ( of.isPresent() ) {
			f = of.get();
		}
		return f;
	}
	
	/**
	 * Searches the database, using the criteria repository, for a list of <code>Food</code> items corresponding 
	 * to the parameters.
	 * 
	 * @param name the name of Food searched
	 * @param type the type of Food searched
	 * @param date the date when the Food was added
	 * @return The list of all results
	 */
	public List<Food> getByCriteria (String name, Type type, Date date) {
		return criteriaRepo.findByCriteria(name, type, date);
	}
	
	/**
	 * Saves the <code>Food</code> item in the database using the appropriate repository.
	 * 
	 * @param food the item to save in the DB.
	 * @return the item with his id.
	 */
	public Food create (Food food) {
		return repository.saveAndFlush(food); 
	}
	
	/**
	 * Updates a <code>Food</code> item details in the DB using the appropriate repository.
	 * 
	 * @param food the item to update.
	 */
	public void update (Food food) {
		repository.saveAndFlush(food);
	}
	
}
