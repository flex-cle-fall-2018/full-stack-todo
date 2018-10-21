package com.wecancodeit.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaskItem {
	
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
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

}
