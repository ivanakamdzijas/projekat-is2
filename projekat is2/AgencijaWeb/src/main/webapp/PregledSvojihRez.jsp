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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">


<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="js/prefix-free.min.js"></script>
<title>Prikaz trenutnih rezervacija</title>
</head>
<body>

	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">

<c:if test="${!empty rezervacije}">
		<table border="1" class="login1">
			<tr>
				<th>Destinacija</th>
				<th>Opis smestaja</th>
				<th>Datum polaska</th>
				<th>Datum dolaska</th>
				<th>Iznos</th>
				<th>Uplata</th>
			</tr>
			<c:forEach items="${rezervacije }" var="r">
				<tr>
					<td>${r.aranzman.smestaj.destinacija.nazivD}</td>
					<td>${r.aranzman.smestaj.opisS }</td>
					<td>${r.aranzman.datumP }</td>
					<td>${r.aranzman.datumD }</td>
					<td>${r.ukupnaCena }</td>
					<c:if test="${r.placeno }">
						<td>placeno</td>
					</c:if>
					<c:if test="${!r.placeno }">
						<td>Nije placeno. Molimo Vas da uplatite ceo iznos 10 dana
							pre polaska. Hvala.</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
</c:if>
<c:if test="${empty rezervacije }">
	<b>Nema aktivnih rezervacija. Pogledajte nasu aktuelnu ponudu!</b>
</c:if>
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