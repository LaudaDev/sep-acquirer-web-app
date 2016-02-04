(function() {
  "use strict";

  angular
    .module('paying-app.paying')
    .controller('PayingController', PayingController);

  PayingController.$inject = ['$window', '$location','transaction','buyService','$stateParams'];

  function PayingController($window, $location,transaction,buyService,$stateParams) {
    var pc = this;

    console.log(transaction);
    pc.price = transaction.amount;
    console.log(pc.price);
    pc.showTooltip = false;
    pc.howerOwer = howerOwer;
    pc.validatePayment = validatePayment;
    pc.isSecCodeNeeded = isSecCodeNeeded;
    pc.payment = {};
    pc.buyService = buyService;
    pc.payedDone = payedDone;
    pc.backToInsurance = backToInsurance;

    function howerOwer() {
      pc.showTooltip = (pc.showTooltip === false) ? true : false;
    }

    function backToInsurance() {
      $window.location.href = "http://localhost:8081/#/home/";
    }

    function payedDone(){
      pc.buyService.save({paymentID: $stateParams.paymentID},pc.payment,onSuccess,onError);
    }

    function onError(){
      console.log("Doslo je do errora prilkom transakcije");
      $window.location.href = "http://localhost:8081/#/error/";
    }

    function onSuccess(){
      console.log("Uspesno placanje");
    }

    function validatePayment() {
      if (pc.stateForm.$valid) {
        pc.stateForm.$setUntouched();
        console.log("tacno");
        console.log(pc.payment);
        pc.payedDone();
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
