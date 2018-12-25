let functions = require('firebase-functions');
let admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendPush = functions.database.ref('/notifcations/{pushId}').onWrite((change,context) => 
{
    const Id = context.params.pushId;

    const receiverToken = admin.database().ref(`/notifcations/${Id}/receiverToken`).once('value');

    const senderToken = admin.database().ref(`/notifcations/${Id}/senderToken`).once('value');

    const message = admin.database().ref(`/notifcations/${Id}/message`).once('value');

    const title = admin.database().ref(`/notifcations/${Id}/title`).once('value');

    const notif = admin.database().ref(`/notifcations/${Id}`).once('value');


    return notif.then(result => 
    {
        const receiver_token = result.val().receiverToken;
        const sender_token = result.val().senderToken;
        const body = result.val().message;
        const title = result.val().title

        const payload = {
            notification : {
                title : title,
                body : body,
                icon : "default"
            }
        };
        return admin.messaging().sendToDevice(receiver_token, payload).then(response => {
            console.log('This was the notification Feature');
            return null;
        }); 
    });
});