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
<title>Korisnici aranzmana</title>


</head>
<body>

	<div class="body"></div>
	<div class="grad"></div>
	<div class="header">
		${porukaO } <br><br>
		${porukaU }
		<form action="/Agencija/auth/zaposleni/korisniciAranzmana"
			method="get" class="login1">
			<select name="idAranzman">
				<c:forEach items="${aranzmani }" var="a">
					<option value="${a.idAranzman }">${a.nazivA }</option>
				</c:forEach>
			</select><br> <br>
			<br>
			<br>
			
			<br> <input type="submit" value="Prikazi">
		</form>
		<br> <br> <br> <br>
		
		<c:if test="${!empty rezervacije }">
			<form action="/Agencija/izvestaji/getIzvestajKorisnici" method="get"
				class="login">
				Prikaz rezervacija za aranzman: ${naziv } <br> <br>

				<table border="1">
					<tr>
						<th>Ime i prezime korisnika</th>
						<th>Datum polaska</th>
						<th>Uplata</th>
						<th>Otkazivanje rezervacije</th>
					</tr>
					<c:forEach items="${rezervacije }" var="r">
						<tr>
							<td>${r.agencijaUser.name }</td>

							<td>${r.aranzman.datumP }</td>
							<c:if test="${ r.placeno}">
								<td>placeno</td>
							</c:if>
							<c:if test="${ !r.placeno}">
								<td><a
									href="/Agencija/auth/zaposleni/unosUplate?idR=${r.idRezervacija }">Unos
										uplate</a></td>
							</c:if>
							<td><a
								href="/Agencija/auth/zaposleni/otkazivanje?idR=${r.idRezervacija }">Otkazivanje</a></td>
						</tr>
					</c:forEach>
				</table>
				<br> <br> <input type="submit" value="Generisi izvestaj">
				
			</form>
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

	<form action="/Agencija/auth/zaposleni/korisniciAranzmana" method="get">
		<select name="idAranzman">
			<c:forEach items="${aranzmani }" var="a">
				<option value="${a.idAranzman }">${a.nazivA }</option>
			</c:forEach>
		</select><br> <br> <input type="submit" value="Prikazi">
	</form>
	<br>
	<br>
	<c:if test="${!empty rezervacije }">
		<form action="/Agencija/izvestaji/getIzvestajKorisnici" method="get">
			Prikaz rezervacija za aranzman: ${naziv } <br> <br>

			<table border="1">
				<tr>
					<th>Ime i prezime korisnika</th>
					<th>Datum polaska</th>
					<th>Uplata</th>
					<th>Otkazivanje rezervacije</th>
				</tr>
				<c:forEach items="${rezervacije }" var="r">
					<tr>
						<td>${r.agencijaUser.name }</td>

						<td>${r.aranzman.datumP }</td>
						<c:if test="${ r.placeno}">
							<td>placeno</td>
						</c:if>
						<c:if test="${ !r.placeno}">
							<td><a
								href="/Agencija/auth/zaposleni/unosUplate?idR=${r.idRezervacija }">Unos
									uplate</a></td>
						</c:if>
						<td><a
							href="/Agencija/auth/zaposleni/otkazivanje?idR=${r.idRezervacija }">Otkazivanje</a></td>
					</tr>
				</c:forEach>
			</table>
			<br> <br> <input type="submit" value="Generisi izvestaj">
		</form>
	</c:if>

	${porukaO } ${porukaU }






</body>
</html>