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
  <h1>Artikels per groep</h1>
  <ul class='inlinelist'>
  <c:forEach items='${artikelgroepen}' var='artikelgroep'>
    <c:url value='' var='url'>
      <c:param name='id' value='${artikelgroep.id}' />
    </c:url>
    <li><a href='${url}'>${artikelgroep.naam}</a></li>
  </c:forEach>
  </ul>
  <c:if test='${not empty artikelgroep}'>
    <h2>${artikelgroep.naam}</h2> 
    <c:if test='${not empty param and empty fouten and empty artikelgroep.artikels}'>
  	Geen Artikels gevonden in deze groep
  	</c:if>
    <c:if test='${not empty artikelgroep.artikels}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th><th>Nummer</th><th>Aankoopprijs</th><th>Verkoopprijs</th><th>Soort<th>Houdbaarheid/Garantie</th><th>Groep</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${artikelgroep.artikels}' var='artikel'>
          <c:set var='soortArtikel' value="${artikel['class'].simpleName}" />
          <tr>
            <td>${artikel.naam}</td>
            <td>${artikel.id}</td>
            <td><fmt:formatNumber value='${artikel.aankoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></td>
            <td><fmt:formatNumber value='${artikel.verkoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></td>
            <td>${soortArtikel}</td>
            <td><c:if test='${soortArtikel eq "FoodArtikel"}'>${artikel.houdbaarheid}</c:if>
            	<c:if test='${soortArtikel eq "NonFoodArtikel"}'>${artikel.garantie}</c:if></td>
            <td>${artikel.artikelgroep.naam}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>  
    </c:if>
  </c:if>  
</body>
</html> 