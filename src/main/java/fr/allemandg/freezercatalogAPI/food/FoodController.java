package fr.allemandg.freezercatalogAPI.food;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.allemandg.freezercatalogAPI.exceptions.NotFoundException;
import fr.allemandg.freezercatalogAPI.exceptions.PreconditionFailedException;


/**
 * FoodController is a RestController class representing the public methods of the Freezer Catalog API concerning the Food items.
 * 
 * The provided methods can be used to create a new item, search for an existing one, update an existing one 
 * and search for a list of items corresponding to given parameters.
 * 
 * 
 * @author Guillaume Allemand
 */
@RestController
@Transactional
public class FoodController {
	
	/**
	 * The service containing methods to contact the FoodRepository
	 */
	@Autowired
	private FoodService service;
	
	
	/**
	 * Constructs a <code>List<Food></code> containing elements corresponding to the research parameters using the appropriate service.
	 * 
	 * @param name the name of Food searched
	 * @param type the type of Food searched
	 * @param date the date when the Food was added
	 * @return The list of all results
	 * @exception NotFoundException if the results list is null
	 */
	@RequestMapping(path="/food/search", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Food> search (@RequestParam(value="name", required=false) String name, 
								@RequestParam(value="type", required=false) Type type, 
								@RequestParam(value="date", required=false) String date) {
		
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date dateTemp = null;
		if ( !StringUtils.isEmpty(date) ) {
			try {
				dateTemp = df.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<Food> results = service.getByCriteria(name, type, dateTemp);
		if (results != null) {
			return results;
		} else {
			throw new NotFoundException();
		}
	}
	
	/**
	 * Creates a new <code>Food</code> item and use the appropriate service to save it in the database.
	 * Then returns the id of the created item.
	 * 
	 * Preconditions : name is not empty
	 * 
	 * @param name the name of the Food item to be created
	 * @param type the type of the Food item to be created
	 * @param quantity the quantity of the Food item to be created
	 * @return The id of the Food item created
	 */
	@RequestMapping(path="/food", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.CREATED)
	public Long add (@RequestParam(value="name", required=true) String name, 
					@RequestParam(value="type", required=true) Type type, 
					@RequestParam(value="quantity", required=true) Integer quantity) {
		if ( !StringUtils.isEmpty(name) ) {
			Food food = new Food(name, type, quantity);
			food.setDate( new Date() );
			service.create(food);
			return food.getId();
		} else {
			throw new PreconditionFailedException();
		}
		
	}
	
	/**
	 * Searches for a <code>Food</code> item using the appropriate service and returns it.
	 * 
	 * @param id the id of the item to return
	 * @return The Food item from the database with the corresponding id.
	 * @exception NotFoundException if there is no corresponding item in the database
	 */
	@RequestMapping(path="/food/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Food get (@PathVariable Long id) {
		Food food = service.getById(id);
		if (food != null) {
			return food;
		} else {
			throw new NotFoundException();
		}
	}
	
	/**
	 * Updates the details of a <code>Food</code> item using the appropriate service.
	 * 
	 * Preconditions : existing item in the database (item with the same id).
	 * 
	 * @param food the item containing its updated values.
	 */
	@RequestMapping(path="/food", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void update (@RequestBody Food food) {
		Food foodTemp = service.getById(food.getId());
		if (foodTemp != null) {
			service.update(food);
		} else {
			throw new PreconditionFailedException();
		}
		
	}
	
}
