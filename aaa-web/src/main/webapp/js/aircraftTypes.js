'use strict';

(function() {

	var app = angular.module('aircraftAllocationApp.aircraftTypes', []);

	app.directive("aircraftTypes", function() {
		return {
			restrict : 'E',
			templateUrl : "views/aircraftTypes.html",
			controller : function($http, $window) {
				console.log('Initializing the aircraftTypeCtrl.');
				this.aircraftTypes = [];
				var store = this;

				this.deleteAircraftType = function(aircraftType){
					$http({method: 'DELETE', url: '/aaa-web/rest-api/aircraft-type/', data : aircraftType, headers : {'Content-Type' : 'application/json;charset=utf-8'}})
					.success(function(data) {
						store.listAircraftTypes();
					})
					.error(function(data) {
						console.log(data);
					});
				};
				
				this.listAircraftTypes = function() {
					$http.get('/aaa-web/rest-api/aircraft-type/list-aircraft-types').success(function(data) {
						store.aircraftTypes = data;
						$window.aircraftTypes = store.aircraftTypes;
					}).error(function(data) {
						console.log(data);
					});
				};
				
				// initial aircraft type loading.
				store.listAircraftTypes();
			},
			controllerAs : 'aircraftTypeCtrl'
		};
	});
	
	app.directive("createAircraftType", function() {
		return {
			restrict : 'E',
			templateUrl : "views/createAircraftType.html",
			controller : function($http, $scope) {
				console.log('Initializing the createAircraftTypeCtrl.');
				this.aircraftType = {};
				var store = this;
				store.errorMessage = '';

				this.createAircraftType = function(){
					$http({method: 'POST', url: '/aaa-web/rest-api/aircraft-type/', data : store.aircraftType, headers : {'Content-Type' : 'application/json;charset=utf-8'}})
					.success(function(data) {
						$('#createAircraftTypeDialog').modal('hide');
						store.aircraftType = {};
						$scope.aircraftTypeCtrl.listAircraftTypes();
					})
					.error(function(data) {
						console.log(data);
						store.errorMessage = data;
					});
				};
				
			},
			controllerAs : 'createAircraftTypeCtrl'
		};
	});

})();