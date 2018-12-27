let functions = require('firebase-functions');
let admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendPush = functions.database.ref('/notifcations/{pushId}').onWrite((change,context) => 
{
    const Id = context.params.pushId;
    const notif = admin.database().ref(`/notifcations/${Id}`).once('value');

    return notif.then(result => 
    {
        const receiver_token = result.val().receiverToken;
        const sender_token = result.val().senderToken;
        const body = result.val().message;
        const title = result.val().title;
        const betId = result.val().betId;

        const payload = {
            notification : {
                title : title,
                body : body  + "split" + betId,
                icon : "default"
            }
        };
        return admin.messaging().sendToDevice(receiver_token, payload).then(response => {
            console.log('This was the notification Feature');
            return null;
        }); 
    });
});