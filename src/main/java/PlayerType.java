public enum PlayerType {
    COMPUTER, HUMAN;

    public boolean isHuman() {
        return this == PlayerType.HUMAN;
    }
}
