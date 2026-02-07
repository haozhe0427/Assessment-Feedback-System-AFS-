package models;

public class assessment {
    private String id;
    private String subject;
    private String title;
    private int maxMarks;

    
    public assessment(String id, String subject, String title, int maxMarks) {
        this.id = id;
        this.subject = subject;
        this.title = title;
        this.maxMarks = maxMarks;
    }

    public String getId() { return id; }
    public String getSubject() { return subject; }
    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }

    // Helper to format data for writing to text file
    public String toFileString() {
        return id + "," + subject + "," + title + "," + maxMarks;
    }

    @Override
    public String toString() {
        return subject + " - " + title; // Useful for ComboBoxes
    }
}


