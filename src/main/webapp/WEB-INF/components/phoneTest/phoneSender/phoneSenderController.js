({
    sendPhone : function(component, event, helper) {
        console.log("Sending phone");

        var phoneEvent = $A.get("e.phoneTest:phoneMessage");
        phoneEvent.setParams({
            "name": "Matt",
            "date": new Date(),
            "message": "A tweet!",
            "imageUrl": "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
        });
        phoneEvent.fire();
        component.set("v.inputText", "");
    }
})
