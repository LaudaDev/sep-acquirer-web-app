(function() {
	"use strict";

	angular
		.module('paying-app.paying')
		.factory('payingService', payingService);

	payingService.$inject = ['$resource'];
	function payingService($resource) {
		var url = "https://localhost:8082/api/payment/paying/:paymentID";
		return $resource(url, {});
	}
})();
