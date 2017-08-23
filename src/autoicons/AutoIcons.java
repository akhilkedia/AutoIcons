package autoicons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class AutoIcons extends Parameters {

	public static void main(String[] args) throws IOException {

		if (args.length > 1)
			CONFIG_FILE = args[0];

		LinkedList<String> follist = new LinkedList<String>();
		readParams();
		if (RETRYFAILLIST == 0) {
			follist.add(TOP_FOLDER);
			changeiconoffolder.listfolder(TOP_FOLDER, follist, DEPTH);
		} else {
			BufferedReader buf = new BufferedReader(new FileReader(FAIL_LIST));
			String line = buf.readLine();
			while (line != null) {
				follist.add(line);
				line = buf.readLine();
			}
			buf.close();
			File failfile = new File(FAIL_LIST);
			failfile.delete();
		}
		changeIconList(follist);

	}

	public static void changeIconList(LinkedList<String> follist) throws IOException {

		ArrayList<String> cachelist = new ArrayList<String>();
		File infile = new File(FOLDER_LIST);
		if (!infile.exists())
			try {
				infile.createNewFile();
			} catch (IOException e2) {
				System.out.println("Cannot Create File - Please Retry");
				e2.printStackTrace();
			}

		BufferedReader inp = new BufferedReader(new FileReader(infile));
		String line = inp.readLine();
		while (line != null) {
			cachelist.add(line);
			line = inp.readLine();
		}
		inp.close();
		BufferedWriter buf;
		buf = new BufferedWriter(new FileWriter(FOLDER_LIST, true));

		for (String s : follist) {
			try {

				boolean success = false;
				int index = -1;
				index = cachelist.lastIndexOf(s);
				System.out.println("Currently Iconizing Folder - " + s);
				String iconpath = s+"\\"+ ICON_FILE;
				File foldericon = new File(iconpath);

				if (foldericon.exists()) {
					System.out.println("Already has an icon - Not replacing ");
					continue;
				}
				if (index == -1) {
					index = cachelist.size();
					if (MUSICMOVIE == 1) {
						String folderjpgpath = s + "\\folder.jpg";
						File folderjpg = new File(folderjpgpath);
						Path dst = new File(DOWNLOAD_PATH + index + DOWNLOADED_IMAGE).toPath();

						if (folderjpg.exists()) {
							Files.copy(folderjpg.toPath(), dst, StandardCopyOption.REPLACE_EXISTING);
							success = true;
						} else {
							String original = "Poster*.jpg";
							String regex = original.replace("?", ".?").replace("*", ".*?");
							final Pattern p = Pattern.compile(regex);
							File root = new File(s);
							File allfiles[] = root.listFiles(new FileFilter() {
								@Override
								public boolean accept(File file) {
									return p.matcher(file.getName()).matches();
								}
							});
							if (allfiles.length > 0) {
								try {
									File posterfile = allfiles[0];
									Files.copy(posterfile.toPath(), dst, StandardCopyOption.REPLACE_EXISTING);
									success = true;
								} catch (Exception E) {
									E.printStackTrace();
								}
							}
						}

					}
					if (success == false) {
						String searchstring = s.substring(s.lastIndexOf("\\", s.length() - 2) + 1);
						System.out.println("Google searching for - " + searchstring);
						String downloadlink = GoogleSearch.getimage(searchstring, 1);
						if (downloadlink != null) {
							try {
								saveImage(downloadlink, (DOWNLOAD_PATH + index + DOWNLOADED_IMAGE));
							} catch (IOException e) {
								downloadlink = GoogleSearch.getimage(searchstring, 2);
								try {
									saveImage(downloadlink, (DOWNLOAD_PATH + index + DOWNLOADED_IMAGE));
								} catch (IOException e1) {
									e1.printStackTrace();
									recordFail(s);
									continue;
								}
							}
						}
					}

					File imgfile = new File(DOWNLOAD_PATH + index + DOWNLOADED_IMAGE);
					if (imgfile.exists()) {
						success = true;
						cachelist.add(s);
						buf.write(s);
						buf.newLine();
						buf.flush();
					}
				}
				File imgfile = new File(DOWNLOAD_PATH + index + DOWNLOADED_IMAGE);
				if (imgfile.exists())
					success = true;

				if (success) {
					String delcommand = "cmd /C del /A:H \"" + s + "\\" + ICON_FILE + "\"";
					Process delpr = Runtime.getRuntime().exec(delcommand);
					try {
						delpr.waitFor();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					String command = "cmd /C cd \"" + MAGICK_PATH + "\" && convert \"" + DOWNLOAD_PATH + index + DOWNLOADED_IMAGE + "\" " + CONVERT_COMMAND
							+ " \"" + s + "\\" + ICON_FILE + "\"";
					try {
						Process pr = Runtime.getRuntime().exec(command);
						pr.waitFor();
						File iconfile = new File(s + "\\" + ICON_FILE);
						if (iconfile.exists()) {
							success = true;
							changeiconoffolder.changeicon(s);
						} else
							success = false;
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
						recordFail(s);
						continue;
					}
				}
				if (!success) {
					recordFail(s);
				}
			} catch (Exception E) {
				E.printStackTrace();
				recordFail(s);
				continue;
			}
		}

		buf.close();

	}

	public static void readParams() {
		try {
			BufferedReader buf = new BufferedReader(new FileReader(CONFIG_FILE));
			String line;
			for (int i = 0; i < 11; i++) {
				line = buf.readLine();
			}
			API_KEY = buf.readLine();
			SEARCH_ENGINE_ID = buf.readLine();
			PROXY_HOST = buf.readLine();
			PROXY_PORT = Integer.parseInt(buf.readLine());
			PROXY = Integer.parseInt(buf.readLine());
			FULL_PATH = buf.readLine();
			TOP_FOLDER = buf.readLine();
			DEPTH = Integer.parseInt(buf.readLine());
			MUSICMOVIE = Integer.parseInt(buf.readLine());
			RETRYFAILLIST = Integer.parseInt(buf.readLine());
			buf.close();

		} catch (IOException e) {
			System.out.println("Config File Not Found - Using Default in-built parameters");
		}
	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
		URLConnection conn;
		if (PROXY == 1)
			conn = url.openConnection(proxy);
		else
			conn = url.openConnection();
		conn.setRequestProperty("User-Agent", USER_AGENT);
		InputStream is = conn.getInputStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	public static void recordFail(String path) {
		BufferedWriter buf;
		try {
			buf = new BufferedWriter(new FileWriter(FAIL_LIST, true));
			buf.write(path);
			buf.newLine();
			buf.close();
		} catch (IOException e) {
			System.out.println("Cannot write to fail file");
			e.printStackTrace();
		}
	}

}
