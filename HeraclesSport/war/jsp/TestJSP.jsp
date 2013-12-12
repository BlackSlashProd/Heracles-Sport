<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="ISO-8859-1">
        <title>Heracles Sport</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.css" type="text/css" />
    </head>
    <body>
        <div id="page">
            <header>
                <h1>Welcome to Heracles Sport site !</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="index.html">Accueil</a></li>
                    <li><a href="#">Parier</a></li>
                    <li><a href="#">Classement</a></li>
                </ul>
            </nav>
            <section>
	           <p> Random int: <%=     request.getAttribute("randInt") %> </p>
            </section>
        </div>
    </body>
</html>
