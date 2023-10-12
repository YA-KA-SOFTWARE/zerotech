/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */


// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

/*
const algoliasearch = require("algoliasearch");
const client = algoliasearch("CHWCTDMZHO", "9d3f1d282b5ba11618222eeb901c198d");
const index = client.initIndex("products");

// Firestore'dan ürünleri alma
const firestore = require("firebase/firestore");
const db = firestore.getFirestore();

db.collection("products").get().then((querySnapshot) => {
  const products = [];
  querySnapshot.forEach((doc) => {
    const product = doc.data();
    product.objectID = doc.id;
    products.push(product);
  });

  // Algolia'ya ürünleri indexleme
  index.saveObjects(products, {autoGenerateObjectIDIfNotExist: true})
      .then(() => {
        console.log("Products indexed in Algolia");
      }).catch((error) => {
        console.error("Error indexing products in Algolia", error);
      });
}).catch((error) => {
  console.error("Error fetching products from Firestore", error);
});
*/


