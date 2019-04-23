package ga.awsapp.qrscanner.create.schemes;


public class Event extends QRCodeScheme {

    private static final String BEGIN_EVENT = "BEGIN:VEVENT";
    private static final String START = "DTSTART";
    private static final String END = "DTEND";
    private static final String SUMMARY = "SUMMARY";

    private String start;
    private String end;
    private String summary;

    public Event() {
        super();
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String generateString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BEGIN_EVENT).append(LINE_FEED);
        sb.append(SUMMARY).append(DEFAULT_KEY_VALUE_SEPARATOR).append(summary).append(LINE_FEED);
        sb.append(START).append(DEFAULT_KEY_VALUE_SEPARATOR).append(start).append(LINE_FEED);
        sb.append(END).append(DEFAULT_KEY_VALUE_SEPARATOR).append(end).append(LINE_FEED);
        sb.append("END:VEVENT");
        return sb.toString();
    }

    @Override
    public String toString() {
        return generateString();
    }

}
