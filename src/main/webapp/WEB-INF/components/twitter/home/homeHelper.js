({
	loadTweets: function(component) {
		var loadAction = component.get("c.getTweets");
		loadAction.setCallback(this, $A.getCallback(function(result) {
			if (result.getState() === "SUCCESS") {
				var tweetsList = result.getReturnValue();
				console.log("Received tweets: " + JSON.stringify(tweetsList));
				component.set("v.tweets", tweetsList);
			} else {
				console.log("Error receiving tweets");
			}
		}));
		$A.enqueueAction(loadAction);
	}
})