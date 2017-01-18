'use strict';

App.factory('Expense', ['$resource', function($resource) {
  return $resource('/api/expenses/:id', {
    id: '@id'
  }, {
    update: {
      method: "PUT"
    }
  });
}]);