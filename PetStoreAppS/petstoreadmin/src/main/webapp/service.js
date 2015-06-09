/**
 * 
 */
'use strict'

indexModule.factory('ProductService',['$resource',function($http,$rootScope){
	//return new Product($resourcce);
	var service = {}
	
	service.loadAllProducts = function(){
		alert("hi in laod all prodcuts ");
		 var response3 = $http.get('./home.json');
		 response3.success(function(productList){
			 $scope.products = productList;
		 });
		 response3.error(function(data, status, headers, config) {
				alert( "Exception details: " + JSON.stringify({data: data}));
			});
	}
}]);