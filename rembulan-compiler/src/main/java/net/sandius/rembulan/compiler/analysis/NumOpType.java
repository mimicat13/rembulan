package net.sandius.rembulan.compiler.analysis;

import net.sandius.rembulan.compiler.analysis.types.LuaTypes;
import net.sandius.rembulan.compiler.analysis.types.Type;

public enum NumOpType {

	Integer,
	Float,
	Number,
	Any;

	public Type toSlotType() {
		switch (this) {
			case Integer:  return LuaTypes.NUMBER_INTEGER;
			case Float:    return LuaTypes.NUMBER_FLOAT;
			case Number:   return LuaTypes.NUMBER;
			case Any:
			default:       return LuaTypes.ANY;
		}
	}

	@Deprecated
	public String toSuffix() {
		switch (this) {
			case Integer: return "_i";
			case Float:   return "_f";
			case Number:  return "_N";
			case Any:
			default:      return "";
		}
	}

}