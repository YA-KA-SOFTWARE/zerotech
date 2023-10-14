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


const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

const db = admin.firestore();

exports.aramaUygula = functions.https.onRequest(async (req, res) => {
  const {aramaTerimi} = req.query;

  if (!aramaTerimi) {
    return res.status(400).send("Arama terimi gereklidir.");
  }

  // Arama terimini küçük harfe dönüştür
  const kucukHarfliAramaTerimi = aramaTerimi.toLowerCase();

  try {
    const sonuclar = [];

    // Firestore'da belirli bir koleksiyona erişin (örneğin, "products")
    const productsRef = db.collection("products");

    // Tüm alt belgelere erişmek için bir sorgu yapın
    const snapshot = await productsRef.listDocuments();

    // Her alt belgeyi dönün ve arama terimine göre kontrol edin
    for (const productDoc of snapshot) {
      const subcollectionRef = productDoc.collection(productDoc.id);
      const subSnapshot = await subcollectionRef.get();

      subSnapshot.forEach((doc) => {
        const productData = doc.data();

        // Verileri küçük harfe dönüştür
        const kucukHarfliTitle = productData.title.toLowerCase();
        const kucukHarfliType = productData.type.toLowerCase();

        if (kucukHarfliTitle.includes(kucukHarfliAramaTerimi) ||
        kucukHarfliType.includes(kucukHarfliAramaTerimi)) {
          sonuclar.push(productData);
        }
      });
    }

    res.status(200).json(sonuclar);
  } catch (error) {
    console.error("Hata:", error);
    res.status(500).send("Arama işlemi sırasında bir hata oluştu.");
  }
});
