package upmc.aar2013.project.heraclessport.server.tools;


public enum Sport {
	NBA ("nba", 3, "urgcrmzf67tv3p53uszcfu42"),
	BASKET ("ncaamb", 3, "8fmc4ndzdncnz4awnkc9t3us"),
	NFL ("nfl", 1, "txknqd8fy2aq8zyjuxeutvrc"),
	GOLF ("golf", 1, "3745dkvwpryuffhahtrhrz9z"),
	MLB ("mlb", 3, "vjq8pc2xbsxhxznguuqtutrw"),
	NASCAR ("nascar", 2, "asw325kygybkx867fa2ee2r8"),
	FOOT ("ncaafb", 1, "5re84cd45ufgm5848ymhqsgg"),
	NHL ("nhl", 3, "2gjxka73pq78f58vpnu85qn9");
   
	private String name = "";
	private int version = 1;
	private String key = "";
   
	Sport(String name, int version, String key){
		this.name = name;
		this.version = version;
		this.key = key;
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
}