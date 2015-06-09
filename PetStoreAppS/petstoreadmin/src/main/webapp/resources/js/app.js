'use strict';
alert("hi in app.js file");
var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['ngRoute']);

App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/register', {
        templateUrl: '/views/register.html',
        controller: 'LoginController'
    }).controller('LoginController', [function(){
    	
    }]);

}]);