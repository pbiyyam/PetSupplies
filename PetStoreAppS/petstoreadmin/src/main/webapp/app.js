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
	    templateUrl: 'login.html',
	    controller: 'LoginController'
	  });
});	  
	  
indexModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: 'home.html',
                controller: 'HomeController'
            }).when('/login', {
                templateUrl: 'login.html',
                controller: 'LoginController'
            }).when('/logout', {
                templateUrl: 'logout.html',
                controller: 'LoginController'
            }).when('/category', {
                templateUrl: 'category.html',
                controller: 'CategoryController'
            }).when('/product', {
                templateUrl: 'product.html',
                controller: 'ProductController'
            }).when('/', {
                templateUrl: 'login.html',
                controller: 'LoginController'
            }).otherwise({
                redirectTo: '/login'
            });
    }]);

indexModule.controller("HomeController",function($scope,$http,$window) {
});

indexModule.controller("LoginController",function($scope,$http,$window,$location) {
	$scope.login = function() {
		$scope.lgnForm.$valid
		 var formData1 = {
				"emailId" : $scope.userEmailId,
				"password" : $scope.userPassword
		};
		 var response1 = $http.post('./adminLogin.htm',formData1);
		response1.success(function(data, status, headers, config) {
			
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
			/*$scope.userEmailId='';
			$scope.userPassword='';
			$scope.message = data;
			$location.path("/home");*/
		});
	 	response1.error(function(data, status, headers, config) {
		$scope.message = "Something went wrong. Please try after sometime";
	});
	};
	
	$scope.logout = function(){
	    $location.path('/login');
	};
});


indexModule.controller("CategoryController",function($scope,$http,$window) {
	
	$scope.category = {};
    $scope.editMode = false;
    
	$scope.fetchCategories = function() {
		 $http.get('./category.json').success(function(categoryList){
			 $scope.categories = categoryList;
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
	$scope.addNewCategory = function(category) {
        $scope.resetError();

        $http.post('./addCategory.htm', category).success(function() {
            $scope.fetchCategories();
            $scope.category.categoryName = '';
            $scope.category.categoryDesc = '';
        }).error(function() {
            $scope.setError('Could not add new category');
        });
    };
    
    $scope.updateCategory = function(category) {
        $scope.resetError();

        $http.put('./updateCategory.htm', category).success(function() {
        	$scope.setError('Successfully updated category');
            $scope.fetchCategories();
            $scope.category.categoryName = '';
            $scope.category.categoryDesc = '';
            $scope.editMode = false;
        }).error(function() {
            $scope.setError('Could not update category');
        });
    };
    
    $scope.editCategory = function(category) {
        $scope.resetError();
        $scope.category = category;
        $scope.editMode = true;
    };
    
    $scope.removeCategory = function(category) {
        $scope.resetError();
        $http.post('./removeCategory.htm', category).success(function() {
        	$scope.setError('Successfully Removed category');
            $scope.fetchCategories();
        }).error(function() {
            $scope.setError('Could not remove category');
            console.log(JSON.stringify({data: data}));
        });
        $scope.category.categoryName = '';
        $scope.category.categoryDesc = '';
    };
    
    $scope.resetCategoryForm = function() {
        $scope.resetError();
        $scope.category = {};
        $scope.editMode = false;
    };
    
    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };
    
    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };
    
    $scope.predicate = 'categoryId';
});

indexModule.controller("ProductController",function($scope,$http,$window) {
	$scope.product = {};
    $scope.editMode = false;
    
    $scope.fetchAllCategories = function() {
		 $http.get('./category.json').success(function(categoryList){
			 $scope.categories = categoryList;
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
    
	$scope.fetchProducts = function() {
		 $http.get('./product.json').success(function(productList){
			 $scope.products = productList;
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
	
	$scope.addNewProduct = function(product) {
        $scope.resetError();
        $http.post('./addProduct.htm', product).success(function() {
        	$scope.setError('Successfully added product');
        	$scope.fetchProducts();
            $scope.product.productName = '';
            $scope.product.productDesc = '';
            $scope.product.productPrice='';
            $scope.product.productStatus='';
        }).error(function() {
            $scope.setError('Could not add new product');
        });
    };
    
    $scope.updateProduct = function(product) {
        $scope.resetError();

        $http.put('./updateProduct.htm', product).success(function() {
        	$scope.setError('Successfully updated product');
            $scope.fetchProducts();
            $scope.product.productName = '';
            $scope.product.productDesc = '';
            $scope.product.productPrice='';
            $scope.product.productStatus='';
            $scope.editMode = false;
        }).error(function() {
            $scope.setError('Could not update product');
        });
    };
    
    $scope.editProduct = function(product) {
        $scope.resetError();
        $scope.product = product;
        $scope.editMode = true;
    };
    
    $scope.removeProduct = function(product) {
        $scope.resetError();
        $http.post('./removeProduct.htm', product).success(function() {
        	$scope.setError('Successfully Removed product');
            $scope.fetchProducts();
        }).error(function() {
            $scope.setError('Could not remove product');
        });
        $scope.product.productName = '';
        $scope.product.productDesc = '';
        $scope.product.productPrice='';
        $scope.product.productStatus='';
    };
    
    $scope.resetProductForm = function() {
        $scope.resetError();
        $scope.product = {};
        $scope.editMode = false;
    };
    
    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };
    
    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };
    
    $scope.predicate = 'productId';
});
