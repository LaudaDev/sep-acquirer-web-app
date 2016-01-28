(function() {
  "use strict";

  angular
    .module('paying-app.paying')
    .controller('PayingController', PayingController);

  PayingController.$inject = ['$window', '$location','price'];

  function PayingController($window, $location,price) {
    var pc = this;
    pc.price = price;
    console.log(pc.price);
    pc.showTooltip = false;
    pc.howerOwer = howerOwer;
    pc.validatePayment = validatePayment;
    pc.isSecCodeNeeded = isSecCodeNeeded;
    pc.payment = {};

    pc.backToInsurance = backToInsurance;

    function howerOwer() {
      pc.showTooltip = (pc.showTooltip === false) ? true : false;
    }

    function backToInsurance() {
      $window.location.href = "http://localhost:8081/#/home/"
    }

    function validatePayment() {
      if (pc.stateForm.$valid) {
        pc.stateForm.$setUntouched();
        console.log("tacno");
      } else {
        touchControllsVehicles();
        console.log("netacno");
      }
    }

    function touchControllsVehicles() {
      //Prođi kroz sve propertie stateForm objekta
      angular.forEach(pc.stateForm, function(value, key) {
        //Pronađi propertie čiji naziv počinje sa "input"
        if (key.indexOf("input") === 0) {
          //"Dodirni" polje
          value.$setTouched();
        }
      });
    }

    function isSecCodeNeeded(){
      return (pc.payment.creditCard == 'maestro') ? false : true;
    }

  }
})();
