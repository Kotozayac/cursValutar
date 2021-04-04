import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class cursValutar {

    public static Document getPage(String url) throws IOException {
        return Jsoup.parse(new URL(url), 6000);
    }

    public static void main(String[] args) throws IOException {
        Document page = getPage("https://www.curs.md/ru/curs_valutar/oficial");
        String date = page.select("input[type=date]").text();
        System.out.println("Курс валют на " + date);
        Element table = page.select("table[id=tabelValute]").first();
        Elements syms = table.select("tbody > tr");
        System.out.println("Символ   Наименование   Курс   Изменение   Изменение%");
        for (Element sym : syms) {
            String s = sym.select("td[class=cod] > span").text();
            String name = sym.select("td > a > strong").text();
            String curs = sym.select("td[class=rightalign]").get(0).text();
            String diff = sym.select("td[class=basic up]").text();
            String diffPercent = sym.select("td[class=rightalign]").get(1).text();
            if (diff.isEmpty()) {
                String d = sym.select("td[class=basic down]").text();
                System.out.println(s + "   " + name + "   " + curs + "   " + d + "   " + diffPercent);
            } else {
                System.out.println(s + "   " + name + "   " + curs + "   " + diff + "   " + diffPercent);
            }
        }
    }
}
