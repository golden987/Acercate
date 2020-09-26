package co.com.acercate.ui.assignedDatesList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignedDatesListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<AssignedDateItem> ITEMS = new ArrayList<AssignedDateItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, AssignedDateItem> ITEM_MAP = new HashMap<String, AssignedDateItem>();

    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createAssignedDateItem(i));
        }
    }

    private static void addItem(AssignedDateItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static AssignedDateItem createAssignedDateItem(int position) {
        return new AssignedDateItem(String.valueOf("Cita " + position), "descripcion de cita " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Fecha de la cita: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nLorem ipsun.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class AssignedDateItem {
        public final String id;
        public final String content;
        public final String details;

        public AssignedDateItem(String id, String content, String details) {
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