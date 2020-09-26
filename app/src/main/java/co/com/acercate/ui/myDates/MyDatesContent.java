package co.com.acercate.ui.myDates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MyDatesContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MyDatesItem> ITEMS = new ArrayList<MyDatesItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MyDatesItem> ITEM_MAP = new HashMap<String, MyDatesItem>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createMyDatesItem(i));
        }
    }

    private static void addItem(MyDatesItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static MyDatesItem createMyDatesItem(int position) {
        return new MyDatesItem(String.valueOf(position), "Descripcion de cita " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MyDatesItem {
        public final String id;
        public final String content;
        public final String details;

        public MyDatesItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}