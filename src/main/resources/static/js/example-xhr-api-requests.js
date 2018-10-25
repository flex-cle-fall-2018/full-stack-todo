// GET (retrieve ALL)
(function() {
  ​
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            console.log(this.responseText);
        }
    };
    
    xhr.open('GET', '/api/tasks');
​
    xhr.send();
    
})();

// GET (retrieve just ONE)
(function() {
  ​
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            console.log(this.responseText);
        }
    };
    
    xhr.open('GET', '/api/tasks/1');
​
    xhr.send();
    
})();

// POST (create)
(function() {
  ​
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            console.log(this.responseText);
        }
    };
    
    xhr.open('POST', '/api/tasks');
​
    xhr.setRequestHeader('Content-Type', 'application/json');
​
    const body = JSON.stringify({ description: 'Created with JS #2' });
    
    xhr.send(body);
    
})();

// DELETE
(function() {

  const xhr = new XMLHttpRequest();
  
  xhr.onreadystatechange = function() {
      if (this.status === 200 && this.readyState === 4) {
    console.log(this.responseText);
      }
  };
  
  xhr.open('DELETE', '/api/tasks/2');

  xhr.send();
  
})();

// PUT (update)
(function() {

  const xhr = new XMLHttpRequest();
  
  xhr.onreadystatechange = function() {
      if (this.status === 200 && this.readyState === 4) {
    console.log(this.responseText);
      }
  };
  
  xhr.open('PUT', '/api/tasks');

  xhr.setRequestHeader('Content-Type', 'application/json');

  const body = JSON.stringify({ id: 2, description: 'Cut the Grass' });
  
  xhr.send(body);
  
})();