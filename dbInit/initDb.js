use paymentDatabase
db.merchant.drop();
db.transaction.drop();


db.merchant.insert({
  "merchantId" : "merchantId",
  "merchantPassword" : "merchantPassword"
});
