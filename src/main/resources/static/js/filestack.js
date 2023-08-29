window.addEventListener('DOMContentLoaded', function () {
   const iGotTheKey = "AoiMA2SeORDMRIRZ5h9Qwz";
   const client = filestack.init(iGotTheKey);
   const options = {
      onUploadDone: (res) => {
       const imageUrl = res.filesUploaded[0].url;
       const imageConatainer = document.getElementById("image-profile");
       imageConatainer.innerHTML = `<img src="${imageUrl}" alt="uploaded image" />`;
      }
   }
   const picker = client.picker(options);
   const openBtn = document.getElementById("open-filestack");
   openBtn.addEventListener("click", () => picker.open());
});





//  document.addEventListener('DOMContentLoaded', function () {
//     const apiKey = "AoiMA2SeORDMRIRZ5h9Qwz";
//     const client = filestack.init(apiKey);
//
//     const uploadProfilePicButton = document.getElementById("upload-profile-pic");
//     const profilePicInput = document.querySelector("input[name='profile-pic']");
//
//     uploadProfilePicButton.addEventListener("click", function () {
//        client.picker({
//           fromSources: ['local_file_system', "url"],
//           accepts: ["image/*"],
//           onFileUploadFinished: function (file) {
//              profilePicInput.value = file.url;
//           }
//        }).open();
//     });
//  });

