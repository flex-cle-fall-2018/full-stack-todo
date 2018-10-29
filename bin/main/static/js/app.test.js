const app = require('./app');

test('App message should be friendly', () => {
  expect(app.getMessage()).toBe('Hello World!');
});

test('example failure', () => {
  expect(app.todos.length).toBe(1);
});

test('should be able to get ID of task element', () => {
  // Arrange
  const span = document.createElement('span');
  span.setAttribute('data-id', 77);

  // Act
  const id = app.getItemId(span);

  // Assert
  expect(id).toBe(77);
});

test('should be able to update task item element', () => {
  // Arrange
  const span = document.createElement('span');
  span.textContent = 'Original';
  const task = { id: 13, description: 'Updated' };

  // Act
  app.updateTaskItem(span, task);

  // Assert
  expect(span.textContent).toBe('Updated');
});
