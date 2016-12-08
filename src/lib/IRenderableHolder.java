package lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IRenderableHolder {
	private static final IRenderableHolder instance = new IRenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public IRenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	public static IRenderableHolder getInstance() {
		return instance;
	}
	
	public void addAndSort(IRenderable entity) {
		add(entity);
		sort();
	}
	
	public void add(IRenderable entity) {
		entities.add(entity);
		sort();
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	
	
	public List<IRenderable> getEntities() {
		return entities;
	}
}
