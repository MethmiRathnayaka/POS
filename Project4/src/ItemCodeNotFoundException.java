// Custom exception for item not found
class ItemCodeNotFoundException extends RuntimeException {
    public ItemCodeNotFoundException(String message) {
        super(message);
    }
}
