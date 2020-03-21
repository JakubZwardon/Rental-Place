//Ustawia min dla pola wyboru daty
function setMinDateToChoose() {
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth() + 1;
  var yyyy = today.getFullYear();
      
  if(dd<10) {
  dd = '0' + dd;
  }
  if(mm<10) {
    mm='0'+mm;
  }
      
  today = yyyy + '-' + mm + '-' + dd;
  document.getElementById("returnDateInput").setAttribute("min", today);
}

//ustawia pusty widok dla wyboru sprzętu
function setFormDisplay() {
  document.getElementById("equipmentsList").selectedIndex = -1;
}