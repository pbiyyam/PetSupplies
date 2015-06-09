'use strict';

var indexModule = angular.module("MainWebApp", ['ngRoute']);

indexModule.provider('myPageCtx', function() {
	  var defaultCtx = {
	    title: 'Default Title',
	    headerUrl: 'header.html',
	    footerUrl: 'footer.html',
	    //leftUrl: 'leftPanel.html'
	    //subHeaderUrl: 'subHeader.html'
	  };
	  
	  var currentCtx = angular.copy(defaultCtx);	  
	  return {
		    $get: function($rootScope) { 
		      
		      // We probably want to revert back to the default whenever
		      // the location is changed.
		      
		      $rootScope.$on('$locationChangeStart', function() {
		        angular.extend(currentCtx, defaultCtx);
		        ProductService.loadAllProducts();
		      }); 
		      
		      return currentCtx; 
		    }
		  };
		  
		});

indexModule.controller('MainCtrl', function($scope, myPageCtx) {
	  $scope.pageCtx = myPageCtx;
	});

indexModule.config(function($routeProvider) {
	  
	  $routeProvider.when('/', {
	    templateUrl: 'home.html'
	  });
	  
	  $routeProvider.otherwise({
	    redirectTo: 'html/login.html'
	  });
	});	  
	  
indexModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: 'productDetails.html'
            }).when('/success', {
                templateUrl: 'success.html'
            }).when('/failure', {
                templateUrl: 'failure.html'
            }).
            when('/login', {
                templateUrl: 'html/login.html',
                controller: 'LoginController'
            }).
            when('/register', {
                templateUrl: 'html/register.html',
                controller: 'RegisterController'
            }).
            otherwise({
                redirectTo: '/home'
            });
    }]);

indexModule.controller("HomeController",function($scope,$http,$window) {
	$scope.fetchProducts = function() {
		 $http.get('./productDetails.json').success(function(productList){
			 $scope.products = productList;
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
	$scope.fetchCategories = function() {
		 $http.get('./category.json').success(function(categoryList){
			 $scope.categories = categoryList;
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
});

indexModule.controller("RegisterController", function($scope,$http,$window,$location){
	$scope.list = [];
	$scope.register = function() {
		$scope.rgnForm.$valid
		var formData = {
				"emailId" : $scope.emailId,
				"password" : $scope.password,
				"firstName" : $scope.firstName,
				"lastName" : $scope.lastName,
				"userAddress" : $scope.userAddress,
				"contactNum" : $scope.contactNum
		};
		
		 var response = $http.post('./register.htm', formData);
		response.success(function(data, status, headers, config) {
			$scope.message = "You have Successfully Registered!!"
				$location.path("/success");
		});
	 	response.error(function(data, status, headers, config) {
		$scope.message = "Something went wrong. Please try after sometime"
			$location.path("/failure");
			console.log(JSON.stringify({data: data}));
	});
	};
});


indexModule.controller("LoginController",function($scope,$http,$window,$location) {
	
	$scope.login = function() {
		$scope.lgnForm.$valid
		 var formData1 = {
				"emailId" : $scope.userEmailId,
				"password" : $scope.userPassword
		};
		 var response1 = $http.post('./login.htm',formData1);
		response1.success(function(data, status, headers, config) {
			$scope.resetError();
			if(data == 'User Has Not Registered'){
				$scope.userEmailId='';
				$scope.userPassword='';
				$scope.message = data;
			}
			if(data == 'Success'){
				$location.path("/home");
			}
			if(data == 'User Not Authorised'){
				$scope.userEmailId='';
				$scope.userPassword='';
				$scope.message = data;
			}			 	
		});
	 	response1.error(function(data, status, headers, config) {
	 		$scope.userEmailId='';
			$scope.userPassword='';
			//$scope.message = data;	
		$scope.message = "Something went wrong. Please try after sometime";
		$location.path("/failure");
	});
	};
	
	$scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };
});
