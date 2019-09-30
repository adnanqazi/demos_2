	const readLine = require("readline");
	const fs = require("fs");

	var appRouter = app => {
		app.get("/list", (req, res) => {
			var stringFile =  fs.readFileSync("/tmp/data.dat",
		    {encoding:'utf-8', flag:'r'});
			var stringArray = stringFile.split("\n");
			console.log(stringArray);
	    var returnData = "";

			stringArray.map(
					element=> {
						if(element) {
							if(element.indexOf("\"")!=-1){
								throw "FATAL: [names with quotes inside not supported]";
								}
						returnData = returnData + "\"" + element + "\"" + ","
						}
					}
				)

			returnData = returnData.substring(0,returnData.length - 1);
			returnData = "["+ returnData + "]";
			console.log(returnData);
			res.header("Content-Type", "application/json");
			res.send(returnData);
	})}

	module.exports=appRouter;
