<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${empty artikel ? "Artikel zoeken" : artikel.naam}'/>
</head>
<body>
  <v:menu/>
  <h1>Artikel zoeken</h1>
  <form>
    <label>Nummer:<span>${fouten.id}</span>
    <input name='id' value='${param.id}'
      required autofocus type='number' min='1'></label>
    <input type='submit' value='Zoeken'>
  </form>
  <c:if test='${not empty param and empty fouten and empty artikel}'>
  Artikel niet gevonden
  </c:if>
  <c:if test='${not empty artikel}'>
    ${artikel.naam}, aankoopprijs: &euro; <fmt:formatNumber value='${artikel.aankoopprijs}'/>, verkoopprijs: &euro; <fmt:formatNumber value='${artikel.verkoopprijs}'/>, , winstpercentage: <fmt:formatNumber value='${artikel.getWinstPercent()}'/> % 
  	<c:set var='soortArtikel' value="${artikel['class'].simpleName}" />
  	<c:if test='${soortArtikel eq "FoodArtikel"}'>
  		houdbaarheid: ${artikel.houdbaarheid}
  	</c:if>
  	<c:if test='${soortArtikel eq "NonFoodArtikel"}'>
  		garantie: ${artikel.garantie}
  	</c:if>
  	<br/>${artikel.artikelgroep.naam}
  </c:if>
</body>
</html> 