package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HtmlParser {

	public static void main(String[] args) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			PlayByPlayHandler playByPlayHandler = new PlayByPlayHandler();
			saxParser.parse(new FileInputStream("C:\\Users\\e388630\\Desktop\\moves.xml"), playByPlayHandler);
			System.out.println(playByPlayHandler.getPlays());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	static class PlayByPlayItem {
		String description;

		String score;

		String team;

		String period;

		String minute;

		public String getMinute() {
			return minute;
		}

		public void setMinute(String minute) {
			this.minute = minute;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getTeam() {
			return team;
		}

		public void setTeam(String team) {
			this.team = team;
		}

		public String getPeriod() {
			return period;
		}

		public void setPeriod(String period) {
			this.period = period;
		}

	}

	static class PlayByPlayHandler extends DefaultHandler {

		private static final String MIN = "min";

		private static final String TITLE = "title";

		private static final String ITEM = "item";

		private static final String PERIOD = "category";

		private static final String TEAM = "team";

		private static final String SCORE = "score";

		private PlayByPlayItem playByPlay;

		private StringBuilder stringBuilder;

		private List<PlayByPlayItem> plays;

		public List<PlayByPlayItem> getPlays() {
			return plays;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			plays = new ArrayList<PlayByPlayItem>();
			stringBuilder = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if (qName.equalsIgnoreCase(ITEM)) {
				this.playByPlay = new PlayByPlayItem();
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			stringBuilder.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
			if (playByPlay != null) {
				if (qName.equalsIgnoreCase(TITLE)) {
					playByPlay.setDescription(stringBuilder.toString());
				} else if (qName.equalsIgnoreCase(SCORE)) {
					playByPlay.setScore(stringBuilder.toString());
				} else if (qName.equalsIgnoreCase(TEAM)) {
					playByPlay.setTeam(stringBuilder.toString());
				} else if (qName.equalsIgnoreCase(PERIOD)) {
					playByPlay.setPeriod(stringBuilder.toString());
				} else if (qName.equalsIgnoreCase(MIN)) {
					playByPlay.setMinute(stringBuilder.toString());
				} else if (qName.equalsIgnoreCase(ITEM)) {
					plays.add(playByPlay);
				}
				stringBuilder.setLength(0);
			}
		}
	}
}
