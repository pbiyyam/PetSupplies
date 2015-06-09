'use strict';

var LoginController = function($scope, $http){
	 $scope.getLoginPage = function() {
	        $http.get('/register').success(function(data){
	            $scope.data = data;
	        });
	    };
};