(function() {
  "use strict";

  var crTranslations = {
    "en": {
      "SIDEBAR": {
        "ORDER_TOTAL": "Total amount",
        "ORDER_ID": "Insurance id: "

      },

      "NAVBAR": {
        "TEAM": "PayingApp-Team18",
        "LANGUAGE":"Language",
        "ENGLISH": "English",
        "SERBIAN": "Serbian"
      },
      "FOOTER":{
				"PRIVACY" : "Privacy",
				"TERMS" : "Terms",
				"TOP" : "Back to top"
			},
      "MAIN":{
        "CARD_TYPE" : "Card type",
        "CARD_NUM" : "Card number",
        "EXPIRATION_DATE" : "Expiration date",
        "SECURITY_CODE" : "Security code",
        "WHAT_THIS" : "What is this?",
        "CARDHOLDER_NAME" : "Cardholder name",
        "NAME" : "Name",
				"SURNAME" : "Surname",
        "PAY_NOW" : "Pay now",
        "YY" : "YY",
        "EXAMPLE" : "e.g. ",
        "TOOLTIP" : "For MasterCard or VISA, it's the last three digits in the signature area on the back of your card. For Maestro cards,it is not required to enter this code if you don't have one."
      },
      "JUMBOTRON":{
        "TITLE" : "Welcome to Paying page!",
        "TEXT" : "On this page you can pay all you need with several credit cards. We provide wide set of cards. You can pay with your MasterCard, Visa or Maestro card. It's up to you! For any question ask out at call-centar.",
      },
			"MESSAGES":{
				"MIN_LENGTH" : "This field is too short",
				"MAX_LENGTH" : "This field is too long",
				"REQUIRED" : "This field is required",
				"EMAIL" : "This needs to be a valid email(test@test)",
				"CUSTOM" : "At least one of three fields is required",
				"NUMBER" : "This field accepts only numbers("
			}

    },
    "sr-latn": {
      "SIDEBAR": {
        "ORDER_TOTAL": "Ukupna cena:",
        "ORDER_ID": "Id osiguranja: "

      },
      "NAVBAR": {
        "TEAM": "PayingApp-Tim18",
        "LANGUAGE":"Jezik",
        "ENGLISH": "Engleski",
        "SERBIAN": "Srpski"
      },
      "FOOTER":{
				"PRIVACY" : "Privatnost",
				"TERMS" : "Uslovi",
				"TOP" : "Nazada na vrh stranice"
			},
      "MAIN":{
        "CARD_TYPE" : "Tip kartice",
        "CARD_NUM" : "Broj kartice",
        "EXPIRATION_DATE" : "Datum isteka",
        "SECURITY_CODE" : "Zaštitni kod",
        "WHAT_THIS" : "Šta je ovo?",
        "CARDHOLDER_NAME" : "Vlasnik kartice",
        "NAME" : "Ime",
				"SURNAME" : "Prezime",
        "PAY_NOW" : "Platite sad",
        "YY" : "GG",
        "EXAMPLE" : "npr. ",
        "TOOLTIP" : "Za MasterCard ili Visa kartice ovaj broj predstavlja zadnje tri cifre na poleđini kartice. Za Maestro kartice ovaj broj nije potrebno uneti ako ga nemate na poleđini kartici."
      },
      "JUMBOTRON":{
        "TITLE" : "Dobrodošli na stranicu za naplatu!",
        "TEXT" : "Na ovoj stranici možete izvršiti plaćanje za sve što Vam je potrebno. U ponudi imamo široku paletu kreditnih kartica. Plaćanje možete izvršiti MasterCard,Visa ili Maestro karticom. Na vama je izbor! Za bilo kakva pitanja nazovite naš call centar. "
      },
			"MESSAGES":{
				"MIN_LENGTH" : "Ovo polje je prekratko",
				"MAX_LENGTH" : "Ovo polje je predugačko",
				"REQUIRED" : "Ovo polje je obavezno!",
				"EMAIL" : "Potrebno je da ovo bude validna email adresa(test@test)",
				"CUSTOM" : "Potrebno je uneti podatke bar u 1 polje",
				"NUMBER" : "Ovo polje prihvata samo brojeve("
			}

    }
  };

  angular
    .module('paying-app.i18n.constants')
    .constant("crTranslations", crTranslations);
})();
