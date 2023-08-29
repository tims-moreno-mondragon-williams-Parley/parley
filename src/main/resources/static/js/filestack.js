window.addEventListener('DOMContentLoaded', function () {
   const key = iGotTheKey;
   const client = filestack.init(key);
   const options = {
      onUploadDone: (res) => {
       const imageUrl = res.filesUploaded[0].url;
       let imageConatainer = document.getElementById("uploaded-image");
          let imageinput = document.getElementById("profile_pic");
          imageConatainer.src = imageUrl;
          imageinput.value = imageUrl;
       console.log(imageinput.value);
      }
   }
   const picker = client.picker(options);
   const openBtn = document.getElementById("upload-profile-pic");
   openBtn.addEventListener("click", () => picker.open());
});


