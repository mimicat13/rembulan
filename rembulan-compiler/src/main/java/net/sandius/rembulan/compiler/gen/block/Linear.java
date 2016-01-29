package net.sandius.rembulan.compiler.gen.block;

import net.sandius.rembulan.util.Check;

public abstract class Linear implements Node, Sink, Src {

	private Src prev;
	private Sink next;

	public Linear() {
		this.prev = Nodes.DUMMY_SRC;
		this.next = Nodes.DUMMY_SINK;
	}

	@Override
	public Src prev() {
		return prev;
	}

	@Override
	public void prependSource(Src that) {
		Check.notNull(that);
		this.setPrev(that);
		that.setNext(this);
	}

	@Override
	public void appendSink(Sink that) {
		Check.notNull(that);
		this.setNext(that);
		that.setPrev(this);
	}

	@Override
	public Src appendLinear(Linear that) {
		Check.notNull(that);
		appendSink(that);
		return that;
	}

	public void insertAfter(Src n) {
		Sink next = n.next();
		n.appendSink(this);
		next.prependSource(this);
	}

	public void insertBefore(Sink n) {
		Src prev = n.prev();
		prev.appendSink(this);
		n.prependSource(this);
	}

	@Override
	public Sink next() {
		return next;
	}

	@Override
	public void setNext(Sink n) {
		Check.notNull(n);
		this.next = n;
	}

	@Override
	public void setPrev(Src n) {
		Check.notNull(n);
		this.prev = n;
	}

	public void remove() {
		Src p = this.prev();
		Sink n = this.next();

		p.setNext(n);
		n.setPrev(p);

		this.setPrev(Nodes.DUMMY_SRC);
		this.setNext(Nodes.DUMMY_SINK);
	}

	public void replaceWith(Linear replacement) {
		Src p = this.prev();
		Sink n = this.next();

		p.setNext(replacement);
		replacement.setPrev(p);
		replacement.setNext(n);
		n.setPrev(replacement);

		this.setPrev(Nodes.DUMMY_SRC);
		this.setNext(Nodes.DUMMY_SINK);
	}

	@Override
	public void accept(NodeVisitor visitor) {
		if (visitor.visitNode(this)) {
			visitor.visitEdge(this, next);
			next.accept(visitor);
		}
	}

}