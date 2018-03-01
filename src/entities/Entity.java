package entities;

public class Entity {

	public String entityName;
	public Entity entityParent;

	public Entity(String entityName) {
		super();
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}

	public Entity getEntityParent() {
		return entityParent;
	}

}
