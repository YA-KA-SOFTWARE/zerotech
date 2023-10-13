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
  const {kategori, aramaTerimi} = req.query;

  if (!kategori || !aramaTerimi) {
    return res.status(400).send("Kategori ve arama terimi gereklidir.");
  }

  try {
    // Firestore'da belirli koleksiyonları hedefle
    const koleksiyonRef = db.collection("products").doc(kategori)
        .collection(kategori);

    // Verileri sorgula ve sonuçları al
    const snapshot = await koleksiyonRef
        .where("title", "==", aramaTerimi).get();

    const sonuclar = [];
    snapshot.forEach((doc) => {
      sonuclar.push(doc.data());
    });

    // Sonuçları JSON olarak dön
    res.status(200).json(sonuclar);
  } catch (error) {
    console.error("Hata:", error);
    res.status(500).send("Arama işlemi sırasında bir hata oluştu.");
  }
});
