package net.visionezone.rajib.myapplication.parsers;
import net.visionezone.rajib.myapplication.model.Categories;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CategoriesXMLParser {
    public static List<Categories> parseFeed(String content) {

        try {

            boolean inDataItemTag = false;
            String currentTagName = "";
            Categories categories = null;
            List<Categories> categoriesList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("product")) {
                            inDataItemTag = true;
                            categories = new Categories();
                            categoriesList.add(categories);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("product")) {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        if (inDataItemTag && categories != null) {
                            switch (currentTagName) {
                                case "cat_id":
                                    categories.setCat_id(Integer.parseInt(parser.getText()));
                                    break;
                                case "parent_id":
                                    categories.setParent_id(Integer.parseInt(parser.getText()));
                                    break;
                                case "cat_name":
                                    categories.setCat_name(parser.getText());
                                    break;
                                case "cat_description":
                                    categories.setCat_description(parser.getText());
                                    break;
                                case "image_name":
                                    categories.setImage_name(parser.getText());
                                    break;
                                case "image_banner":
                                    categories.setImage_banner(parser.getText());
                                default:
                                    break;
                            }
                        }
                        break;
                }

                eventType = parser.next();

            }

            return categoriesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}