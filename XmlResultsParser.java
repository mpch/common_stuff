/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws XmlPullParserException the xml pull parser exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws XmlPullParserException, IOException {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			ResultHandler resultHandler = new ResultHandler();
			saxParser.parse(new FileInputStream("C:\\Users\\xxxxxx\\Desktop\\results.xml"), resultHandler);
			System.out.println(resultHandler.getResults());
		} catch (SAXNotRecognizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * The Class MatchItem.
	 */
	public static class ResultItem {

		/** The id. */
		private int id;

		/** The match date. */
		private String matchDate;

		/** The current time. */
		private String liveClock;

		/** The local team. */
		private String localTeam;

		/** The local points. */
		private int localPoints;

		/** The away team. */
		private String awayTeam;

		/** The away points. */
		private int awayPoints;

		/** The detail url. */
		private String detailURL;

		/** The status. */
		private String status;

		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * Sets the id.
		 *
		 * @param id the new id
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Gets the match date.
		 *
		 * @return the match date
		 */
		public String getMatchDate() {
			return matchDate;
		}

		/**
		 * Sets the match date.
		 *
		 * @param matchDate the new match date
		 */
		public void setMatchDate(String matchDate) {
			this.matchDate = matchDate;
		}

		/**
		 * Gets the live clock.
		 *
		 * @return the live clock
		 */
		public String getLiveClock() {
			return liveClock;
		}

		/**
		 * Sets the live clock.
		 *
		 * @param liveClock the new live clock
		 */
		public void setLiveClock(String liveClock) {
			this.liveClock = liveClock;
		}

		/**
		 * Gets the local team.
		 *
		 * @return the local team
		 */
		public String getLocalTeam() {
			return localTeam;
		}

		/**
		 * Sets the local team.
		 *
		 * @param localTeam the new local team
		 */
		public void setLocalTeam(String localTeam) {
			this.localTeam = localTeam;
		}

		/**
		 * Gets the local points.
		 *
		 * @return the local points
		 */
		public int getLocalPoints() {
			return localPoints;
		}

		/**
		 * Sets the local points.
		 *
		 * @param localPoints the new local points
		 */
		public void setLocalPoints(int localPoints) {
			this.localPoints = localPoints;
		}

		/**
		 * Gets the away team.
		 *
		 * @return the away team
		 */
		public String getAwayTeam() {
			return awayTeam;
		}

		/**
		 * Sets the away team.
		 *
		 * @param awayTeam the new away team
		 */
		public void setAwayTeam(String awayTeam) {
			this.awayTeam = awayTeam;
		}

		/**
		 * Gets the away points.
		 *
		 * @return the away points
		 */
		public int getAwayPoints() {
			return awayPoints;
		}

		/**
		 * Sets the away points.
		 *
		 * @param awayPoints the new away points
		 */
		public void setAwayPoints(int awayPoints) {
			this.awayPoints = awayPoints;
		}

		/**
		 * Gets the detail url.
		 *
		 * @return the detail url
		 */
		public String getDetailURL() {
			return detailURL;
		}

		/**
		 * Sets the detail url.
		 *
		 * @param detailURL the new detail url
		 */
		public void setDetailURL(String detailURL) {
			this.detailURL = detailURL;
		}

		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * Sets the status.
		 *
		 * @param status the new status
		 */
		public void setStatus(String status) {
			this.status = status;
		}

	}

	/**
	 * The Class MatchHandler.
	 */
	public static class ResultHandler extends DefaultHandler {

		private static final String ID = "id";

		/** The Constant PUB_DATE. */
		// names of the XML tags
		private static final String PUB_DATE = "pubDate";

		/** The Constant DESCRIPTION. */
		private static final String DESCRIPTION = "description";

		/** The Constant LINK. */
		private static final String LINK = "link";

		/** The Constant TITLE. */
		private static final String TITLE = "title";

		/** The Constant ITEM. */
		private static final String ITEM = "item";

		private static final String LOCAL_TEAM = "localTeam";

		private static final String VISITOR_TEAM = "visitorTeam";

		private static final String MATCH = "match";

		/** The match item. */
		private ResultItem resultItem;

		/** The results. */
		private List<ResultItem> results;

		private StringBuilder builder;

		private Stack<String> tagsStack = new Stack<String>();

		/**
		 * Gets the results.
		 *
		 * @return the results
		 */
		public List<ResultItem> getResults() {
			return results;
		}

		/**
		 * Start element.
		 *
		 * @param uri the uri
		 * @param localName the local name
		 * @param qName the q name
		 * @param attributes the attributes
		 * @throws SAXException the SAX exception
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if (qName.equalsIgnoreCase(ITEM)) {
				this.resultItem = new ResultItem();
				tagsStack.clear();
			}
			tagsStack.push(qName);
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			results = new ArrayList<ParametrieInfqtxuMgr.ResultItem>();
			builder = new StringBuilder();
			tagsStack = new Stack<String>();
		}

		/**
		 * Characters.
		 *
		 * @param ch the ch
		 * @param start the start
		 * @param length the length
		 * @throws SAXException the SAX exception
		 */
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			builder.append(ch, start, length);
		}

		/**
		 * End element.
		 *
		 * @param uri the uri
		 * @param localName the local name
		 * @param name the name
		 * @throws SAXException the SAX exception
		 */
		@Override
		public void endElement(String uri, String localName, String name) throws SAXException {
			super.endElement(uri, localName, name);
			String removedTag = tagsStack.pop();
			String parentTag = tagsStack.peek();
			tagsStack.push(removedTag);
			if (this.resultItem != null) {
				if (name.equalsIgnoreCase(ID) && parentTag.equalsIgnoreCase(MATCH)) {
					resultItem.setId(Integer.valueOf(builder.toString().trim()));
				} else if (name.equalsIgnoreCase(ID) && parentTag.equalsIgnoreCase(LOCAL_TEAM)) {
					resultItem.setLocalTeam(builder.toString());
				} else if (name.equalsIgnoreCase(ID) && parentTag.equalsIgnoreCase(VISITOR_TEAM)) {
					resultItem.setAwayTeam(builder.toString());
				} else if (name.equalsIgnoreCase(LINK)) {
					resultItem.setDetailURL(builder.toString());
				} else if (name.equalsIgnoreCase(DESCRIPTION)) {
					// resultItem.setDescription(builder.toString());
				} else if (name.equalsIgnoreCase(PUB_DATE)) {
					resultItem.setMatchDate(builder.toString());
				} else if (name.equalsIgnoreCase(ITEM)) {
					results.add(resultItem);
				}
				builder.setLength(0);
			}
		}

	}
