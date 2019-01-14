package fr.allemandg.freezercatalogAPI.food;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.validation.constraints.NotBlank;


/**
 * Food is the class representing an object to store in the Freezer.
 * 
 * The id is a Long generated with a Sequence so that every item has a different id and can be identified using it.
 * 
 * The name is a String given by the user at the item's creation.
 * 
 * The type is a Type given by the user at the item's creation.
 * 
 * The quantity is an Integer given by the player at the item's creation.
 * 
 * The date is corresponding to the day wwhen the item was created.
 * 
 * 
 * @author Guillaume Allemand
 */
@Entity
@SequenceGenerator(name="food_id_seq", sequenceName="food_is_seq", initialValue=0, allocationSize=1)
public class Food implements Serializable {
	@Transient
	private static final long serialVersionUID = -2444712513726181291L;
	
	// Attributes
	
	@Id
	@GeneratedValue(generator="food_id_seq")
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotBlank
	private String name;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	//Constructors
	
	public Food () {}
	
	public Food (String name, Type type, Integer quantity) {
		this.name = name;
		this.type = type;
		this.quantity = quantity;
	}
	
	
	// Getters and Setters
	
	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public Type getType() { return this.type; }
	public void setType(Type type) { this.type = type; }
	
	public Integer getQuantity() { return this.quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }
	
	public Date getDate() { return this.date; }
	public void setDate(Date date) { this.date = date; }
	
}
