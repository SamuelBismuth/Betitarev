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

        const payload = {
            data : {
                title : result.val().title,
                body : result.val().message,
                id : result.val().betId,
                icon : "default"
            }
        };
    
        return admin.messaging().sendToDevice(receiver_token, payload).then(response => {
            console.log('This was the notification Feature');
            return null;
        }); 
    });
});