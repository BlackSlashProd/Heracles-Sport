<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<style type="text/css">
    #form_connect {
        float:left;
        width:500px;
    }
    #form_connect fieldset {
        padding:10px;
    }
    #form_connect label {
        float:left;
        width:150px;
    }
    #form_connect p {
        font-style:italic;
        font-size:14px;
    }
    #form_connect p.error, p.succes {
        clear:both;
        padding-top:20px;
        font-weight:bold;
        text-align:center;
    }  
    #form_connect #mail, #form_connect #psswd {
        float:right;
        width:250px; height:20px;        
    }
    #form_connect #submit_connect {
        float:right;
        width:100px; height:30px;
        font-weight:bold;
        border-radius:5px;
        border:2px solid #FFFFFF;
        color:black;
        background:#00CC00;
        cursor:pointer;
    }
    #form_connect #create_account {
        float:left;
        height: 20px;
        padding: 5px;
    }
</style>
<section>
    <h2>Home Page</h2>
    <p>
        <b><u>Pour les utilisateurs non connectés :</b></u><br/>
        Descriptif du jeu et formulaire de connection ainsi qu'un lien vers la création de compte.<br/><br/>
        <b><u>Pour les joueurs connectés :</b></u><br/>
        Récapitulatif du compte (nombre de point, mise actuelle en jeu, ...).<br/><br/>
        <b><u>Menu :</b></u><br/>
        Les liens disponibles dans le menu seront différents si l'utilisateur est connectés ou non.<br/> 
        On pourra éventuellement penser à un bouton "Deconnexion" dans le menu quand le joueur sera connecté.<br/><br/>
    </p>
    <p>
        <b><u>Servlets de test :</u></b><br/>
        <a href="/test">ServletTest</a><br/>
        <a href="/testjsp">ServletJSPTest</a><br/><br/>
    </p>
    <p>
        <b><u>Formulaire de connexion :</u></b><br/>
    </p>
    <form id="form_connect" method="post" action="/connect">
        <fieldset>
            <legend>Connexion</legend>
            <p>Vous pouvez vous connecter via ce formulaire.<br/><br/>
            </p>
    
            <label for="mail">Adresse email</label>
            <input type="mail" id="mail" name="mail" value="<c:out value="${user.mail}"/>" size="20" maxlength="60" />
            
            <br />
            <label for="psswd">Mot de passe</label>
            <input type="password" id="psswd" name="psswd" value="" size="20" maxlength="20" />
            
            <br /><br />
    
            <a id="create_account" href="/account">Pas encore de compte ?</a> <input id="submit_connect" type="submit" value="Connexion" /> 
            <br />
            <p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
            <c:if test="${!empty sessionScope.session}">
                <p class="succes">Vous êtes connecté(e) avec l'email : ${sessionScope.session.mail}</p>
            </c:if>
        </fieldset>
    </form>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />