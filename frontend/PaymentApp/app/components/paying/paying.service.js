(function() {
	"use strict";

	angular
		.module('paying-app.paying')
		.factory('payingService', payingService);

	payingService.$inject = ['$resource'];
	function payingService($resource) {
		var apiKey = "INXupqz0_X3m6Xp42025Tl54zoIT4Mfc";
		var dbName = "insuranceapp";
		var collectionName = "purchaseInsurances";
		return $resource("https://api.mongolab.com/api/1/databases/:dbName/collections/:collectionName/:id",
			{apiKey: apiKey, id: "@_id", dbName: dbName, collectionName: collectionName});
	}
})();
