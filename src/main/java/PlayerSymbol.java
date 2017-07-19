public enum PlayerSymbol {
    NEITHER {
        public boolean isEmpty() {
            return true;
        }
    },

    X {
        public boolean isEmpty() {
            return false;
        }
    },

    O {
        public boolean isEmpty() {
            return false;
        }
    };

    public abstract boolean isEmpty();
}
