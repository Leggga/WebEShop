package eShop.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchForm {
	private String query;
	private List<Integer> categories;
	private List<Integer> producers;

	public SearchForm(String query, String[] categories, String[] producers) {
		super();
		
		if (query == null) {
			this.query = "";
		}else {
			this.query = query;			
		}
		this.categories = convertArrayToList(categories);
		this.producers = convertArrayToList(producers);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Integer> getCategories() {
		return categories;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	public List<Integer> getProducers() {
		return producers;
	}

	public void setProducers(List<Integer> producers) {
		this.producers = producers;
	}

	private List<Integer> convertArrayToList(String[] arr) {
		if (arr != null) {
			List<Integer> list = new ArrayList<Integer>();
			for (String item : arr) {
				list.add(Integer.parseInt(item));
			}
			return list;
		}

		return Collections.emptyList();
	}
	
}
