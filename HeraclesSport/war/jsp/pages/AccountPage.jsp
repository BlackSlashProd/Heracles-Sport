<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Account Page</h2>
    <p>
        Formulaire de création de compte pour devenir joueur.<br/><br/>
        <b style="color:#FF0000;">Ne pas doit pas être disponible pour les joueurs connectés.</b>
    </p>
    <form id="form_registr" class="log_form" method="post" action="registr">
    <fieldset>
            <legend>Devenir joueur</legend>
            <p>
                Pour pouvoir jouer il vous faut vous inscrire.<br/><br/>
            </p>

            <label for="mail">Adresse Email</label>
            <input type="email" id="mail" name="mail" value="" size="20" maxlength="80" />
            <span class="error">${errors['mail']}</span>
            <p class="form_clear"></p>

            <label for="psswd">Mot de passe</label>
            <input type="password" id="psswd" name="psswd" value="" size="20" maxlength="50" />
            <span class="error">${errors['psswd']}</span>
            <p class="form_clear"></p>

            <label for="confirm">Saisissez à nouveau votre mot de passe</label>
            <input type="password" id="confirm" name="confirm" value="" size="20" maxlength="50" />
            <span class="error">${errors['confirm']}</span>
            <p class="form_clear"></p>

            <label for="name">Nom d'utilisateur</label>
            <input type="text" id="name" name="name" value="" size="20" maxlength="50" />
            <span class="error">${errors['name']}</span>
            <p class="form_clear"></p>

            <input id="submit_registr" type="submit" value="Inscription" />
        </fieldset>
    </form>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />