package javavanila.classes.inner;

class InnerClassesMyOuter {
    private int outerValue = 7;

    InnerClassesMyInner makeInner() {
        return new InnerClassesMyInner();
    }

    class InnerClassesMyInner {
        private int outerValue = 88;

        public int getInnerValue() {
            return outerValue;
        }

        int getOuterValue() {
            return InnerClassesMyOuter.this.outerValue;
        }
    }

    static class InnerClassesMyInnerStatic {
        private int innerValue = 99;

        public int getInnerValue() {
            return innerValue;
        }
    }

    int getOuterValue() {
        return outerValue;
    }
}