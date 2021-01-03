<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
   
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
   
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="js/prefix-free.min.js"></script>
  
	 
<title>Unos aranzmana</title>
</head>
<body>

<div class="body"></div>
		<div class="grad"></div>
		<div class="header">
			
			<form action = "/Agencija/auth/zaposleni/unosAranzmana" method = "get" class ="login">
		<b>Unos aranzmana</b>
		<table>
			<tr><td>Naziv aranzmana:</td><td><input type="text" name ="nazivA"  value = ''></td></tr>
			<tr><td>Datum polasaka:</td><td><input type ="date" name = "datumP" ></td></tr>
			<tr><td>Datum dolasaka:</td><td><input type ="date" name = "datumD"></td></tr>
			<tr><td>Broj slobodnih mesta:</td><td><input type="text" name = "brSlobMesta"></td></tr>
			<tr><td>Smestaj:</td><td><select name="idSmestaj">
										<option  value="0" >Unos novog smestaja</option>
										<c:forEach items = "${smestaji }" var = "s">
											<option value = "${ s.idSmestaj}">${s.nazivS }</option>
										</c:forEach>
									</select></td></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr><td></td><td><input type="submit" value = "Sacuvaj"></td></tr>
			
		</table>
		<br><br>
 		${porukaAr }<br><br>
 		${porukaS }
	
	
	</form><br><br>
	
		</div>
		<br>

	<div id='cssmenu'>
<ul>
   <li><a href='/Agencija/index.jsp'>Pocetna stranica</a></li>
   <li class='active has-sub'><a href='#'>Odaberite akciju</a>
      <ul>
      <security:authorize access="hasRole('korisnik')">
         <li class='has-sub'><a href='/Agencija/auth/korisnik/getPonuda'>Ponuda</a>
         </li>
         <li class='has-sub'><a href='/Agencija/auth/korisnik/getRez'>Trenutne rezervacije</a>
         </li>
         </security:authorize>
         <security:authorize access="hasRole('zaposleni')">
         	<li class='has-sub'><a href='/Agencija/auth/zaposleni/getAranzmani'>Korisnici aranzmana</a>
         </li>
         <li class='has-sub'><a href='/Agencija/auth/zaposleni/getSmestajiIDestinacije'>Unos aranzmana</a>
         </li>
         <li class='has-sub'><a href='/Agencija/auth/zaposleni/getSviAranzmani'>Brisanje aranzmna</a>
         </li>
         <li class='has-sub'><a href='/Agencija/izvestaji/getIzvestajNeplacenih'>Izvestaj - neplacene rezervacije</a>
         </li>
         </security:authorize>
         <li class='has-sub'><a href='/Agencija/auth/logout'>Odjava</a>
         </li>
      </ul>
   </li>
   
   

</ul>
</div>
</body>
</html>