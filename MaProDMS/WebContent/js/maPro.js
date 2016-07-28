

function uploadMaProDocument(){

	$('#fileToUpload').click();
		var fileToUploadVar = document.getElementById("fileToUpload").files[0];
alert(fileToUploadVar);

		if (fileToUploadVar) {
			alert("i if");
			var formdata = new FormData();
			formdata.append("fileToUpload", fileToUploadVar);
			var xhr = new XMLHttpRequest();

			xhr.open("POST", "/MaProDMS/uploadMaProDocument", true);
			xhr.send(formdata);
			xhr.onload = function(e) {
				if (this.status == 200) {
					var	respJSON = this.response;
					if(respJSON == "success"){
						alert(respJSON.errorMessage);
					}
					
				}
			};
		} else {
			alert("i else");
		}
		

}

function browseMaProDocument(){

	$('#fileToBrowse').click();
		var fileToBrowseVar = document.getElementById("fileToBrowse");
		alert(fileToBrowseVar);

		if (fileToBrowseVar) {
			alert("i if");
			var formdata = new FormData();
			formdata.append("fileToBrowse", fileToBrowseVar);
			var xhr = new XMLHttpRequest();

			xhr.open("GET", "/MaProDMS/listdocuments", true);
			xhr.send(formdata);
			xhr.onload = function(e) {
				if (this.status == 200) {
					var	respJSON = this.response;
					if(respJSON == "success"){
						alert(respJSON.errorMessage);
					}
					
				}
			};
		} else {
			alert("i else");
		}
		

}



function displayFile(objId){
	alert(objId);
	var xhr = new XMLHttpRequest();

	xhr.open("POST", "/MaProDMS/getDocument/"+objId, true);
	xhr.send();
	xhr.onload = function(e) {
		if (this.status == 200) {
			var	respJSON = this.response;
			if(respJSON == "success"){
				alert(respJSON.errorMessage);
			}
			
		}
	};
}