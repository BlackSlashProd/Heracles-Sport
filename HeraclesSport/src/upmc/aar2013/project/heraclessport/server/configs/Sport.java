package upmc.aar2013.project.heraclessport.server.configs;

import java.util.Arrays;
import java.util.List;


public enum Sport {
	NBA ("nba", 3, "urgcrmzf67tv3p53uszcfu42","BASKET"),
	BASKET ("ncaamb", 3, "8fmc4ndzdncnz4awnkc9t3us","BASKET"),
	NFL ("nfl", 1, "txknqd8fy2aq8zyjuxeutvrc","FOOT"),
	GOLF ("golf", 1, "3745dkvwpryuffhahtrhrz9z","GOLF"),
	MLB ("mlb", 3, "vjq8pc2xbsxhxznguuqtutrw","BASEBALL"),
	NASCAR ("nascar", 2, "asw325kygybkx867fa2ee2r8","NASCAR"),
	FOOT ("ncaafb", 1, "5re84cd45ufgm5848ymhqsgg","FOOT"),
	NHL ("nhl", 3, "2gjxka73pq78f58vpnu85qn9","HOCKEY");
   
	private String name = "";
	private int version = 1;
	private String key = "";
	private String cleanName = "";
   
	Sport(String name, int version, String key, String clean){
		this.name = name;
		this.version = version;
		this.key = key;
		this.cleanName = clean;
	}
	
	private static List<Sport> getAllSport() {
		return Arrays.asList(
				Sport.NBA,
				Sport.BASKET,
				Sport.NFL,
				Sport.GOLF,
				Sport.MLB,
				Sport.NASCAR,
				Sport.FOOT,
				Sport.NHL
			);
	}
	
	public static String getClean(String sport) {
		List<Sport> sports = getAllSport();
		for(Sport sp : sports) {
			if(sp.name.compareTo(sport)==0)
				return sp.getCleanName();
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public String getKey() {
		return this.key;
	}

	/**
	 * @return the cleanName
	 */
	public String getCleanName() {
		return cleanName;
	}
}