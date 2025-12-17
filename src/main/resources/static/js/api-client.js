const api = {
  get: (url) => 
    fetch(url).then(r => r.json()),
    
  post: (url, body) =>
    fetch(url, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(body)
    }).then(r => r.json()),
    
  put: (url, body) =>
    fetch(url, {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(body)
    }).then(r => r.json()),
    
  patch: (url, body) =>
    fetch(url, {
      method: 'PATCH',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(body)
    }).then(r => r.json()),
    
  delete: (url) =>
    fetch(url, {
      method: 'DELETE'
    })
};

// Sample entity functions
const sampleEntityAPI = {
  getAll: () => api.get('/api/sample-entities'),
  getById: (id) => api.get(`/api/sample-entities/${id}`),
  create: (entity) => api.post('/api/sample-entities', entity),
  update: (id, entity) => api.put(`/api/sample-entities/${id}`, entity),
  delete: (id) => api.delete(`/api/sample-entities/${id}`)
};

// Export for use in other files
window.api = api;
window.sampleEntityAPI = sampleEntityAPI;