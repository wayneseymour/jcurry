/*
   Copyright 2016 Anderson Dorow

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package jcurry.util.function;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToLongFunction;

public interface CurryingToLongFunction<T> extends ToLongFunction<T> {

    default CurryingLongSupplier curry(T t) {
        return () -> this.applyAsLong(t);
    }

    default <V> CurryingToLongFunction<V> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (v) -> this.applyAsLong(before.apply(v));
    }

    default <V, U> CurryingToLongBiFunction<V, U> compose(BiFunction<? super V, ? super U, ? extends T> before) {
        Objects.requireNonNull(before);
        return (v, u) -> this.applyAsLong(before.apply(v, u));
    }

}
