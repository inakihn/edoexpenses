'use strict';

App.controller('edoexpensesController', function($scope, Expense) {

  function fetchAllExpenses() {
    Expense.query(function(response) {
      $scope.expenses = response ? response : [];
    });
  };

  $scope.fetchAllExpenses = fetchAllExpenses;
  fetchAllExpenses();

  $scope.deleteExpense = function(expense) {
    expense.$remove(function() {
      $scope.expenses.splice($scope.expenses.indexOf(expense), 1);
    });
  };


  $scope.addExpense = function(newExpense) {
    new Expense({
      description: newExpense.description,
      value: newExpense.value,
      date: newExpense.date
    }).$save(function(expense) {
      $scope.expenses.push(expense);
    });
    $scope.newExpense = "";
    cancelCreating();
  };

  $scope.updateExpense = function(expense) {
    //Option 2: Re-querying service
    //    	expense.$update(function() {
    //    		fetchAllExpenses();
    //    		  });

    //Option 1: Without re-executing query, using lodash
    expense.$update();
    var index = _.findIndex($scope.expenses, function(b) {
      return b.id == expense.id;
    });
    $scope.expenses[index] = expense;
    $scope.editedExpense = null;
    $scope.isEditing = false;
  };

  // --------------------------------------------------------------
  // WINDOW STATE MANAGEMENT
  // --------------------------------------------------------------

  // Creating and editing states
  $scope.isCreating = false;
  $scope.isEditing = false;

  function startCreating() {
    $scope.isCreating = true;
    $scope.isEditing = false;
  }

  function cancelCreating() {
    $scope.isCreating = false;
  }

  function startEditing() {
    $scope.isCreating = false;
    $scope.isEditing = true;
  }

  function cancelEditing() {
    $scope.isEditing = false;
  }

  function shouldShowCreating() {
    return !$scope.isEditing;
  }

  function shouldShowEditing() {
    return $scope.isEditing && !$scope.isCreating;
  }

  $scope.startCreating = startCreating;
  $scope.cancelCreating = cancelCreating;
  $scope.startEditing = startEditing;
  $scope.cancelEditing = cancelEditing;
  $scope.shouldShowCreating = shouldShowCreating;
  $scope.shouldShowEditing = shouldShowEditing;
  
  $scope.editedExpense = null;

  function setEditedExpense(expense) {
    $scope.editedExpense = angular.copy(expense);
    $scope.editedExpense.date = new Date(expense.date);
  }
  $scope.setEditedExpense = setEditedExpense;
  
	function isSelectedExpense(expenseId) {
		return $scope.editedExpense !== null && $scope.editedExpense.id === expenseId;
	}
	
	$scope.isSelectedExpense = isSelectedExpense;

});