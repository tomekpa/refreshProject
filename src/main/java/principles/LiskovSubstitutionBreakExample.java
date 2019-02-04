package principles;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;

/**
 * The answer depends on mutability. If your rectangle and square classes are immutable,
 * then Square is really a subtype of Rectangle and it's perfectly OK to derive first from second.
 * Otherwise, Rectangle and Square could both expose an IRectangle with no mutators, but deriving
 * one from the other is wrong since neither type is properly a subtype of the other.
 */
class LiskovSubstitutionBreakExample {

    static int calculateArea(Rectangle r) {
        return r.getArea();
    }

    @Getter
    @Setter
    static class Rectangle {
        private int length;
        private int breadth;

        public int getArea() {
            return this.length * this.breadth;
        }
    }

    static class Square extends Rectangle {

        @Override
        public void setBreadth(int breadth) {
            super.setBreadth(breadth);
            super.setLength(breadth);
        }

        @Override
        public void setLength(int length) {
            super.setLength(length);
            super.setBreadth(length);
        }
    }
}
