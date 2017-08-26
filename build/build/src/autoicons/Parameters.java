package autoicons;

import java.io.File;

public class Parameters {
	// API KEY - AIzaSyBj9YT6qBrfpbeYIEAkNwdszkScviDFLzo ||
	// AIzaSyBYyreqbcWnhqyKjoyHkdbEeyzPTQ4pKj0 ||
	// AIzaSyBADgBIj2_PedzPeZFXMi3i5hIIxPFyC80 ||
	// AIzaSyD1L10h5glzRT00BOer9cBxekrtc9SX-KY
	public static String API_KEY = "";
	// SEARCH ENGINE KEY - 005855697128495569639:m35qcfvf1se ||
	// 001250895446244880298:_n7-of9-lq8 ||
	// 014469433644943503174:-xt14k1iqge ||
	// 000357411643573644417:nderoqdu0o0
	public static String SEARCH_ENGINE_ID = "";
	public static String TOP_FOLDER = "D:\\Your\\Folder\\Path";
	public static int DEPTH = 0;
	public static int MUSICMOVIE = 1;
	public static int RETRYFAILLIST = 0;

	public static String PROXY_HOST = "10.10.78.22";
	public static int PROXY_PORT = 3128;
	public static int PROXY = 0;

	public static String FULL_PATH = "C:\\Program Files\\AutoIcons\\app\\resources";
	public static String MAGICK_PATH = FULL_PATH + "\\ImageMagick-6.9.1-9-portable-Q16-x64\\";
	public static String CONFIG_FILE = FULL_PATH + "\\config.txt";

	public static String DATA_PATH = "C:\\Users\\Akhil Kedia\\AppData\\Roaming\\AutoIcons\\";
	public static String CONFIG_PROPERTIES_FILE = DATA_PATH + "\\config.properties";
	public static String DOWNLOAD_PATH = DATA_PATH + "\\Downloads\\";
	public static String FOLDER_LIST = DATA_PATH + "\\Folderlist.txt";
	public static String FAIL_LIST = DATA_PATH + "\\Faillist.txt";

	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
	public static String DOWNLOADED_IMAGE = "DonwloadedImage.png";
	public static String ICON_FILE = "Icon.ico";
	public static String CONVERT_COMMAND = " -gravity center -resize 256x256 -extent 256x256 -transparent white -colors 16777216 icon256.png && composite -gravity SouthEast foldericon.png icon256.png icon256.png && convert icon256.png -resize 16x16 icon16.png && convert icon256.png -resize 32x32 icon32.png && convert icon256.png -resize 64x64 icon64.png && convert icon256.png -resize 128x128 icon128.png && convert icon256.png icon32.png icon64.png icon128.png icon256.png -colors 16777216 ";
	public static int TIMEOUT = 1000 * 10 * 1;

	public static void recalculatePaths() {
		CONFIG_FILE = FULL_PATH + "\\config.txt";
		MAGICK_PATH = FULL_PATH + "\\ImageMagick-6.9.1-9-portable-Q16-x64\\";

		DATA_PATH = System.getenv("APPDATA") + "\\AutoIcons\\";
		File directory = new File(DATA_PATH);
		if (!directory.exists())
			directory.mkdir();
		DOWNLOAD_PATH = DATA_PATH + "\\Downloads\\";
		directory = new File(DOWNLOAD_PATH);
		if (!directory.exists())
			directory.mkdir();
		FOLDER_LIST = DATA_PATH + "\\Folderlist.txt";
		FAIL_LIST = DATA_PATH + "\\Faillist.txt";
	}

}
