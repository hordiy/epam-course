package ua.nure.hordiienko.practice7.constants;

/**
 * 
 * @author V.Hordiienko
 * @version 1.0  June 16, 2019.
 * This class holds entities declared in XSD document.
 *
 */

public enum XML {
	
	// elements names
	KNIFES("knifes"), KNIFE("knife"), TYPE("knifeType"), HANDY("knifeHandy"), ORIGIN("knifeOrigin"),
		VISUAL("knifeVisual"), BLADE("knifeBlade"), HANDLE("knifeHandle"), COLLECTION("collection"),
			WOOD("wood"), LEATHERCOATED("leatherCoated"), PLASTIC("plastic"),
	
	// attribute name
	NAME("name"), LENGTH("length"), WIDTH("width"), METAL("metal"), WOODTYPE("type");
		
		private String value;
		
		XML(String value) {
			this.value = value;
	}
		/**
		 * Determines if a name is equal to the string value wrapped by this enum element.<br/>
		 * If a SAX/StAX parser make all names of elements and attributes interned you can use
		 * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
		 * @param name string to compare with value. 
		 * @return value.equals(name)
		 */
		public boolean equalsTo(String name) {
			return value.equals(name);
		}

		public String value() {
			return value;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(this);
			return sb.toString();
		}
}
