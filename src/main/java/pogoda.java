import org.jsoup.nodes.Document;
import java.util.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pogoda extends cursValutar {
    public static void main(String[] args) throws IOException {
        ArrayList<Map<String, String>> arr = new ArrayList<>();
        Document page = getPage("https://www.gismeteo.com/weather-chisinau-4980/");
        Elements dateDiv = page.getElementsByClass("tab  tooltip");
        String date = dateDiv.select("div.tab-content > div").first().text();
        //convert abbreviated to full weekday name
        Map<String, String> weekNames = Map.of("Su", "Sunday", "Mo", "Monday", "Tu", "Tuesday", "We", "Wednesday", "Th", "Thursday", "Fr", "Friday", "Sa", "Saturday");
        String regex = "(\\D\\D),";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(date);
        if (m.find() && weekNames.containsKey(m.group(1))) {
            date = date.replace(m.group(1), weekNames.get(m.group(1)));
        } else {
            System.out.println("Full week day name not found.");
        }
        System.out.println("Today: " + date);
        Elements forecastFrame = page.select("div.forecast_frame.hw_wrap");
        Elements timeRows = forecastFrame.select("div.widget__row.widget__row_time > div.widget__item");
        for (Element timeRow : timeRows) {
            Map<String, String> timeArr = new LinkedHashMap<>();
            String hrs = timeRow.select("div.w_time > span").text();
            String mins = timeRow.select("div.w_time > sup").text();
            String time = hrs + ":" + mins;
            timeArr.put("Time", time);
            arr.add(timeArr);
        }

        Elements weatherIcons = forecastFrame.select("div.widget__row.widget__row_table.widget__row_icon >div.widget__item > div.widget__value.w_icon");
        int count = 0;
        for (Element weatherIcon : weatherIcons) {
            String weather = weatherIcon.select("span.tooltip").attr("data-text");
            arr.get(count).put("Weather", weather);
            count++;
        }
        count = 0;
        Elements tempValues = forecastFrame.select("div.chart.chart__temperature > div.values > div.value");
        for (Element tempValue : tempValues) {
            String temp = tempValue.select("span.unit.unit_temperature_c").text();
            arr.get(count).put("Degrees °C", temp);
            count++;
        }
        Elements windItems = forecastFrame.select("div.widget__row.widget__row_table.widget__row_wind-or-gust > div.widget__item > div.w_wind > div.w_wind__warning.w_wind__warning_ > span.unit.unit_wind_km_h ");
        count = 0;
        for (Element windItem : windItems) {
            String wind = windItem.text();
            arr.get(count).put("Wind (km/hr)", wind);
            count++;
        }
        Elements precipitationItems = forecastFrame.select("div.w_prec__value");
        count = 0;
        for (Element precipitationItem : precipitationItems) {
            String precipitation = precipitationItem.text();
            arr.get(count).put("Precipitation", precipitation);
            count++;
        }
        System.out.println(arr);
    }
}
