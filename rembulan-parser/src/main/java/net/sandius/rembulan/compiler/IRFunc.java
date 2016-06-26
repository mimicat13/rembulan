package net.sandius.rembulan.compiler;

import net.sandius.rembulan.compiler.ir.Var;
import net.sandius.rembulan.util.Check;

import java.util.List;
import java.util.Objects;

public class IRFunc {

	private final FunctionId id;
	private final List<Var> params;
	private final Blocks blocks;
	private final List<IRFunc> nested;

	public IRFunc(FunctionId id, List<Var> params, Blocks blocks, List<IRFunc> nested) {
		this.id = Check.notNull(id);
		this.params = Check.notNull(params);
		this.blocks = Check.notNull(blocks);
		this.nested = Check.notNull(nested);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		IRFunc that = (IRFunc) o;
		return id.equals(that.id)
				&& params.equals(that.params)
				&& blocks.equals(that.blocks)
				&& nested.equals(that.nested);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, params, blocks, nested);
	}

	public FunctionId id() {
		return id;
	}

	public List<Var> params() {
		return params;
	}

	public Blocks blocks() {
		return blocks;
	}

	public List<IRFunc> nested() {
		return nested;
	}

	public IRFunc update(Blocks blocks) {
		if (this.blocks.equals(blocks)) {
			return this;
		}
		else {
			return new IRFunc(id, params, blocks, nested);
		}
	}

}
