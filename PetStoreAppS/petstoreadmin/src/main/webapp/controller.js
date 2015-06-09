'use strict';
alert("hiii in login contl js");



























/*var LoginController = function($scope,,$http,$window) {
	$scope.login = function() {
		alert("hi in login fun of home js"+$window.location);
		var formData = {
				"emailId" : $scope.emailId,
				"password" : $scope.password
		};
		
		 var response = $http.post('./login.htm', formData);
		response.success(function(data, status, headers, config) {
			alert("after success of registration"+$window.location);
			$window.location.replace('./html/success.html');
			//JSON.stringify({status})
		});
	 	response.error(function(data, status, headers, config) {
		alert( "Exception details: " + JSON.stringify({data: data}));
		$window.location.replace('./html/error.html');
	});
	};
};*/