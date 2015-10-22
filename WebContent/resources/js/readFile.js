function delegateFileSelect(fileName){               
    if (!window.File || !window.FileReader || !window.FileList || !window.Blob) {
        alert('The File APIs are not fully supported in this browser.');
        return;
    }   
    var str = fileName==1 ? "partFile" : "partFileTest";
    input = document.getElementById('form:'+str);
    console.log(fileName);
    console.log(str);
    console.log(input);
    console.log(input.files);
    if (!input) {
      alert("Um, couldn't find the fileinput element.");
   }
   else if (!input.files) {
      alert("This browser doesn't seem to support the `files` property of file inputs.");
   }
   else if (!input.files[0]) {
      alert("Please select a file before clicking 'Load'");               
   }
   else {
      var file = input.files[0];
      fr = new FileReader();
      //fr.onload = receivedText;
      fr.readAsText(file);
      //fr.readAsDataURL(file);
      if(fileName==1){
    	  str = "interfaceFileName";
    	  interfaceFile = true;
      }else{
    	  str = "testFileName";
    	  testFile =true;
      }
    	  
      document.getElementById("form:"+str).value = file.name;
      
      if(interfaceFile && testFile)
    	  $("#form")[0][9].hidden = false;
      
      return true;
   }
}

