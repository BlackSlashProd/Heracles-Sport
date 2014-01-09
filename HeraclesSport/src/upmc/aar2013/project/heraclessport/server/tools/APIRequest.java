package upmc.aar2013.project.heraclessport.server.tools;

import upmc.aar2013.project.heraclessport.server.configs.Sport;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.TeamModel;

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

public class APIRequest {
	private static APIRequest instance= new APIRequest();
	
	public APIRequest() {}
	
	public static APIRequest getInstance(){
		return instance;
	}

	private long lastRequestTime = 0;
	private final long timeBetweenRequest = 1600; // un peu plus de 1 seconde, on sait jamais
	
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
				
				Element element = send(request);
				try {
					Node node = null;
					NodeList teamNodes = element.getElementsByTagName("team");
					for (int i=0;i<teamNodes.getLength();i++) {
						//TeamModel team = new TeamModel();
						
						NamedNodeMap nodeMapTeamAttributes = teamNodes.item(i).getAttributes();
						node = nodeMapTeamAttributes.getNamedItem("id");
						String team_id = node.getNodeValue();
						//team.setTeam_id(node.getNodeValue());
						node = nodeMapTeamAttributes.getNamedItem("name");
						String team_name = node.getNodeValue();
						//team.setTeam_name(node.getNodeValue());
						String team_city = "";
						String team_country = "";
						NodeList nodeInTeam  = teamNodes.item(i).getChildNodes();
						for (int j=0;j<nodeInTeam.getLength();j++) {
							if (nodeInTeam.item(j).getNodeName().equals("venue")) {
								NamedNodeMap nodeMapVenueAttributes = nodeInTeam.item(j).getAttributes();
								node = nodeMapVenueAttributes.getNamedItem("city");
								team_city = node.getNodeValue();
								//team.setTeam_town(node.getNodeValue());
								node = nodeMapVenueAttributes.getNamedItem("country");
								team_country = node.getNodeValue();
								//team.setTeam_country(node.getNodeValue());
							}
						}
						TeamModel team = new TeamModel(team_id,team_name,team_city,team_country);
						DataStore.storeTeam(team);
					}
				} catch (Exception e) {
					System.out.println("@ erreur lors du parcours du fichier xml dans getLeagueHierarchyRequest()");
					return false;
				}
				break;
			// à compléter si besoin
			default:
				break;
		}
		return true;
	}

	public boolean updateScheduleRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + season + "/" + season2 + "/schedule.xml?api_key=" + sport.getKey();
				
				Element element = send(request);
				try {
					Node node = null;
					NodeList gameNodes = element.getElementsByTagName("game");
					for (int i=0;i<gameNodes.getLength();i++) {
						//ScheduleTeamModel schedule = new ScheduleTeamModel();
						NamedNodeMap nodeMapGameAttributes = gameNodes.item(i).getAttributes();
						node = nodeMapGameAttributes.getNamedItem("id");
						String sched_id = node.getNodeValue();
						node = nodeMapGameAttributes.getNamedItem("home_team");
						String sched_home_team_id = node.getNodeValue();
						node = nodeMapGameAttributes.getNamedItem("away_team");
						String sched_away_team_id = node.getNodeValue();
						node = nodeMapGameAttributes.getNamedItem("status");
						boolean isFinish = node.getNodeValue().equals("closed");
						node = nodeMapGameAttributes.getNamedItem("scheduled");
						Date sched_date = this.toJavaDate(node.getNodeValue());
						ScheduleTeamModel schedule = new ScheduleTeamModel(sport,sched_id,sched_date,isFinish,sched_home_team_id,sched_away_team_id);
						DataStore.storeSchedule(schedule);
						//schedule.setSched_id(node.getNodeValue());
						//schedule.setSched_home_team_id(node.getNodeValue());
						//schedule.setSched_away_team_id(node.getNodeValue());
						// status : closed, inprogress, scheduled, postponed
						//schedule.setSched_isFinish(node.getNodeValue().equals("closed"));
						//schedule.setSched_date(this.toJavaDate(node.getNodeValue()));

						
					}
				} catch (Exception e) {
					System.out.println("@ erreur lors du parcours du fichier xml dans getScheduleRequest()");
					return false;
				}
				break;
			// à compléter si besoin
			default:
				break;
		}
		return true;
	}
	
	public String updateDailyScheduleRequest(Sport sport, int year, String month, String day) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + year + "/" + month + "/" + day + "/schedule.xml?api_key=" + sport.getKey();
				
				Element element = send(request);
				try {
					String scheduleID = null, scheduleStatus = null;;
					Node node = null;
					NodeList gameNodes = element.getElementsByTagName("game");
					for (int i=0;i<gameNodes.getLength();i++) {				
						NamedNodeMap nodeMapGameAttributes = gameNodes.item(i).getAttributes();
						node = nodeMapGameAttributes.getNamedItem("id");
						scheduleID = node.getNodeValue();
						
						// status : closed, inprogress, scheduled, postponed
						node = nodeMapGameAttributes.getNamedItem("status");
						scheduleStatus = node.getNodeValue();
						ResultScoreModel resultScoreModel = DataStore.getScoreResultsBySchedule(scheduleID);
if (resultScoreModel==null)
	System.out.println("aucune resultat pour la rencontre : " + scheduleID);
						if ( scheduleStatus.equals("closed") && resultScoreModel==null) { // à verif si ca renvoie bien null s'il n'y a pas de resultat
							return scheduleID;
						}
					}
				} catch (Exception e) {
e.printStackTrace();
					System.out.println("@ erreur lors du parcours du fichier xml dans getScheduleRequest()");
					return null;
				}
				break;
			// à compléter si besoin
			default:
				break;
		}
		return null;
	}
	
	public boolean updateGameBoxscore(Sport sport, String scheduleID) {
		if (scheduleID==null) return false;
		
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + scheduleID + "/boxscore.xml?api_key=" + sport.getKey();
				
				Element element = send(request);
				int home_score = -1, away_score = -1;
				try {
					Node node = null;
					//NamedNodeMap nodeMapGameAttributes = element.getAttributes();
					//node = nodeMapGameAttributes.getNamedItem("id");

					NodeList teamNodes = element.getElementsByTagName("team");
					for (int j=0;j<teamNodes.getLength();j++) {
						NamedNodeMap nodeMapTeamAttributes = teamNodes.item(j).getAttributes();
						//node = nodeMapTeamAttributes.getNamedItem("id");
						node = nodeMapTeamAttributes.getNamedItem("points");
						if (j==0) {
							home_score = Integer.parseInt(node.getNodeValue());
						} else {
							away_score = Integer.parseInt(node.getNodeValue());
						}
					}
					
					ResultScoreModel result = new ResultScoreModel(scheduleID, home_score, away_score);	
					DataStore.storeResult(result);
					DataStore.updateSchedule(scheduleID, result);
				} catch (Exception e) {
					System.out.println("@ erreur lors du parcours du fichier xml dans getGameBoxscore()");
					return false;
				}
				break;
			// à compléter si besoin
			default:
				break;
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
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return sdf.parse(date);
	}
}
