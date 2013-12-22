package upmc.aar2013.project.heraclessport.server.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

	public Element getLeagueHierarchyRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/league/hierarchy.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			// parcours temporaire
			NodeList TeamNodes = element.getElementsByTagName("team");
			for (int i=0;i<5/*TeamNodes.getLength()*/;i++) {
				NamedNodeMap nodeMapTeamAttributes = TeamNodes.item(i).getAttributes();
				Node node = nodeMapTeamAttributes.getNamedItem("id");
				System.out.println("id="+node.getNodeValue());
				node = nodeMapTeamAttributes.getNamedItem("name");
				System.out.println("name="+node.getNodeValue());
				
				NodeList nodeInTeam  = TeamNodes.item(i).getChildNodes();
				for (int j=0;j<nodeInTeam.getLength();j++) {
					if (nodeInTeam.item(j).getNodeName().equals("venue")) {
						NamedNodeMap nodeMapVenueAttributes = nodeInTeam.item(j).getAttributes();
						node = nodeMapVenueAttributes.getNamedItem("city");
						System.out.println("city="+node.getNodeValue());
						node = nodeMapVenueAttributes.getNamedItem("country");
						System.out.println("country="+node.getNodeValue());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getLeagueHierarchyRequest()");
		}
		return element;
	}

	// pas sur que se soit très utile ça
	public Element getTeamProfileRequest(Sport sport, String teamID) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/teams/" + teamID + "/profile.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			// parcours temporaire
			Node node = null;
			NamedNodeMap nodeMapTeamAttributes = element.getAttributes();
			node = nodeMapTeamAttributes.getNamedItem("id");
			System.out.println("id="+node.getNodeValue());
			node = nodeMapTeamAttributes.getNamedItem("name");
			System.out.println("name="+node.getNodeValue());

			NodeList venueNodes = element.getElementsByTagName("venue");
			for (int j=0;j<venueNodes.getLength();j++) {
				NamedNodeMap nodeMapVenueAttributes = venueNodes.item(j).getAttributes();
				node = nodeMapVenueAttributes.getNamedItem("city");
				System.out.println("city="+node.getNodeValue());
				node = nodeMapVenueAttributes.getNamedItem("country");
				System.out.println("country="+node.getNodeValue());
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getTeamProfileRequest()");
		}
		return element;
	}
	
	public Element getScheduleRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + season + "/" + season2 + "/schedule.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			// parcours temporaire
			NodeList TeamNodes = element.getElementsByTagName("game");
			for (int i=0;i<5/*TeamNodes.getLength()*/;i++) {
				NamedNodeMap nodeMapTeamAttributes = TeamNodes.item(i).getAttributes();
				Node node = nodeMapTeamAttributes.getNamedItem("id");
				System.out.println("id="+node.getNodeValue());
				node = nodeMapTeamAttributes.getNamedItem("status");
				System.out.println("status="+node.getNodeValue());
				node = nodeMapTeamAttributes.getNamedItem("home_team");
				System.out.println("home_team"+node.getNodeValue());
				node = nodeMapTeamAttributes.getNamedItem("away_team");
				System.out.println("away_team="+node.getNodeValue());
				node = nodeMapTeamAttributes.getNamedItem("scheduled");
				System.out.println("scheduled="+node.getNodeValue());
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getScheduleRequest()");
		}
		return element;
	}
	
	public Element getGameBoxscore(Sport sport, String teamID) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + teamID + "/boxscore.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		
		Element element = send(request);
		try {
			// parcours temporaire
			Node node = null;
			NamedNodeMap nodeMapTeamAttributes = element.getAttributes();
			node = nodeMapTeamAttributes.getNamedItem("id");
			System.out.println("id="+node.getNodeValue());
			node = nodeMapTeamAttributes.getNamedItem("status");
			System.out.println("status="+node.getNodeValue());
			node = nodeMapTeamAttributes.getNamedItem("home_team");
			System.out.println("home_team"+node.getNodeValue());
			node = nodeMapTeamAttributes.getNamedItem("away_team");
			System.out.println("away_team="+node.getNodeValue());
			node = nodeMapTeamAttributes.getNamedItem("scheduled");
			System.out.println("scheduled="+node.getNodeValue());
			
			NodeList venueNodes = element.getElementsByTagName("team");
			for (int j=0;j<venueNodes.getLength();j++) {
				NamedNodeMap nodeMapVenueAttributes = venueNodes.item(j).getAttributes();
				node = nodeMapVenueAttributes.getNamedItem("id");
				System.out.println("id="+node.getNodeValue());
				node = nodeMapVenueAttributes.getNamedItem("name");
				System.out.println("name="+node.getNodeValue());
				node = nodeMapVenueAttributes.getNamedItem("points");
				System.out.println("points="+node.getNodeValue());
			}
		} catch (Exception e) {
			System.out.println("@ erreur lors du parcours du fichier xml dans getGameBoxscore()");
		}
		return element;
	}
		
	private synchronized Element send(String request) {
System.out.println(request);
System.out.println("Thread:"+Thread.currentThread().getName());
System.out.println("Thread:"+Thread.currentThread().toString());
		if (request!=null ) {
			long current = System.currentTimeMillis();
			long past = current - this.lastRequestTime;
System.out.println("current:"+current);
System.out.println("past:"+past);
			if (past < this.timeBetweenRequest) {
System.out.println("to wait:"+ (this.timeBetweenRequest - past));
				try {
					Thread.sleep(this.timeBetweenRequest - past);
System.out.println("really wait:"+(System.currentTimeMillis()-current));
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
	            } catch (IOException e) {
	            	System.out.println("# erreur : is.close()");
	            	return null;
	            }
			}
			return document.getDocumentElement();
		}
		return null;
	}
}
