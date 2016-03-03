({
    init : function(component, event, helper) {
        console.log("init");
    },
    receiveMessage : function(component, event, helper) {
        // Logs a message to the console
         console.log("Received a message: " + JSON.stringify(event));
         console.log("Message is: " + event.getParam("text"));
        component.set("v.number", event.getParam("text"));
     }
})
