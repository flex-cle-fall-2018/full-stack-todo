(function(module) {

  const todoItems = document.querySelectorAll('li[data-id]');

  for (let todoItem of todoItems) {
    todoItem.addEventListener('click', renameItem);
  }

  function renameItem() {
    const id = this.getAttribute('data-id');

    const newDescription =
      prompt(`What would you like to rename this task?`);

    updateTaskItem(id, newDescription);
  }

  function updateTaskItem(id, description) {
    const xhr = new XMLHttpRequest();
  
    xhr.onreadystatechange = function() {
      if (this.status === 200 && this.readyState === 4) {
        console.log(this.responseText);

        alert('Updated');
        window.location.reload();
      }
    };
    
    xhr.open('PUT', '/api/tasks');
  
    xhr.setRequestHeader('Content-Type', 'application/json');
  
    const body = JSON.stringify({ id: id, description: description });
    
    xhr.send(body);
  }

  
  // const app = {};

  // app.todos = [];

  // app.message = "Hello World!";

  // app.getMessage = function() {
  //   return app.message;
  // };

  if (module) module.exports = app;

})(typeof module !== 'undefined' ? module : null);