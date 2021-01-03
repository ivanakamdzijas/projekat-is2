<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/styles.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="js/prefix-free.min.js"></script>

<title>Ponuda aranzmana</title>

</head>
<body>

	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">
		
		<form action="/Agencija/auth/korisnik/filtriranje" method="get"
			class="login1">
			Filtritaj po ceni:<br> <select name="idOpcija">
				<option value="1">rastuce</option>
				<option value="2">opadajuce</option>
			</select><br>
			<br> <input type="submit" value="Filtriraj"><br>
			<br>
			<table border="1" >
				<tr>
					<th>Naziv aranzmana</th>
					<th>Destinacija</th>
					<th>Datum polaska</th>
					<th>Datum dolaska</th>
					<th>Smestaj</th>
					<th>Opis smestaja</th>
					<th>Cena po osobi (na dan)</th>
					<th>Rezervacija</th>
				</tr>
				<c:forEach items="${aranzmani }" var="a">
					<tr>
						<td>${a.nazivA }</td>
						<td>${a.smestaj.destinacija.nazivD }</td>
						<td>${a.datumP }</td>
						<td>${a.datumD }</td>
						<td>${a.smestaj.nazivS}</td>
						<td>${a.smestaj.opisS }</td>
						<td>${a.smestaj.cenaPoOsobi }</td>
						<td><a
							href="/Agencija/auth/korisnik/unosRezervacije?idA=${a.idAranzman }">Rezervisite</a></td>
					</tr>
				</c:forEach>
			</table>

		</form>
	</div>
	<br>

	<div id='cssmenu'>
		<ul>
			<li><a href='/Agencija/index.jsp'>Pocetna stranica</a></li>
			<li class='active has-sub'><a href='#'>Odaberite akciju</a>
				<ul>
					<security:authorize access="hasRole('korisnik')">
						<li class='has-sub'><a
							href='/Agencija/auth/korisnik/getPonuda'>Ponuda</a></li>
						<li class='has-sub'><a href='/Agencija/auth/korisnik/getRez'>Trenutne
								rezervacije</a></li>
					</security:authorize>
					<security:authorize access="hasRole('zaposleni')">
						<li class='has-sub'><a
							href='/Agencija/auth/zaposleni/getAranzmani'>Korisnici
								aranzmana</a></li>
						<li class='has-sub'><a
							href='/Agencija/auth/zaposleni/getSmestajiIDestinacije'>Unos
								aranzmana</a></li>
						<li class='has-sub'><a
							href='/Agencija/auth/zaposleni/getSviAranzmani'>Brisanje
								aranzmna</a></li>
						<li class='has-sub'><a
							href='/Agencija/izvestaji/getIzvestajNeplacenih'>Izvestaj -
								neplacene rezervacije</a></li>
					</security:authorize>
					<li class='has-sub'><a href='/Agencija/auth/logout'>Odjava</a>
					</li>
				</ul></li>
		</ul>
	</div>



</body>
</html>