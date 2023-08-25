window.addEventListener('DOMContentLoaded', function () {
   const iGotTheKey = "AoiMA2SeORDMRIRZ5h9Qwz";
   const client = filestack.init(iGotTheKey);
   const options = {
      onUploadDone: (res) => console.log(res)
   }
   const picker = client.picker(options);

   const openBtn = document.getElementById("open-filestack");
   openBtn.addEventListener("click", () => picker.open());
});
