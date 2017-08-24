package autoicons;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

public class GoogleSearch extends Parameters {

	public static String getimage(String searchKeyword, int index) {
		GoogleSearch gsc = new GoogleSearch();

		List<Result> resultList = gsc.getSearchResult(searchKeyword);
		if (resultList != null && resultList.size() > 0) {
			for (Result result : resultList) {
				System.out.println("Downloading Image From Link - "+result.getLink());
				if(index == 1)
					return result.getLink();
				index = index-1;
			}
		}
		return null;
	}

	public List<Result> getSearchResult(String keyword) {
		// Set up the HTTP transport and JSON factory
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
		HttpTransport httpTransport;
		if(PROXY==1)
			httpTransport = new NetHttpTransport.Builder().setProxy(proxy).build();
		else
			httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		Customsearch customsearch = new Customsearch.Builder(httpTransport, jsonFactory, null).setApplicationName("AutoIcons Bot").build();
		//Customsearch customsearch = new Customsearch(httpTransport, jsonFactory, null);

		List<Result> resultList = null;
		try {
			Customsearch.Cse.List list = customsearch.cse().list(keyword);
			list.setKey(API_KEY);
			list.setCx(SEARCH_ENGINE_ID);
			list.setSearchType("image");
			list.setGl("IN");
			list.setNum(3l);
			list.setSafe("off");
			Search results = list.execute();
			resultList = results.getItems();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;

	}
}
