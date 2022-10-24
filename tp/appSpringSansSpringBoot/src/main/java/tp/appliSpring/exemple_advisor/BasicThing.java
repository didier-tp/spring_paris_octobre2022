package tp.appliSpring.exemple_advisor;

public class BasicThing implements Thing {
	private String name;
	private String value;
	
	public BasicThing() {
		super();
	}

	public BasicThing(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getValue() {
		return value;
	}
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "BasicThing [name=" + name + ", value=" + value + "]";
	}
	
	
	
	
}
