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
  <form id='zoekenopnaamform'>
	<label>Naam:<span>${fouten.naam}</span> 
		<input name='naam' value='${param.naam}' autofocus required>
	</label>
    <input type='submit' value='Zoeken'>
  </form>
  <c:if test='${not empty param and empty fouten and empty namen}'>
  	Artikel niet gevonden
  </c:if>
  <c:if test='${not empty namen}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th><th>Nummer</th><th>Aankoopprijs</th><th>Verkoopprijs</th><th>Soort<th>Houdbaarheid/Garantie</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${namen}' var='artikel'>
          <c:set var='soortArtikel' value="${artikel['class'].simpleName}" />
          <tr>
            <td>${artikel.naam}</td>
            <td>${artikel.id}</td>
            <td><fmt:formatNumber value='${artikel.aankoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></td>
            <td><fmt:formatNumber value='${artikel.verkoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></td>
            <td>${soortArtikel}</td>
            <td><c:if test='${soortArtikel eq "FoodArtikel"}'>${artikel.houdbaarheid}</c:if>
            	<c:if test='${soortArtikel eq "NonFoodArtikel"}'>${artikel.garantie}</c:if></td>
          </tr>
        </c:forEach>
      </tbody>
    </table> 
  </c:if>
</body>
</html> 