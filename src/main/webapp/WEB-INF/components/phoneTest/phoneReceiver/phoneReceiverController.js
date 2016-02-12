({
    init : function(component, event, helper) {
        console.log("init");
        component.set("v.history", [{
            "name": "Matt",
            "date": new Date(),
            "message": "First tweet!",
            "imageUrl": "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
        }]);
    },
    receiveMessage : function(component, event, helper) {
        // Logs a message to the console
        /*
         console.log("Received a message: " + JSON.stringify(event));
         console.log("Message is: " + event.getParam("text"));
        component.set("v.number", event.getParam("text"));
        */
         var newTweet = {
             "name": event.getParam("name"),
             "message": event.getParam("massage"),
             "date": event.getParam("date"),
             "imageUrl": event.getParam("imageUrl")
         };

        var history = component.get("v.history");
        history.push(newTweet);
        component.set("v.history", history);

        for (i=0; i < history.length; i++) {
            console.log("History[" + i + "] = " + JSON.stringify(history[i]));
        }
     }
})
