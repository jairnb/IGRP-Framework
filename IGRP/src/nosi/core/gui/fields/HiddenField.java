package nosi.core.gui.fields;

public class HiddenField extends AbstractField {

	public HiddenField(String name) {
		super();
		this.propertie.put("type","hidden");
		this.setTagName(name);
		this.propertie.put("name","p_"+name);
		this.propertie.put("tag", "hidden_1");
		this.propertie.put("maxlength", 30);
	}

}
