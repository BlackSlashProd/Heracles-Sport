<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>Servlet Paris Page</h2>
    <p>
        <b><u>Pour les utilisateurs non connectés :</b></u><br/>
        On ne voit que la liste des rencontres à venir.<br/><br/>
        <b><u>Pour les joueurs connectés :</b></u><br/>
        On voit la liste des rencontres à venir. On permet au joueur de parier sur les rencontres sur rencontres pour lesquelles il n'a pas déjà effectué de paris.
    </p>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />

