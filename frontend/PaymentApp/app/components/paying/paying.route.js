(function() {
	"use strict";

	angular
		.module('paying-app.paying')
		.config(config);

	config.$inject = ['$stateProvider','$urlRouterProvider'];
	function config($stateProvider,$urlRouterProvider) {
		$urlRouterProvider.otherwise('/paying');

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
				url:'/paying',//:id
				views: {
					'content@': {
						// resolve: {  //IDEJA JE DA DOBIJEM ID TJ DA GA PROCITAM IZ BAZE I DA DOBIJEM TU PONUDU KAKO BIH IMAO CENU I OSTALE PODATKE
						// 	insurance: getInsurance,
						// },
						templateUrl: 'app/components/paying/paying.html',
						controller: 'PayingController',
						controllerAs: 'pc'
					}
				}
			});

		// getInsurance.$inject = ['payingService','$stateParams'];
		// function getInsurance(payingService,$stateParams) {
		// 	return payingService.get({},{'_id':$stateParams.id}).$promise;
		// }
	}
})();
