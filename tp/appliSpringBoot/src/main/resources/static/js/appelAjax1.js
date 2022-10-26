

var traiterReponse = function(response) {
	//response ici au format "json string"
	console.log(response);
	var zoneResultat = document.getElementById("spanRes");
	var jsCompte = JSON.parse(response);
	zoneResultat.innerHTML = jsCompte.solde; //ou .label
}

function onSearchCompte() {
	var zoneSaisieNumero = document.getElementById("txtNumero");
	var numero = zoneSaisieNumero.value;
	console.log("numero=" + numero);
	var urlWsGet = "./api-bank/compte/" + numero;
	makeAjaxGetRequest(urlWsGet, traiterReponse); //non bloquant (asynchrone)
	//....
}
