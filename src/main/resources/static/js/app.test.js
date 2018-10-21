const app = require('./app');

test('App message should be friendly', () => {
  expect(app.getMessage()).toBe('Hello World!');
});

test('example failure', () => {
  expect(app.todos.length).toBe(1);
});
