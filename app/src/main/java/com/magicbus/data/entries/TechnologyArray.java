package com.magicbus.data.entries;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TechnologyArray {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Technology> technologies = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */



    static {
        // Add some sample items.



        //MVP
        //
        //BUtterknife
        //
        //Retrofit
        //
        //Rxjava
        //
        //Awesome Validations
        //
        //Paypal SDK
        //
        //Room Db
        //
        //Facebook SSO
        //
        //Google Calender API
        //
        //MAp API
        //
        //Gmail Intent
        //
        //


        //UI elements - Recycler view, card view, spinner, splash screen, Date picker

        technologies.add(new Technology("1", "MVP"));
        technologies.add(new Technology("2", "Butterknife"));
        technologies.add(new Technology("3", "Retrofit"));
        technologies.add(new Technology("4", "Rxjava"));
        technologies.add(new Technology("5", "Awesome Validations"));
        technologies.add(new Technology("6", "Drawer Layout"));
        technologies.add(new Technology("7", "Room Db"));
        technologies.add(new Technology("8", "Facebook SSO"));
        technologies.add(new Technology("9", "Google Calender API"));
        technologies.add(new Technology("10", "Map API"));
        technologies.add(new Technology("11", "Gmail Intent"));

        technologies.add(new Technology("12", "UI elements - Recycler view, card view, spinner, splash screen, Date picker"));

    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class Technology {
        public final String id;
        public final String content;











        public Technology(String id, String content) {
            this.id = id;
            this.content = content;

        }

        @Override
        public String toString() {
            return content;
        }
    }
}
