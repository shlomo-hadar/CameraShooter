package CustomCard;

public class ElementInfo {

    private final String elementName;
    private final int elementPrice, elementUrlImage;

    public ElementInfo(String elementName, int elementPrice, int elementUrlImage) {
        this.elementName = elementName;
        this.elementPrice = elementPrice;
        this.elementUrlImage = elementUrlImage;
    }

    public String getElementName() {
        return elementName;
    }

    public int getElementPrice() {
        return elementPrice;
    }

    public int getElementUrlImage() {
        return elementUrlImage;
    }

}
