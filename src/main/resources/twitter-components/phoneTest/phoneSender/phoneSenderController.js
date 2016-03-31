({
    sendPhone : function(component, event, helper) {
        console.log("Sending phone");

        var phoneEvent = $A.get("e.phoneTest:phoneMessage");
        phoneEvent.setParams({
        		text: component.get("v.inputText")
        });
        phoneEvent.fire();
        component.set("v.inputText", "");
    }
})
