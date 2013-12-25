package upmc.aar2013.project.heraclessport.server.tools;

import upmc.aar2013.project.heraclessport.server.datamodel.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.ScoreResultModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.TeamModel;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.googlecode.objectify.Key;

public class APIRequest {
	private static APIRequest instance= new APIRequest();
	
	public APIRequest() {}
	
	public static APIRequest getInstance(){
		return instance;
	}

	private long lastRequestTime = 0;
	private final long timeBetweenRequest = 1500; // un peu plus de 1 seconde, on sait jamais
	
	private final char access_level = 't';
	private final int season = 2013;
	private String season2 = "reg"; // pre, reg ou pst ???
		
	public String getSeason2() {
		return season2;
	}

	public void setSeason2(String season2) {
		if (season2.equals("pre") || season2.equals("reg") || season2.equals("pst"))
			this.season2 = season2;
	}

	public boolean updateLeagueHierarchyRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/league/hierarchy.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}

		Element element = send(request);
		try {
			Node node = null;
			NodeList teamNodes = element.getElementsByTagName("team");
			for (int i=0;i<teamNodes.getLength();i++) {
				TeamModel team = new TeamModel();
				
				NamedNodeMap nodeMapTeamAttributes = teamNodes.item(i).getAttributes();
				node = nodeMapTeamAttributes.getNamedItem("id");
				team.setTeam_id(node.getNodeValue());
				node = nodeMapTeamAttributes.getNamedItem("name");
				team.setTeam_name(node.getNodeValue());
				
				NodeList nodeInTeam  = teamNodes.item(i).getChildNodes();
				for (int j=0;j<nodeInTeam.getLength();j++) {
					if (nodeInTeam.item(j).getNodeName().equals("venue")) {
						NamedNodeMap nodeMapVenueAttributes = nodeInTeam.item(j).getAttributes();
						node = nodeMapVenueAttributes.getNamedItem("city");
						team.setTeam_town(node.getNodeValue());
						node = nodeMapVenueAttributes.getNamedItem("country");
						team.setTeam_country(node.getNodeValue());
					}
				}
				
				DataStore.storeTeam(team);
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getLeagueHierarchyRequest()");
			return false;
		}
		return true;
	}

	// pas sur cette requête soit utile, mais si l'on veut plus de renseignements sur l'équipe, on peut les avoir.
	public boolean updateTeamProfileRequest(Sport sport, String teamID) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/teams/" + teamID + "/profile.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			TeamModel team = DataStore.getTeam(teamID);
			// si besoin de choses, les récupérer ici
			DataStore.storeTeam(team);
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getTeamProfileRequest()");
			return false;
		}
		return true;
	}
	
	public boolean updateScheduleRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + season + "/" + season2 + "/schedule.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			Node node = null;
			NodeList gameNodes = element.getElementsByTagName("game");
			for (int i=0;i<gameNodes.getLength();i++) {
				ScheduleModel schedule = new ScheduleModel();
				
				NamedNodeMap nodeMapGameAttributes = gameNodes.item(i).getAttributes();
				node = nodeMapGameAttributes.getNamedItem("id");
				schedule.setSched_id(node.getNodeValue());
				node = nodeMapGameAttributes.getNamedItem("home_team");
				//schedule.setSched_home_team_id(Key.create(TeamModel.class, node.getNodeValue()));
				node = nodeMapGameAttributes.getNamedItem("away_team");
				//schedule.setSched_away_team_id(Key.create(TeamModel.class, node.getNodeValue()));
				// status : closed, inprogress, scheduled, postponed
				node = nodeMapGameAttributes.getNamedItem("status");
				//schedule.setSched_isStart(node.getNodeValue().equals("inprogress"));
				schedule.setSched_isFinish(node.getNodeValue().equals("closed"));
				node = nodeMapGameAttributes.getNamedItem("scheduled");
				schedule.setSched_date(this.toJavaDate(node.getNodeValue()));

				DataStore.storeSchedule(schedule);
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getScheduleRequest()");
			return false;
		}
		return true;
	}
	
	public boolean updateDailyScheduleRequest(Sport sport) {
		String monthS = "", dayS = "";
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		
		int monthI = calendar.get(Calendar.MONTH);
		monthI++; // commence à zero
		if (monthI<10) monthS+="0";
		monthS+=monthI;
		
		int dayI = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayI<10) dayS+="0";
		dayS+=dayI;

		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + year + "/" + monthS + "/" + dayS + "/schedule.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			Node node = null;
			NodeList gameNodes = element.getElementsByTagName("game");
			for (int i=0;i<gameNodes.getLength();i++) {
				ScheduleModel schedule = new ScheduleModel();
				
				NamedNodeMap nodeMapGameAttributes = gameNodes.item(i).getAttributes();
				node = nodeMapGameAttributes.getNamedItem("id");
				schedule.setSched_id(node.getNodeValue());
				node = nodeMapGameAttributes.getNamedItem("home_team");
				//schedule.setSched_home_team_id(Key.create(TeamModel.class, node.getNodeValue()));
				node = nodeMapGameAttributes.getNamedItem("away_team");
				//schedule.setSched_away_team_id(Key.create(TeamModel.class, node.getNodeValue()));
				// status : closed, inprogress, scheduled, postponed
				node = nodeMapGameAttributes.getNamedItem("status");
				//schedule.setSched_isStart(node.getNodeValue().equals("inprogress"));
				schedule.setSched_isFinish(node.getNodeValue().equals("closed"));
				//node = nodeMapGameAttributes.getNamedItem("scheduled");
				//schedule.setSched_date(this.toJavaDate(node.getNodeValue()));

				DataStore.storeSchedule(schedule);
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getScheduleRequest()");
			return false;
		}
		return true;
	}
	
	public boolean updateGameBoxscore(Sport sport, String scheduleID) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + scheduleID + "/boxscore.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			//ScheduleModel schedule = DataStore.getSchedule(scheduleID);
			ScoreResultModel result = new ScoreResultModel();	

			Node node = null;
			NamedNodeMap nodeMapGameAttributes = element.getAttributes();
			node = nodeMapGameAttributes.getNamedItem("id");
			//result.setRes_sched(Key.create(ScheduleModel.class, node.getNodeValue()));

			NodeList teamNodes = element.getElementsByTagName("team");
			for (int j=0;j<teamNodes.getLength();j++) {
				NamedNodeMap nodeMapTeamAttributes = teamNodes.item(j).getAttributes();
				//node = nodeMapTeamAttributes.getNamedItem("id");
				node = nodeMapTeamAttributes.getNamedItem("points");
				if (j==0) {
					result.setScore_res_score_home(Integer.parseInt(node.getNodeValue()));
				} else {
					result.setScore_res_score_away(Integer.parseInt(node.getNodeValue()));
				}
			}
			
			DataStore.storeResult(result);
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getGameBoxscore()");
			return false;
		}
		return true;
	}

	private synchronized Element send(String request) {
		System.out.println(request);
		if (request!=null ) {
			long current = System.currentTimeMillis();
			long past = current - this.lastRequestTime;
			if (past < this.timeBetweenRequest) {
				try {
					Thread.sleep(this.timeBetweenRequest - past);
				} catch (InterruptedException e) {
					System.out.println("# erreur : Thread.sleep()");
					return null;
				}
			}
			this.lastRequestTime = current;
			
			InputStream is = null;
			Document document = null;
			try {
				URL address = new URL(request);
				is = address.openStream();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				document = builder.parse(is);
			} catch (Exception e) {
				System.out.println("# erreur : dans new URL(), openStream() ou parse()");
				return null;
			} finally {
	            try {
	                is.close();
	            } catch (Exception e) {
	            	System.out.println("# erreur : is.close()");
	            	return null;
	            }
			}
			return document.getDocumentElement();
		}
		return null;
	}
	
	private Date toJavaDate(String date) throws ParseException {
	    //Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    //calendar.setTime(sdf.parse(date));
	    //sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		return sdf.parse(date);
	    /*
	    DateFormat indf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    indf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
	    Date purchaseDate = indf.parse(date);
	    
	    DateFormat outdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    outdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	    //csvfile.println(outdf.format(purchaseDate) +" GMT");
	    */
	}
}
