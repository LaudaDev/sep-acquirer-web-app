(function() {
	"use strict";

	angular
		.module('paying-app.paying')
		.factory('buyService', buyService);

	buyService.$inject = ['$resource'];
	function buyService($resource) {
		var url = "https://localhost:8082/api/payment/paying/buy/:paymentID";
		return $resource(url, {});
	}
})();
