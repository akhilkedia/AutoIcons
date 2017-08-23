package autoicons;

public class Parameters {
	public static String API_KEY = "AIzaSyBj9YT6qBrfpbeYIEAkNwdszkScviDFLzo";
	public static String SEARCH_ENGINE_ID = "005855697128495569639:m35qcfvf1se";
	public static String PROXY_HOST = "10.10.78.22";
	public static int PROXY_PORT = 3128;
	public static int PROXY = 0;
	public static String FULL_PATH = "C:\\Software\\AutoIcons";
	public static String TOP_FOLDER = "D:\\";
	public static int DEPTH = 2;
	public static int MUSICMOVIE = 1;
	public static int RETRYFAILLIST = 0;
	
	public static String CONFIG_FILE = FULL_PATH+"\\config.txt";
	
	public static final String CONVERT_COMMAND = " -gravity center -resize 256x256 -extent 256x256 -transparent white -colors 16777216 icon256.png && composite -gravity SouthEast foldericon.png icon256.png icon256.png && convert icon256.png -resize 16x16 icon16.png && convert icon256.png -resize 32x32 icon32.png && convert icon256.png -resize 64x64 icon64.png && convert icon256.png -resize 128x128 icon128.png && convert icon256.png icon32.png icon64.png icon128.png icon256.png -colors 16777216 ";
	public static final String MAGICK_PATH = FULL_PATH + "\\ImageMagick-6.9.1-9-portable-Q16-x64\\";
	public static final String DOWNLOAD_PATH = FULL_PATH + "\\Downloads\\";
	public static final String FOLDER_LIST = FULL_PATH + "\\Folderlist.txt";
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
	public static final String DOWNLOADED_IMAGE = "DonwloadedImage.png";
	public static final String ICON_FILE = "Icon.ico";
	public static final String FAIL_LIST =  FULL_PATH + "\\Faillist.txt";
	

}
