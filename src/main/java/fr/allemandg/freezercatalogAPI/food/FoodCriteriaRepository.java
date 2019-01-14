package fr.allemandg.freezercatalogAPI.food;

import java.util.Date;
import java.util.List;


/**
 * FoodCriteriaRepository is the interface presenting the method to search for Food items with optional research parameters.
 * 
 * @author Guillaume Allemand
 */
public interface FoodCriteriaRepository {
	/**
	 * Method to serach for a Food item in the DB, to be implemented.
	 * 
	 * @param name the name of Food searched
	 * @param type the type of Food searched
	 * @param date the date when the Food was added
	 * @return The list of all results
	 */
	public List<Food> findByCriteria (String name, Type type, Date date);
}
