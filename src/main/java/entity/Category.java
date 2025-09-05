package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	 
	@Column(name = "categoryname",columnDefinition = "nvarchar(50)")
	private String categoryname;
	
	private String images;
	
	private int user_id;
	
	public Category(int id, String categoryname, String images) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.images = images;
	}
	public Category() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
}
