package lib;

import java.util.Comparator;
import java.util.List;

public class IRenderableHolder {
	private static final IRenderableHolder instance = new IRenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public IRenderableHolder() {
		// TODO Auto-generated constructor stub
	}

	public static IRenderableHolder getInstance() {
		return instance;
	}
	
	public List<IRenderable> getEntities() {
		return entities;
	}
}
