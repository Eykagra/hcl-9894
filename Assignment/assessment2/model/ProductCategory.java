package assessment2.model;

/** The three kinds of products sold in the shopping mall. */
public enum ProductCategory {

    CLOTHES("Clothes", "Size (XS, S, M, L, XL, XXL)"),
    ELECTRONIC("Electronic", "Warranty in months"),
    TOYS("Toys", "Minimum age in years");

    private final String displayName;
    private final String extraDetailLabel;      // extra detail asked for this category

    ProductCategory(String displayName, String extraDetailLabel) {
        this.displayName = displayName;
        this.extraDetailLabel = extraDetailLabel;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getExtraDetailLabel() {
        return extraDetailLabel;
    }
}
