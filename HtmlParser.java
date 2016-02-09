package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	public static void main(String[] args) {
		try {
			List<PlayerStatsItem> playerStatsList = new ArrayList<HtmlParser.PlayerStatsItem>();
			// Document teamStatsDocument = Jsoup.connect("http://ws.acb.com/mobile?action=getMatchStats&match=155&team=CAN").get();
			Document teamStatsDocument = Jsoup.parse(new File("C:\\Users\\e388630\\Desktop\\stats.xml"), "UTF-8");
			long time =System.currentTimeMillis();
			for (Element playersTable : teamStatsDocument.select("table.cols-fijas")) {
				for (Element playerRow : playersTable.getElementsByTag("tr")) {
					Elements playerColumns = playerRow.getElementsByTag("td");
					if (!playerColumns.isEmpty()) {
						PlayerStatsItem playerStatsItem = new PlayerStatsItem();
						playerStatsItem.setPlaying("*".equals(playerColumns.get(0).text()));
						playerStatsItem.setNumber(playerColumns.get(1).text());
						playerStatsItem.setName(playerColumns.get(2).text());
						playerStatsList.add(playerStatsItem);
					}
				}
			}
			Elements statsTables = teamStatsDocument.select("div.cols-scroll table");
			for (Element statsTable : statsTables) {
				int rowNumber = 0;
				for (Element statsRow : statsTable.getElementsByTag("tr")) {
					Elements statsColumns = statsRow.getElementsByTag("td");
					if (!statsColumns.isEmpty()) {
						playerStatsList.get(rowNumber).setTime(statsColumns.get(0).text());
						playerStatsList.get(rowNumber).setPoints(statsColumns.get(1).text());
						playerStatsList.get(rowNumber).setRebounds(statsColumns.get(8).text());
						playerStatsList.get(rowNumber).setAssists(statsColumns.get(10).text());
						playerStatsList.get(rowNumber).setBlocks(statsColumns.get(13).text());
						playerStatsList.get(rowNumber).setFoulsCommitted(statsColumns.get(16).text());
						playerStatsList.get(rowNumber).setValorization(statsColumns.get(18).text());
						rowNumber++;
					}
				}
			}
			System.out.println(System.currentTimeMillis() - time);
			// for (PlayerStatsItem playerStatsItem : playerStatsList) {
			// System.out.println(playerStatsItem.toString());
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class PlayerStatsItem {
		String name;

		String number;

		boolean playing;

		String time;

		String points;

		String rebounds;

		String assists;

		String foulsCommitted;

		String blocks;

		String valorization;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public boolean isPlaying() {
			return playing;
		}

		public void setPlaying(boolean playing) {
			this.playing = playing;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getPoints() {
			return points;
		}

		public void setPoints(String points) {
			this.points = points;
		}

		public String getRebounds() {
			return rebounds;
		}

		public void setRebounds(String rebounds) {
			this.rebounds = rebounds;
		}

		public String getAssists() {
			return assists;
		}

		public void setAssists(String assists) {
			this.assists = assists;
		}

		public String getFoulsCommitted() {
			return foulsCommitted;
		}

		public void setFoulsCommitted(String foulsCommitted) {
			this.foulsCommitted = foulsCommitted;
		}

		public String getBlocks() {
			return blocks;
		}

		public void setBlocks(String blocks) {
			this.blocks = blocks;
		}

		public String getValorization() {
			return valorization;
		}

		public void setValorization(String valorization) {
			this.valorization = valorization;
		}

		public int compareTo(PlayerStatsItem other) {
			int returnValue = 0;
			if (playing && !other.isPlaying()) {
				returnValue = -1;
			} else if (!playing && other.isPlaying()) {
				returnValue = 1;
			} else {
				returnValue = name.compareTo(other.getName());
			}
			return returnValue;
		}
	}

}
