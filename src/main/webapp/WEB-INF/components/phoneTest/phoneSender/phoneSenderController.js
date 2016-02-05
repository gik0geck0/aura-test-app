({
    sendPhone : function(component, event, helper) {
        console.log("Sending phone");

        var phoneEvent = $A.get("e.phoneTest:phoneMessage");
        phoneEvent.setParams({
            "phoneNumber": 5
        });
        phoneEvent.fire();
    }
})
