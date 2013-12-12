<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Home Page</h2>
    <p>
        <b><u>Pour les utilisateurs non connectés :</b></u><br/>
        Descriptif du jeu et formulaire de connection ainsi qu'un lien vers la création de compte.<br/>
        <a href="/account">Création de compte</a><br/><br/>
        <b><u>Pour les joueurs connectés :</b></u><br/>
        Récapitulatif du compte (nombre de point, mise actuelle en jeu, ...).<br/><br/>
        <b><u>Menu :</b></u><br/>
        Les liens disponibles dans le menu seront différents si l'utilisateur est connectés ou non.<br/> 
        On pourra éventuellement penser à un bouton "Deconnexion" dans le menu quand le joueur sera connecté.<br/><br/>
    </p>
    <p>
        <b><u>Servlets de test :</u></b><br/>
        <a href="/test">ServletTest</a><br/>
        <a href="/testjsp">ServletJSPTest</a><br/>
    </p>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />