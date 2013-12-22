<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.UserModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.ResultModel" %>
<%@ page import="upmc.aar2013.project.heraclessport.server.datamodel.ScoreResultModel" %>
<jsp:include page="/jsp/general/HeaderSection.jsp" />
<jsp:include page="/jsp/general/NavigationSection.jsp" />
<section>
    <h2>DEBUG : Rencontres terminées</h2>
    <p>
        Liste des rencontres terminées avec leurs résultats (Juste pour debug).<br/> 
        <br/>
    </p>
    <div class="schedules">
        <%
        List<ScheduleModel> scheds = (List<ScheduleModel>) request.getAttribute("schedules");
        for (ScheduleModel sched : scheds) {
        %>
        <div class="sched">
            <h3>
                <%= sched.getSched_home_team().getTeam_name() %> 
                <span class="orange">VS</span> 
                <%= sched.getSched_away_team().getTeam_name() %>
            </h3>
            <p>
                <b>Date : </b><%= sched.getSched_date() %><br/><br/>
            </p>
            <p>
                <%
                List<ResultModel> results = sched.getSched_res();
                if(results != null) {
                	%>
                	<b><u>Résultats :</u></b><br/>
                	<%
	                for(ResultModel res : results){
	                	if(res instanceof ScoreResultModel) {
	                %>
	                       <b>Scores :</b> 
	                       <%= ((ScoreResultModel)res).getScore_res_score_home() %> 
	                       - 
	                       <%= ((ScoreResultModel)res).getScore_res_score_away() %>             
	                       <br/><br/>
	                <%
	                	} else {
	                		%>
	                	   <b>Résultat non géré :</b> <%= res.getClass().getSimpleName() %>
	                		<%
	                	}
	                }
                }
                %>
            </p>
        </div>
        <%
        }
        if(scheds.size()==0) {
        %>
            <div class="nosched">
               Aucune rencontre sportive terminée.<br/>
            </div>
        <%
        }
        %>
    </div>
</section>
<jsp:include page="/jsp/general/FooterSection.jsp" />