package fr.allemandg.freezercatalogAPI.food;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


/**
 * FoodRepository is the class representing the <code>Food</code> entity repository, extending <code>JpaRepository</code>
 * 
 * 
 * @author Guillaume Allemand
 */
@RepositoryRestResource(collectionResourceRel="food", path="food")
@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{
	/**
	 * Searches the database for the item with the corresponding id.
	 * 
	 * @param id the id of the item to search
	 * @return an Optional containing the item with the appropriate id from the database if it exists.
	 */
	Optional<Food> findById(Long id);
}
