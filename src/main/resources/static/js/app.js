(function(module) {

  const app = {};

  app.addEventListeners = function() {
    const todoItems = document.querySelectorAll('span[data-id]');

    for (let todoItem of todoItems) {
      todoItem.addEventListener('click', app.renameItem);
    }
  };

  app.renameItem = function() {
    const id = this.getAttribute('data-id');

    const newDescription =
      prompt(`What would you like to rename this task?`);

    if (newDescription) {
      app.sendItemUpdateRequest(id, newDescription);
    }
  };

  app.sendItemUpdateRequest = function(id, description) {
    const xhr = new XMLHttpRequest();
  
    xhr.onreadystatechange = function() {
      if (this.status === 200 && this.readyState === 4) {
        const updatedTaskItem = JSON.parse(this.responseText);
        const taskItemSpan = document.querySelector(`span[data-id="${id}"]`);
        app.updateTaskItem(taskItemSpan, updatedTaskItem);
      }
    };
    
    xhr.open('PUT', '/api/tasks');
    xhr.setRequestHeader('Content-Type', 'application/json');
    const body = JSON.stringify({ id: id, description: description });
    xhr.send(body);
  };

  app.updateTaskItem = function(taskItemElement, taskItemObject) {
    taskItemElement.textContent = taskItemObject.description
  };

  app.getItemId = function(itemElement) {
    const idString = itemElement.getAttribute('data-id');
    return parseInt(idString);
  };

  app.todos = [];

  app.message = "Hello World!";

  app.getMessage = function() {
    return app.message;
  };

  if (module) module.exports = app;

})(typeof module !== 'undefined' ? module : null);