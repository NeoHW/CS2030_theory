import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Optional;

class CachedString {
	private final Supplier<String> supplier;
	// cache the computed value so that subsequent calls to get() do not re-compute the value unnecessarily.
	private Optional<String> value;

	CachedString(Supplier<String> supplier) {
		this.supplier = supplier;
		this.value = Optional.empty(); 
	}

	CachedString map(Function<? super String, String> mapper) {
		return new CachedString(() -> 
			mapper.apply(supplier.get())
		);
	}

	CachedString flatMap(Function<? super String, CachedString> mapper) {
		return new CachedString(() -> 
			mapper.apply(this.get()).get()
		);
	}

	public void forEach(Consumer<String> action) {
        action.accept(get());
    }

	String get() {
		if (value.isEmpty()) {
			value = Optional.of(supplier.get());
		}
		return value.map(x -> x).orElseThrow(); // same as value.get()
	}

	boolean isEmpty() {
		return this.get().equals("");
	}
}
