package ga.awsapp.qrscanner.create.schemes;

public class Email extends QRCodeScheme {
    public enum MailType {
        SIMPLE,
        COMPREHENSIVE
    }

    private MailType mailType;

    private static final String MAIL_TO = "mailto"; // For simple format

    private static final String MATMSG = "MATMSG";
    private static final String TO = "TO";
    private static final String SUB = "SUB";
    private static final String BODY = "BODY";
    private String to;
    private String sub;
    private String body;

    public Email(MailType type) {
        this.mailType = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        switch (mailType) {
            case SIMPLE:
                sb.append(MAIL_TO).append(DEFAULT_KEY_VALUE_SEPARATOR);
                if (to != null) {
                    sb.append(to);
                }
                break;
            case COMPREHENSIVE:
                sb.append(MATMSG).append(DEFAULT_KEY_VALUE_SEPARATOR).append(LINE_FEED).append(TO);
                if (to != null)
                    sb.append(to);
                sb.append(DEFAULT_LINE_END).append(LINE_FEED).append(SUB);
                if (sub != null)
                    sb.append(sub);
                sb.append(DEFAULT_LINE_END).append(LINE_FEED).append(BODY);
                if (body != null)
                    sb.append(body);
                sb.append(DEFAULT_LINE_END).append(DEFAULT_LINE_END);
        }

        return sb.toString();
    }

    public String getBody() {
        return body;
    }

    public MailType getMailType() {
        return mailType;
    }

    public String getTo() {
        return to;
    }

    public String getSub() {
        return sub;
    }

    public Email setTo(String to) {
        this.to = to;
        return this;
    }

    public Email setSub(String sub) {
        this.sub = sub;
        return this;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }

    public Email setMailType(MailType mailType) {
        this.mailType = mailType;
        return this;
    }
}
