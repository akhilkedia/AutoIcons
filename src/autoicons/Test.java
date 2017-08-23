package autoicons;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		String s = "H:\\Movies\\1980s\\7.9 My Left Foot- The Story of Christy Brown (1989)";
		String folderjpgpath = s + "\\folder.jpg";
		File folderjpg = new File(folderjpgpath);
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
	}

}
