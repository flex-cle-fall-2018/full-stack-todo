(function(module) {
  
  const app = {};

  app.todos = [];

  app.message = "Hello World!";

  app.getMessage = function() {
    return app.message;
  };

  if (module) module.exports = app;

})(module || null);