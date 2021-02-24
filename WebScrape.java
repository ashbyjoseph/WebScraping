import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScrape {
	// jsoup jar was used to make a path to use Document and other tools to create
	// connections
	// josoup-1.13.1.jar

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//url of the webpage
		final String url = "https://web.archive.org/web/20190104110157/http://shares.telegraph.co.uk/indices/?index=MCX";   
		
		try {
			final Document document = Jsoup.connect(url).get();// creates a connection

			for (Element row : document.select("table.tablesorter.full tr")) {// tr gets you the row from the table
																				// which is table.tablesorter.full
				if (row.select("td:nth-of-type(1)").text().equals("")) { // helps you get the whole row of stock symbols
																			// from the webpage
					continue;
				} else {
					final String ticker = row.select("td:nth-of-type(1)").text(); // first part of the row
					// System.out.println(ticker);
					final String name = row.select("td:nth-of-type(2)").text(); // second part of the row
					// System.out.println(name);
					final String priceWithcomma = row.select(".right:nth-of-type(3)").text(); // prices of the stock
																								// from webpage
					final String priceWithoutComma = priceWithcomma.replace(",", "");
					// System.out.println(priceWithoutComma);
					final double price = Double.parseDouble(priceWithoutComma); // this converts price without comma to
																				// double so that you can do operations
																				// on price in the future if you want.

					System.out.println(
							"Stock " + ticker + ":" + name + ". Has a price of " + price + " dollars per share.\n");

				}
			}

			// System.out.println(document.outerHtml());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
