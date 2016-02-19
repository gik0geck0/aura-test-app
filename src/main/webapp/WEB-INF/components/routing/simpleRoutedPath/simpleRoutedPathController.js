({
    onLocationChange : function(component, event, helper) {
         console.log("Location change event: " + JSON.stringify(event));
         var token = event.getParam("token");
         console.log("Token: " + token);
         if ($A.util.isUndefinedOrNull(token)) {
             token="";
         }
         if (token || token === "") {
             var regex = component.get("v.route");
             var oldBody = component.get("v.body");
             console.log("Trying to match " + token + " with regex " + regex);
             if (token.match(regex)) {
                 var qs = event.getParam("querystring");
                 console.log("Creating component " + component.get("v.component") + " with attributes " + qs);
                 $A.createComponent(
                     component.get("v.component"),
                     helper.queryStringToMap(qs),
                     $A.getCallback(function(newCmp, status, statusMessagesList) {
                         // console.log("v.body has been set to " + JSON.stringify(newCmp));
                         component.set("v.body", newCmp);
                     })
                     );
             } else {
                 // "Cleanup" old component
                 component.set("v.body", "");
             }
         }
    }
})
