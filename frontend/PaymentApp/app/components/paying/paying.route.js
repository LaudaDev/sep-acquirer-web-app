(function() {
	"use strict";

	angular
		.module('paying-app.paying')
		.config(config);

	config.$inject = ['$stateProvider','$urlRouterProvider'];
	function config($stateProvider,$urlRouterProvider) {
		$urlRouterProvider.otherwise('/payApp');

		$stateProvider
			.state('main', {
				abstract: true,
				views: {
					'header': {
						templateUrl: 'app/components/core/header.html',
						controller: 'HeaderController',
						controllerAs: 'hc'
					},
					'footer' : {
							templateUrl: 'app/components/core/footer.html'
					}
				}
			})
			.state('main.paying', {
				url:'/payApp/:paymentID',
				views: {
					'content@': {
						resolve: {
							price: getPrice,
						},
						templateUrl: 'app/components/paying/paying.html',
						controller: 'PayingController',
						controllerAs: 'pc'
					}
				}
			});

			getPrice.$inject = ['payingService','$stateParams'];
			function getPrice(payingService,$stateParams) {
      	return payingService.get({paymentID: $stateParams.paymentID}).$promise;
    }
	}
})();
