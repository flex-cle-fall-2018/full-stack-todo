package com.wecancodeit.todo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TaskItem {
	
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
	@ManyToMany(mappedBy = "tasks")
	private Collection<Tag> tags;
	
	TaskItem(String description) {
		this.description = description;
	}
	
	protected TaskItem() {} // JPA

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

}
