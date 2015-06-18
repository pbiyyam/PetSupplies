'use strict';

var productsLoaded;
var categoriesLoaded;
var loggedinUser;

var indexModule = angular.module("MainWebApp", ['ngRoute']);

indexModule.provider('myPageCtx', function() {
	  var defaultCtx = {
	    title: 'Default Title',
	    headerUrl: 'header.html',
	    headerUrlPostLogin: 'headerPostLogin.html',
	    footerUrl: 'footer.html'
	  };
	  
	  var currentCtx = angular.copy(defaultCtx);	  
	  return {
		    $get: function($rootScope) { 
		      
		      // We probably want to revert back to the default whenever
		      // the location is changed.
		      
		      $rootScope.$on('$locationChangeStart', function() {
		        angular.extend(currentCtx, defaultCtx);
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
                                templateUrl: 'home.html',
                                controller: 'HomeController'	
                            }).when('/success', {
                                templateUrl: 'success.html',
                                controller: 'HomeController'
                            }).when('/failure', {
                                templateUrl: 'failure.html',
                                controller: 'HomeController'
                            }).when('/orderSuccess', {
                                templateUrl: 'OrderSuccess.html',
                                controller: 'HomeController'
                            }).when('/login', {
                                templateUrl: 'html/login.html',
                                controller: 'LoginController'
                            }).
                            when('/store', {
                                templateUrl: 'store.html',
                                controller: 'storeController'
                            }).
                            when('/products/:productId', {
                                templateUrl: 'product.html',
                                controller: 'storeController'
                            }).
                            when('/cart', {
                                templateUrl: 'shoppingCart.html',
                                controller: 'storeController'
                            }).
                            when('/register', {
                                templateUrl: 'html/register.html',
                                controller: 'RegisterController'
                            }).
                            otherwise({
                                redirectTo: '/home'
                            });
                    }]);


//create a data service that provides a store and a shopping cart that
//will be shared by all views (instead of creating fresh ones for each view).

indexModule.factory("DataService", function () {

// create store
var myStore = new store();

//create shopping cart
var myCart = new shoppingCart();

//enable PayPal checkout
// note: the second parameter identifies the merchant; in order to use the 
// shopping cart with PayPal, you have to create a merchant account with 
// PayPal. You can do that here:
// https://www.paypal.com/webapps/mpp/merchant
myCart.addCheckoutParameters("PayPal", "bernardo.castilho-facilitator@gmail.com");

return {
	store: myStore,
    cart: myCart
	};
	
});


indexModule.controller("HomeController",function($scope,$http,$window) {
	if(loggedinUser!=null){
		$scope.loggedin = true;
		$scope.user = loggedinUser;
	}
	else{
		$scope.loggedin = false;
	}
});

indexModule.controller("storeController",function($scope,$http,$window,$location,$routeParams,DataService){
	
	if(loggedinUser!=null){
		$scope.loggedin = true;
		$scope.user = loggedinUser;
	}
	else{
		$scope.loggedin = false;
	}
	
	// get store and cart from service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;
        
    var list = [];
    $scope.fetchProducts = function() {
    	 $http.get('./productDetails.json').success(function(productList){
    		 $scope.products = productList;
    		 //list.push(productList);
    	 }).error(function(data, status, headers, config) {
    		 console.log(JSON.stringify({data: data}));
    	});
    };
    
    $scope.fetchCategories = function() {
		 $http.get('./categoryDetails.json').success(function(categoryList){
			 $scope.categories = categoryList;
			 categoriesLoaded = $scope.categories;
			 $scope.fetchProducts();
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
	
	$scope.getProduct = function (sku) {
		 for (var i = 0; i < this.products.length; i++) {
		     if (this.products[i].productId == sku){
		    	 return this.products[i]; 
		     }
		 }
		 return null;
	};
	
	$scope.fetchProductByCategory = function(catId){
		$http.post('./getProductsByCategory.json',catId).success(function(productList){
			 $scope.products = productList;
		 }).error(function(data, status, headers, config) {
			 console.log(JSON.stringify({data: data}));
		});
	};
	
	//placeorder function call
	$scope.placeOrder = function(){
		if(loggedinUser!=null){
			var items = {};
			var tempItem={};
			var prodData={};
			items = $scope.cart.placeOrder();	
			tempItem = items[0];
			var userId = loggedinUser;
			var prodData = {
					"productId" : tempItem.productId,
					"userId" : loggedinUser
			}
			var response3 = $http.post('./placeOrder.htm',prodData).success(function(data) {
				$scope.cart.clearItems();
				$location.path("/orderSuccess");
			}).error(function(data, status, headers, config) {
				$scope.message = "Something went wrong. Please try after sometime";
			});
		}else{
			$scope.message = "User should login before checking out the cart";
			$location.path("/login");
		}
		
	};
	
	// use routing to pick the selected product
    /*if ($routeParams.productId != null) {
        $scope.product = $scope.getProduct($routeParams.productId);
    }*/
	    
});

indexModule.controller("RegisterController", function($scope,$http,$window,$location){
	
	if(loggedinUser!=null){
		$scope.loggedin = true;
		$scope.user = loggedinUser;
	}
	else{
		$scope.loggedin = false;
	}
	
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
			loggedinUser = $scope.userEmailId;
			if(data == 'User Has Not Registered'){
				$scope.loggedin = false;
				$scope.userEmailId='';
				$scope.userPassword='';
				$scope.message = data;
			}
			if(data == 'Success'){
				if(loggedinUser!=null){
					$scope.loggedin = true;
					$scope.user = loggedinUser;
				}
				else{
					$scope.loggedin = false;
				}
				$location.path("/home");
			}
			if(data == 'User Not Authorised'){
				$scope.loggedin = false;
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
