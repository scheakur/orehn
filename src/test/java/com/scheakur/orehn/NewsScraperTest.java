package com.scheakur.orehn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NewsScraperTest {
    @Test
    public void testScrape() throws Exception {
        NewsScraper ns = new NewsScraper("http://example.com");
        Document doc = Jsoup.parse("<html>\n" +
                "  <head>\n" +
                "    <title>Dummy Hacker News</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <center>\n" +
                "      <table border=0 cellpadding=0 cellspacing=0 width=\"85%\" bgcolor=#f6f6ef>\n" +
                "        <tr>\n" +
                "          <td bgcolor=#ff6600>\n" +
                "            <table border=0 cellpadding=0 cellspacing=0 width=\"100%\" style=\"padding:2px\">\n" +
                "              <tr>\n" +
                "                <td style=\"width:18px;padding-right:4px\">\n" +
                "                  <a href=\"http://example.com\">\n" +
                "                    <img src=\"y18.gif\" width=18 height=18 style=\"border:1px #ffffff solid;\">\n" +
                "                    </img>\n" +
                "                  </a>\n" +
                "                </td>\n" +
                "                <td style=\"line-height:12pt; height:10px;\">\n" +
                "                  <span class=\"pagetop\">\n" +
                "                    <b>\n" +
                "                      <a href=\"news\">Dummy Hacker News</a>\n" +
                "                    </b>\n" +
                "                    <img src=\"s.gif\" height=1 width=10>\n" +
                "                    <a href=\"newest\">new</a> | <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> | <a href=\"jobs\">jobs</a> | <a href=\"submit\">submit</a>\n" +
                "                  </span>\n" +
                "                </td>\n" +
                "                <td style=\"text-align:right;padding-right:4px;\">\n" +
                "                  <span class=\"pagetop\">\n" +
                "                    <a href=\"newslogin?whence=%6e%65%77%73\">login</a>\n" +
                "                  </span>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr style=\"height:10px\">\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table border=0 cellpadding=0 cellspacing=0>\n" +
                "              <tr>\n" +
                "                <td align=right valign=top class=\"title\">1.</td>\n" +
                "                <td>\n" +
                "                  <center>\n" +
                "                    <a id=up_99999999997 href=\"vote?for=99999999997&amp;dir=up&amp;whence=%6e%65%77%73\">\n" +
                "                      <img src=\"grayarrow.gif\" border=0 vspace=3 hspace=2>\n" +
                "                    </a>\n" +
                "                    <span id=down_99999999997>\n" +
                "                    </span>\n" +
                "                  </center>\n" +
                "                </td>\n" +
                "                <td class=\"title\">\n" +
                "                  <a href=\"http://example.com/1\">Title 1</a>\n" +
                "                  <span class=\"comhead\"> (example.com) </span>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td colspan=2>\n" +
                "                </td>\n" +
                "                <td class=\"subtext\">\n" +
                "                  <span id=score_99999999997>57 points</span> by <a href=\"user?id=dummy1\">dummy 1</a> 1 hour ago  | <a href=\"item?id=99999999997\">35 comments</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr style=\"height:5px\">\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=right valign=top class=\"title\">2.</td>\n" +
                "                <td>\n" +
                "                  <center>\n" +
                "                    <a id=up_99999999998 href=\"vote?for=99999999998&amp;dir=up&amp;whence=%6e%65%77%73\">\n" +
                "                      <img src=\"grayarrow.gif\" border=0 vspace=3 hspace=2>\n" +
                "                    </a>\n" +
                "                    <span id=down_99999999998>\n" +
                "                    </span>\n" +
                "                  </center>\n" +
                "                </td>\n" +
                "                <td class=\"title\">\n" +
                "                  <a href=\"http://example.org/2\">Title 2</a>\n" +
                "                  <span class=\"comhead\"> (example.org) </span>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td colspan=2>\n" +
                "                </td>\n" +
                "                <td class=\"subtext\">\n" +
                "                  <span id=score_99999999998>153 points</span> by <a href=\"user?id=dummy2\">dummy 2</a> 4 hours ago  | <a href=\"item?id=99999999998\">59 comments</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr style=\"height:5px\">\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=right valign=top class=\"title\">3.</td>\n" +
                "                <td>\n" +
                "                  <center>\n" +
                "                    <a id=up_99999999999 href=\"vote?for=99999999999&amp;dir=up&amp;whence=%6e%65%77%73\">\n" +
                "                      <img src=\"grayarrow.gif\" border=0 vspace=3 hspace=2>\n" +
                "                    </a>\n" +
                "                    <span id=down_6226964>\n" +
                "                    </span>\n" +
                "                  </center>\n" +
                "                </td>\n" +
                "                <td class=\"title\">\n" +
                "                  <a href=\"http://example.net/3\">Title 3</a>\n" +
                "                  <span class=\"comhead\"> (example.net) </span>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td colspan=2>\n" +
                "                </td>\n" +
                "                <td class=\"subtext\">\n" +
                "                  <span id=score_99999999999>77 points</span> by <a href=\"user?id=dummy3\">dummy 3</a> 3 hours ago  | <a href=\"item?id=99999999999\">28 comments</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr style=\"height:5px\">\n" +
                "              </tr>\n" +
                "              <tr style=\"height:10px\">\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td colspan=2>\n" +
                "                </td>\n" +
                "                <td class=\"title\">\n" +
                "                  <a href=\"news2\">More</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <img src=\"s.gif\" height=10 width=0>\n" +
                "            <table width=\"100%\" cellspacing=0 cellpadding=1>\n" +
                "              <tr>\n" +
                "                <td bgcolor=#ff6600>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "            <br>\n" +
                "            <center>\n" +
                "              <span class=\"yclinks\">\n" +
                "                <a href=\"lists\">Lists</a> | <a href=\"rss\">RSS</a> | <a href=\"http://example.com/bookmarklet.html\">Bookmarklet</a> | <a href=\"http://example.com/newsguidelines.html\">Guidelines</a> | <a href=\"http://example.com/newsfaq.html\">FAQ</a> | <a href=\"dmca.html\">DMCA</a> | <a href=\"http://example.com/newsnews.html\">News News</a> | <a href=\"item?id=363\">Feature Requests</a> | <a href=\"https://example.com/issues\">Bugs</a> | <a href=\"http://example.com\">Dummy YC</a> | <a href=\"http://example.com/apply.html\">Apply</a> | <a href=\"http://example.com/lib.html\">Library</a>\n" +
                "              </span>\n" +
                "              <br>\n" +
                "              <br>\n" +
                "              <form method=get action=\"//example.com/search#request/all\">Search: <input type=text name=\"q\" value=\"\" size=17>\n" +
                "              </form>\n" +
                "              <br>\n" +
                "            </center>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </center>\n" +
                "  </body>\n" +
                "</html>\n");
        List<News> newsList = ns.scrape(doc);

        assertThat(newsList.size(), is(3));
        assertThat(newsList.get(0).title, is("Title 1"));
        assertThat(newsList.get(1).url, is("http://example.org/2"));
        assertThat(newsList.get(2).commentsNum, is(28));
    }
}
