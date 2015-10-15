<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>

<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikel toevoegen' />
</head>
<body>
	<v:menu />
	<h1>Artikel toevoegen</h1>
	<form method='post' id='toevoegform'>
		<label>Naam:<span>${fouten.naam}</span> 
			<input name='naam' value='${param.naam}' autofocus required>
		</label> <br/>
		<label>Aankoopprijs:<span>${fouten.aankoopprijs}</span>
			<input name='aankoopprijs' value='${param.aankoopprijs}' required type='number' min='0' step='0.01'>
		</label> <br/>
		<label>Verkoopprijs:<span>${fouten.verkoopprijs}</span>
			<input name='verkoopprijs' value='${param.verkoopprijs}' required type='number' min='0' step='0.01'>
		</label> <br/>
		<span>${fouten.verlies}</span> <br/>
		<h3>Type:</h3> 
			<input type="radio" name="artikeltype" id='food' value="Food">Food<br/>
			<label>Houdbaarheid: <input type="text" name='houdbaarheid' id='houdbaarheid' value='${param.houdbaarheid}' required></label><br/>
  			<input type="radio" name="artikeltype" id="nonfood" value="Non-Food">Non-Food<br/>
  			<label>Garantie: <input type="text" name='garantie' id='garantie' value='${param.garantie}' required></label>
		<br/><br/>
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
	<script>
      document.getElementById('toevoegform').onsubmit = function() {
        document.getElementById('toevoegknop').disabled = true;
      };
      document.getElementById('food').onclick = enableDisableInputs;
    	document.getElementById('nonfood').onclick = enableDisableInputs;
    	enableDisableInputs();
    	function enableDisableInputs() {
    	  document.getElementById('houdbaarheid').disabled = ! document.getElementById('food').checked;
    	    document.getElementById('garantie').disabled = ! document.getElementById('nonfood').checked; 
    	};
    </script>
</body>
</html>
