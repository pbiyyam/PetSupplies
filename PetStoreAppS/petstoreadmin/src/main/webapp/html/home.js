'use strict';
alert("hi in home js");

var RegisterController = function($scope,$http){
	$scope.list = [];
	$scope.register = function() {
		alert("hi in register fun of home js");
		var formData = {
				"userId" : $scope.userId,
				"password" : $scope.password,
				"firstName" : $scope.firstName,
				"lastName" : $scope.lastName,
				"userAddress" : $scope.userAddress,
				"contactNum" : $scope.contactNum,
				"emailId" : $scope.emailId
		};
		
		 var response = $http.post('RegisterUser.htm', formData);
		response.success(function(data, status, headers, config) {
			$scope.list.push(data);
			//JSON.stringify({status})
		});
	 	response.error(function(data, status, headers, config) {
		alert( "Exception details: " + JSON.stringify({data: data}));
	});
	
	//Empty list data after process
	$scope.list = [];
	};
}; 
	
	/*("RegisterController", [ '$scope','$window', '$http', function($scope,$window, $http) {
	$scope.list = [];
	$scope.headerText = 'AngularJS Post Form Spring MVC example: Submit below form';
	$scope.register = function() {
		alert("hi in register fun of home js");
		var formData = {
				"userId" : $scope.userId,
				"password" : $scope.password,
				"firstName" : $scope.firstName,
				"lastName" : $scope.lastName,
				"userAddress" : $scope.userAddress,
				"contactNum" : $scope.contactNum,
				"emailId" : $scope.emailId
		};
		
		 var response = $http.post('RegisterUser.htm', formData);
		response.success(function(data, status, headers, config) {
			$scope.list.push(data);
			//JSON.stringify({status})
		});
	 	response.error(function(data, status, headers, config) {
		alert( "Exception details: " + JSON.stringify({data: data}));
	});
	
	//Empty list data after process
	$scope.list = [];
	};	
}]);
*/