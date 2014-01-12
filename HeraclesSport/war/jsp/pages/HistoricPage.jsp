<%@page import="upmc.aar2013.project.heraclessport.server.configs.Teams"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultScoreModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Heracles Sport - Historique</title>
        <link rel="icon" type="image/png" href="images/style/favicon.png" />
        <link rel="stylesheet" href="css/general.css" type="text/css" />
        <link rel="stylesheet" href="css/schedules.css" type="text/css" />
    </head>
    <body>
        <div id="page">
			<jsp:include page="/jsp/general/NavigationSection.jsp" />
			<section>
			    <h2>Historique de vos paris</h2>
			    <p>
			        Liste des paris en cours et des paris terminés.<br/> 
			        <br/>
			    </p>
			    <h3>Paris en cours</h3>
			    <div class="schedules">
			    	<%
			        List<ParisModel> parisNotFinish = (List<ParisModel>) request.getAttribute("parisNotFinish");
			        for (ParisModel pari : parisNotFinish) {
			        	ScheduleTeamModel schedule = (ScheduleTeamModel) pari.getParis_sched();
				        %>
				        <div class="sched">
				            <h2> <%=schedule.getSched_sportName()%> </h2>
				            <h3>
				                <%=schedule.getSched_home_team().getTeam_name()%> 
				                <span class="orange">VS</span> 
				                <%=schedule.getSched_away_team().getTeam_name()%>
				            </h3>
				            <p>
				                <b>Date : </b><%=schedule.getSched_date()%><br/><br/>
	                            <b>Statut : </b>
	                            <% if(!schedule.isSched_isStart()) { %>
	                                 Non commencé (Temps restant : <%= schedule.computeTimeLeft() %>)
	                            <% } else { %>
	                                 En cours.
	                            <% } %>
	                            <br/><br/>				                
				            </p>
				            <p>
                            <% if (pari instanceof ParisScoreModel) { %>
                                <b>Pari sur le score : </b> <br/>
                                    <% 
                                    if(((ParisScoreModel)pari).getParis_team()==Teams.ALL) {
                                    %>
                                        <%=((ParisScoreModel)pari).getScore_team_home()%> 
                                        - 
                                        <%=((ParisScoreModel)pari).getScore_team_away()%>   
                                    <% } else if(((ParisScoreModel)pari).getParis_team()==Teams.HOME) { %> 
                                        <%= ((ScheduleTeamModel)pari.getParis_sched()).getSched_home_team().getTeam_name() %>
                                        : 
                                        <%=((ParisScoreModel)pari).getScore_team_home()%>
                                    <% } else { %>       
                                        <%= ((ScheduleTeamModel)pari.getParis_sched()).getSched_away_team().getTeam_name() %>
                                        : 
                                        <%=((ParisScoreModel)pari).getScore_team_away()%>                                   
                                    <% } %>
                                    <br/><br/>
                            <% } else if (pari instanceof ParisVictoryModel) { 
                            	   if(((ParisVictoryModel)pari).getParis_team()!=null){ %>
                            	        <b>Pari sur l'équipe : </b> <br/>
                                       <%=((ParisVictoryModel)pari).getParis_team().getTeam_name()%>  
                                <% } else { %>  
                                        <b>Pari sur l'égalité des deux équipes. </b> <br/>
                                <% } %>     
                                    <br/><br/>
                            <% } %>				            
				            </p>
				        </div>
			        <%
			        }
                    if(parisNotFinish.size()==0) {
                    %>
                        <div class="nosched">
                           Aucun paris en cours.<br/>
                        </div>
                    <%
                    }
                    %>
			    </div>
                <h3>Paris terminés</h3>
                <div class="schedules">
                    <%
                    List<ParisModel> parisFinish = (List<ParisModel>) request.getAttribute("parisFinish");
                    for (ParisModel pari : parisFinish) {
                        ScheduleTeamModel schedule = (ScheduleTeamModel) pari.getParis_sched();
                        %>
                        <div class="sched">
                            <h2> <%=schedule.getSched_sportName()%> </h2>
                            <h3>
                                <%=schedule.getSched_home_team().getTeam_name()%> 
                                <span class="orange">VS</span> 
                                <%=schedule.getSched_away_team().getTeam_name()%>
                            </h3>
                            <p>
                                <b>Date : </b><%=schedule.getSched_date()%><br/><br/>
                            </p>
                            <p>
                                <%
                                    ResultScoreModel score = schedule.getSched_res_score();
                                    if(score != null) {
                                %>
                                    <b><u>Résultats :</u></b><br/>
                                           <b>Scores :</b> 
                                           <%=score.getScore_res_score_home()%> 
                                           - 
                                           <%=score.getScore_res_score_away()%>             
                                           <br/><br/>
                                <%
                                    }
                                %>
                            </p>
                            
                            <% if(pari.isIswin()) { %>
                               <p class="green">
                                    Paris Gagné !<br/>
                            <% } else { %>
                               <p class="orange">
                                    Paris Perdu !<br/>
                            <% } %>
                            <% if (pari instanceof ParisScoreModel) { %>
                                <b>Pari sur le score : </b> <br/>
                                    <% 
                                    if(((ParisScoreModel)pari).getParis_team()==Teams.ALL) {
                                    %>
                                        <%=((ParisScoreModel)pari).getScore_team_home()%> 
                                        - 
                                        <%=((ParisScoreModel)pari).getScore_team_away()%>   
                                    <% } else if(((ParisScoreModel)pari).getParis_team()==Teams.HOME) { %> 
                                        <%= ((ScheduleTeamModel)pari.getParis_sched()).getSched_home_team().getTeam_name() %>
                                        : 
                                        <%=((ParisScoreModel)pari).getScore_team_home()%>
                                    <% } else { %>       
                                        <%= ((ScheduleTeamModel)pari.getParis_sched()).getSched_away_team().getTeam_name() %>
                                        : 
                                        <%=((ParisScoreModel)pari).getScore_team_away()%>                                   
                                    <% } %>
                                    <br/><br/>
                            <% } else if (pari instanceof ParisVictoryModel) { 
                                   if(((ParisVictoryModel)pari).getParis_team()!=null){ %>
                                        <b>Pari sur l'équipe : </b> <br/>
                                       <%=((ParisVictoryModel)pari).getParis_team().getTeam_name()%>  
                                <% } else { %>  
                                        <b>Pari sur l'égalité des deux équipes. </b> <br/>
                                <% } %>     
                                    <br/><br/>
                            <% } %>                         
                            </p>
                        </div>
                    <%
                    }
                    if(parisFinish.size()==0) {
                    %>
                        <div class="nosched">
                           Aucun paris terminé.<br/>
                        </div>
                    <%
                    }
                    %>
                </div>
			</section>
        </div>
    </body>
</html>