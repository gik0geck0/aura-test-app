({
    // From http://stackoverflow.com/questions/2090551/parse-query-string-in-javascript
    queryStringToMap : function(queryString) {
         if ($A.util.isUndefinedOrNull(queryString) || queryString === "") {
             return {};
         }
         var args = queryString.split('&'),
            argsParsed = {};

for (i=0; i < args.length; i++) {
    var arg = decodeURIComponent(args[i]);

    if (arg.indexOf('=') == -1) {
        argsParsed[arg.trim()] = true;
    } else {
        var kvp = arg.split('=');
        argsParsed[kvp[0]] = kvp[1];
    }
}
return argsParsed;
}
})
