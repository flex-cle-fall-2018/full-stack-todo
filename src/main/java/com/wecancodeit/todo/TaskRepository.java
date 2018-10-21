package com.wecancodeit.todo;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskItem, Long> {

}
