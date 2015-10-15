<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v' %>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Artikel Overzicht'/>
</head>
<body>
  <v:menu/>
  <h1>Overzicht Artikels</h1>
  <c:if test='${not empty artikels}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th>
          <th>Nummer</th>
          <th>Aankoopprijs</th>
          <th>Verkoopprijs</th>
          <th>Soort
          <th>Houdbaarheid/Garantie</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${artikels}' var='artikel'>
          <c:url value="" var="artikelURL">
             <c:param name="artikelid" value="${artikel.id}"/>
          </c:url>
          <c:set var='soortArtikel' value="${artikel['class'].simpleName}" />
          <tr>
            <td><a href='${artikelURL}'>${artikel.naam}</a></td>
            <td><a href='${artikelURL}'>${artikel.id}</a></td>
            <td><a href='${artikelURL}'><fmt:formatNumber value='${artikel.aankoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></a></td>
            <td><a href='${artikelURL}'><fmt:formatNumber value='${artikel.verkoopprijs}' minFractionDigits='2' maxFractionDigits='2'/></a></td>
            <td><a href='${artikelURL}'>${soortArtikel}</a></td>
            <td><a href='${artikelURL}'><c:if test='${soortArtikel eq "FoodArtikel"}'>${artikel.houdbaarheid}</c:if>
            	<c:if test='${soortArtikel eq "NonFoodArtikel"}'>${artikel.garantie}</c:if></a>
            </td>
          </tr>
          <c:if test='${param.artikelid eq artikel.id}'>
          <tr>
          	<td colspan = "6">
          		<table>
          			<thead>
          				<tr>
          					<th>Korting vanaf #</th>
          					<th>Korting percentage</th>
          				</tr>
          			</thead>
          			<tbody>
          				<c:forEach items='${artikel.kortingen}' var='korting'>
          				<tr>
          					<td>${korting.vanafAantal}</td>
          					<td>${korting.kortingsPercentage}</td>
          				</tr>
          				</c:forEach>
          			</tbody>
          		</table>
          	</td>
          </tr>	
  		  </c:if>
        </c:forEach>
      </tbody>
    </table> 
  </c:if>
</body>
</html> 