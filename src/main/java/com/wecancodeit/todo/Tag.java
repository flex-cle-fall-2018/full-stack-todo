package com.wecancodeit.todo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToMany
	private Collection<TaskItem> tasks;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Tag(String name) {
		this.name = name;
	}
	
	protected Tag() {} // JPA

	public Tag(String name, TaskItem task) {
		this.name = name;
		this.tasks = new ArrayList<TaskItem>();
		this.tasks.add(task);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
