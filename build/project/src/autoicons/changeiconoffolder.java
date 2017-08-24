package autoicons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class changeiconoffolder extends Parameters {
	public static boolean changeicon(String folderpath) throws IOException, InterruptedException {
		File f = new File(folderpath + "\\desktop.ini");
		if (f.exists()) {
			Runtime.getRuntime().exec("attrib -r -h -s \"" + folderpath + "\\desktop.ini\"");
			f.delete();
		}
		f.createNewFile();
		BufferedWriter buf = new BufferedWriter(new FileWriter(f));
		buf.write("[.ShellClassInfo]");
		buf.newLine();
		buf.write("IconResource="+ICON_FILE+",0");
		buf.newLine();
		buf.write("[ViewState]");
		buf.newLine();
		buf.write("Mode=");
		buf.newLine();
		buf.write("Vid=");
		buf.newLine();
		buf.write("FolderType=Pictures");
		buf.newLine();
		buf.close();

		Process pr = Runtime.getRuntime().exec("attrib +r +h +s \"" + folderpath + "\\desktop.ini\"");
		pr.waitFor();
		//System.out.println("attrib +r +h +s \"" + folderpath + "\\desktop.ini\"");
		pr = Runtime.getRuntime().exec("attrib +h \"" + folderpath +"\\"+ ICON_FILE + "\"");
		pr.waitFor();
		//System.out.println("attrib +h \"" + folderpath + ICON_FILE);
		pr = Runtime.getRuntime().exec("attrib +r +s \"" + folderpath + "\"");
		pr.waitFor();
		//System.out.println("attrib +r \"" + folderpath + "\"");

		return true;
	}
	
	public static boolean listfolder(String folderpath, LinkedList<String> folderlist, int depth ){
		File []files = new File(folderpath).listFiles();
		for(File f:files){
			if(f.isDirectory()&&(!f.isHidden())){
				//System.out.println(f.getAbsolutePath());
				if(depth>0){
					folderlist.add(f.getAbsolutePath());
					listfolder(f.getAbsolutePath(),folderlist,depth-1);}
			}
		}
			
		return true;
	}
}
