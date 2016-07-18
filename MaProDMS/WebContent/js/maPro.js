

function uploadMaProDocument(){

	$('#fileToUpload').click();
		var fileToUploadVar = document.getElementById("fileToUpload").files[0];
alert(fileToUploadVar);

		if (fileToUploadVar) {
			alert("i if");
			var formdata = new FormData();
			formdata.append("fileToUpload", fileToUploadVar);
			var xhr = new XMLHttpRequest();

			xhr.open("POST", "/MaPro/uploadMaProDocument", true);
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