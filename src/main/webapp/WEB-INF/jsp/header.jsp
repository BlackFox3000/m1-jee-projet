<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrap_css"
	value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	value="/webjars/bootstrap/4.6.0-1/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="css" value="/style.css" />

<html>
	<head>
        <meta charset="UTF-8">
        <title>Spring boot application</title>
        <link rel="stylesheet" href="${css}">
        <link rel="stylesheet" href="${bootstrap_css}">
        <script src="${jquery_js}"></script>
        <script src="${bootstrap_js}"></script>
    </head>
    <body>
     <div>
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
           <a class="navbar-brand" href="/projet/auth">se connecter</a>
           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
           </button>
           <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
             <div class="navbar-nav">
               <a class="nav-item nav-link active" href="/projet/home">Accueil<span class="sr-only">(current)</span></a>
               <a class="nav-item nav-link" href="/projet/groupCreate">Créer un groupe</a>
               <a class="nav-item nav-link" href="/projet/personCreate">Créer une personne</a>
               <a class="nav-item nav-link disabled" href="#">Se déconnecter</a>
             </div>
           </div>
         </nav>
     </div>
