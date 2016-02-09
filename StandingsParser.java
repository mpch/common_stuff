package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StandingsParser {

	public static void main(String[] args) {
		try {
			List<StandingsItem> standingsList = new ArrayList<StandingsParser.StandingsItem>();
			// Document teamStatsDocument = Jsoup.connect("http://ws.acb.com/mobile?action=getMatchStats&match=155&team=CAN").get();
			Document standingsDocument = Jsoup.parse(new File("C:\\Users\\e388630\\Desktop\\standings.xml"), "UTF-8");
			long time = System.currentTimeMillis();
			for (Element postionRow : standingsDocument.getElementById("resultados").getElementsByTag("tr")) {
				Elements positionColumns = postionRow.getElementsByTag("td");
				if (!positionColumns.isEmpty()) {
					StandingsItem standingsItem = new StandingsItem();
					standingsItem.setPosition(positionColumns.get(0).text());
					standingsItem.setTeam(positionColumns.get(1).text());
					standingsItem.setGames(positionColumns.get(2).text());
					standingsItem.setWins(positionColumns.get(3).text());
					standingsItem.setLosses(positionColumns.get(4).text());
					standingsItem.setPointsScored(positionColumns.get(5).text());
					standingsItem.setPointsConceded(positionColumns.get(6).text());
					standingsList.add(standingsItem);
				}
			}
			System.out.println(System.currentTimeMillis() - time);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class StandingsItem {

		private String position;

		private String team;

		private String games;

		private String wins;

		private String losses;

		private String pointsScored;

		private String pointsConceded;

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getTeam() {
			return team;
		}

		public void setTeam(String team) {
			this.team = team;
		}

		public String getGames() {
			return games;
		}

		public void setGames(String games) {
			this.games = games;
		}

		public String getWins() {
			return wins;
		}

		public void setWins(String wins) {
			this.wins = wins;
		}

		public String getLosses() {
			return losses;
		}

		public void setLosses(String losses) {
			this.losses = losses;
		}

		public String getPointsScored() {
			return pointsScored;
		}

		public void setPointsScored(String pointsScored) {
			this.pointsScored = pointsScored;
		}

		public String getPointsConceded() {
			return pointsConceded;
		}

		public void setPointsConceded(String pointsConceded) {
			this.pointsConceded = pointsConceded;
		}

	}
}
