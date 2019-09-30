var express = require("express");
var app = express();

app.use(express.static('public'));
var routes = require("./server/route.js")(app);

var server = app.listen(3000, function () {
    console.log("Listening on port %s...", server.address().port);
});
