<%@page import="upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.configs.Configs" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Paris</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.min.css" type="text/css" />
        <link rel="stylesheet" href="css/schedules.min.css" type="text/css" />
        <link rel="stylesheet" href="css/paris.min.css" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="js/script_paris.min.js"></script>
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Rencontres à venir</h2>
			    <p>
			        Liste des rencontres sur lesquelles miser.<br/><br/>
			    </p>
                <% 
                   UserModel user = (UserModel)request.getAttribute("user");
                   if(user != null) { %>
                   <div class="user_zone">
                   <p>
                        <b class="orange"> Mise disponible :</b> <b class="green"><%= user.getUser_point() %></b><br/>
                   </p>
                   </div>
                <% } %>
			    <div class="schedules">
			        <%
			        List<ScheduleTeamModel> scheds = (List<ScheduleTeamModel>) request.getAttribute("schedules");
			        for (ScheduleTeamModel sched : scheds) {
			        %>
			        <div class="sched">
			            <h2> <%= sched.getSched_sportName() %> </h2>
			            <h3>
			                <%= sched.getSched_home_team().getTeam_name() %> 
			                <span class="orange">VS</span> 
			                <%= sched.getSched_away_team().getTeam_name() %>
			            </h3>
			            <p>
			                <b>Date : </b><%= sched.getSched_dateClean() %><br/><br/>
			                <b>Temps restant : </b><%= sched.computeTimeLeft() %><br/><br/>
			                <b>A se partager : </b><b class="orange"><%= sched.getTotalBets() %></b><br/><br/>
			            </p>
			            <% 
			            if(user != null) {
			                String active = (String)request.getAttribute("active"); 
			                if(active!=null && active.compareTo(sched.getSched_id())==0) {
			            %>
			            <div class="paris_zone active">
			            <% } else { %>
			            <div class="paris_zone">
			            <% } %>
			                <a class="paris_show" href="#">Parier sur cette rencontre</a>
				            <form class="paris_form" method="post" action="/paris">
					            <fieldset>
					                <input id="<%= sched.getSched_id() %>" type="hidden" name="sched_id" value="<%= sched.getSched_id() %>" />
					                <legend>Parier sur cette rencontre</legend>
					                <% if(DataStore.hasParisForUserAndSchedule(user.getUser_id(), sched.getSched_id())) { %>
					                    <div class="fiel_clear">
					                    <% if(request.getAttribute("form") != null && (active!=null && active.compareTo(sched.getSched_id())==0)) { %>
                                            <p class="succes">${form.result}<br/></p>
                                        <% } else { %>
                                            <p class="succes">
                                                Vous avez déjà parié sur cette rencontre.<br/>
                                            </p>
                                        <% } %>
                                        <p style="text-align:center;"><a href="/histo">Consultez vos paris</a></p>
                                        </div>
					                <% } else { %>
					                 <% if(active!=null && active.compareTo(sched.getSched_id())==0
					                		  && request.getAttribute("form") != null) {
                                       %>
					                    <div class="fiel_clear">
					                        <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
					                    </div>
					                <% } %>
					                <div class="fiel_clear">
					                    <label for="paris_type">Type de paris</label>
					                    <select class="paris_type" name="paris_type">
					                       <option value="vict" selected="selected">Victoire (x<%= Configs.getMultTeamVict() %>)</option>
					                       <option value="scor">Score (x<%= Configs.getMultTeamScorOne() %> ET x<%= Configs.getMultTeamScorBoth() %>)</option>
					                    </select>
					                </div>
				                    <div class="paris_type_vict fiel_clear">
				                        <label for="paris_vict">Equipe victorieuse</label>
				                        <select class="paris_vict" name="paris_vict">
				                           <option value="all" selected="selected">égalité</option>
				                           <option value="teamhome"><%= sched.getSched_home_team().getTeam_name() %></option>
				                           <option value="teamaway"><%= sched.getSched_away_team().getTeam_name() %></option>
				                        </select>
				                    </div>
				                    <div class="paris_type_scor fiel_clear" >
				                        <div>
					                        <label for="paris_scor_eqp">Equipe</label>
	                                        <select class="paris_scor_eqp" name="paris_scor_eqp">
	                                           <option value="all" selected="selected">Les deux (x<%= Configs.getMultTeamScorBoth() %>)</option>
	                                           <option value="home"><%= sched.getSched_home_team().getTeam_name() %> (x<%= Configs.getMultTeamScorOne() %>)</option>
	                                           <option value="away"><%= sched.getSched_away_team().getTeam_name() %> (x<%= Configs.getMultTeamScorOne() %>)</option>
	                                        </select>
                                        </div>
                                        <div class="scor_eqp_home">
					                        <label for="paris_scor_teamhome"><%= sched.getSched_home_team().getTeam_name() %></label>
					                        <input type="text" id="paris_scor_teamhome" name="paris_scor_teamhome" value="0" size="5" maxlength="5" />
					                    </div>
					                    <div class="scor_eqp_away"> 
					                        <label for="paris_scor_teamaway"><%= sched.getSched_away_team().getTeam_name() %></label>
					                        <input type="text" id="paris_scor_teamaway" name="paris_scor_teamaway" value="0" size="5" maxlength="5" />
				                        </div>
				                    </div>
					                <div class="fiel_cleear">
					                    <div>
				                            <label for="paris_bet">Mise</label>
				                            <input type="text" id="paris_bet" name="paris_bet" value="1" size="5" maxlength="5" />
				                        </div>
				                        <div>
				                            <input type="submit" value="Parier" class="button bg_gradient_green" />
				                        </div>
				                    </div>              
				                    <% } %>  
					            </fieldset>
				            </form>
			            </div>
			        <% } %>
			        </div>
			        <%
			        }
			        if(scheds.size()==0) {
			        %>
			        	<div class="nosched">
			        	   Aucune rencontre sportive à venir.<br/>
			        	</div>
			        <%
			        }
			        %>
			    </div>
			</section>
        </div>
    </body>
</html>
