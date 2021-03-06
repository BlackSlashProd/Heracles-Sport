package upmc.aar2013.project.heraclessport.server.configs;

/**
 * Rêgles du jeu.
 *
 */
public class Configs {

	private static int CONFIG_RULES_POINT_START = 30;
	private static int CONFIG_RULES_DAILY_BONUS = 5;
	private static int CONFIG_RULES_PARIS_MULT_TEAM_VICT = 2;
	private static int CONFIG_RULES_PARIS_MULT_TEAM_SCOR_ONE = 3;
	private static int CONFIG_RULES_PARIS_MULT_TEAM_SCOR_BOTH = 5;
	
	public static int getStartPoint() {
		return CONFIG_RULES_POINT_START;
	}

	public static int getDailyPoint() {
		return CONFIG_RULES_DAILY_BONUS;
	}
	
	public static int getMultTeamVict() {
		return CONFIG_RULES_PARIS_MULT_TEAM_VICT;
	}

	public static int getMultTeamScorOne() {
		return CONFIG_RULES_PARIS_MULT_TEAM_SCOR_ONE;
	}
	
	public static int getMultTeamScorBoth() {
		return CONFIG_RULES_PARIS_MULT_TEAM_SCOR_BOTH;
	}
}
