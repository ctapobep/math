package abstractalgebra.abstractions;

import static org.junit.Assert.assertEquals;

public class GroupAssert<T, OP extends MonotypicalGroupOp<T>> {
    private final ValueGenerator<T> generator;
    private final Group<T> group;

    public GroupAssert(Group<T> group, ValueGenerator<T> generator) {
        this.generator = generator;
        this.group = group;
    }

    /**
     * aka Commutative Group
     */
    public void assertIsAbelianGroup() {
        assertIsGroup();
        assertCommutative();
    }
    public void assertIsGroup() {
        // monoids are associative and have identity element
        new MonoidAssert<>(group.toMonoid(), generator).assertIsMonoid();
        new InvertibilityAssert<>(group.getOp(), generator).assertHasInverse();
    }

    private void assertCommutative() {
        GroupElement<T> a = group.create(generator.generate());
        GroupElement<T> b = group.create(generator.generate());
        assertEquals(a.add(b), b.add(a));
    }
}