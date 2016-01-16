(function() {
	"use strict";

	angular
		.module('paying-app.paying')
		.controller('HeaderController', HeaderController);

	HeaderController.$inject = ['$translate', 'tmhDynamicLocale', 'crTranslations'];
	function HeaderController($translate, tmhDynamicLocale, crTranslations) {
		var hc = this;
		hc.changeLocale = changeLocale;

		function changeLocale(locale) {
			$translate.use(locale);
			tmhDynamicLocale.set(locale);
		}
	}
})();
