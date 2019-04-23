package ga.awsapp.qrscanner.create.schemes;


public class GooglePlay extends QRCodeScheme {

	public static final String GPLAY = "market://details?id=%s";
	private String appPackage;


	public GooglePlay() {
		super();
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}


	public QRCodeScheme parseSchema(String code) {
		if (code == null || !code.trim().toLowerCase().startsWith("{{{market:")) {
			throw new IllegalArgumentException("this is not a google play code: " + code);
		}
		String[] paths = code.trim().toLowerCase().replace("}}}", "").split("=");
		if (paths != null && paths.length > 1) {
			setAppPackage(paths[1]);
		}
		return this;
	}


	public String generateString() {
		return String.format(GPLAY, (appPackage != null ? appPackage : ""));
	}

	@Override
	public String toString() {
		return generateString();
	}

	public static GooglePlay parse(final String code) {
		GooglePlay googlePlay = new GooglePlay();
		googlePlay.parseSchema(code);
		return googlePlay;
	}

}
