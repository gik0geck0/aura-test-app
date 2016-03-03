({
	browsers: ['GOOGLECHROME', 'SAFARI', 'FIREFOX'],
	testAccessible: {
		doNotWrapInAuraRun: true,
		attributes : {
			label: "Label"
		},
		test: [
		    function testAccessible(component) {
		    		debugger
		    		$A.test.assertAccessible();
		    }
	    ]
	}
})