package upmc.aar2013.project.heraclessport.server.tools;

public class APIRequest {
	private long lastRequestTime = 0;
	private final long timeBetweenRequest = 1010; // un peu plus de 1 seconde, on sait jamais
	
	private final char access_level = 't';
	private final int season = 2013;
	private String season2 = "reg"; // pre, reg ou pst ???
	
	public APIRequest() {}
	
	public String getSeason2() {
		return season2;
	}

	public void setSeason2(String season2) {
		if (season2.equals("pre") || season2.equals("reg") || season2.equals("pst"))
			this.season2 = season2;
	}

	public void getScheduleRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/games/" + season + "/" + season2 + "/schedule.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		send(request);
	}

	// pas sur que se soit très utile ça
	public void getTeamProfileRequest(Sport sport, String teamID) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/teams/" + teamID + "/profile.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		send(request);
	}
	
	public void getLeagueHierarchyRequest(Sport sport) {
		String request = null;
		switch(sport) {
			case NBA: case NHL:
				request = "http://api.sportsdatallc.org/" + sport.getName() + "-" + access_level + sport.getVersion() + "/league/" + "/hierarchy.xml?api_key=" + sport.getKey();
				break;
			// à compléter si besoin
		}
		send(request);
	}

	private void send(String request) {
		if (request!=null ) {
			long current = System.currentTimeMillis();
			if (current - this.lastRequestTime > this.timeBetweenRequest) {
				this.lastRequestTime = current;
				// envoie de la requete a faire !!!
				// mettre en retour le fichier reçu
			}
		}
	}
	// eventuelement completer avec les MAJ des schema (xsd) si necessaire, 
	// dans ce cas préciser : pour chaque sport, pour chaque requete : un champ en plus concernant la version du schema
}
