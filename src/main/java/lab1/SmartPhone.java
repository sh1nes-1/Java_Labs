package lab1;

import lab1.model.Color;

/**
 * Class, that represents characteristics of SmartPhone
 * Can be created using pattern Builder
 * Example: SmartPhone smartPhone = SmartPhone.newBuilder()
 *                 .setColor(new Color(255, 0, 0))
 *                 .setRam(2048)
 *                 .build();
 */
public class SmartPhone {

    // TODO: Конкретно вибрати щось зі смартфонами. (Які саме поля і методи краще в нього, чи треба наслідування)
    // TODO: Processor, Graphic Processor, Camera, Resolution, OS, Inheritance?
    // TODO: equals(), hashCode(), toString(), some collection

    private Color color;
    private int ram;

    {
        //TODO: Ask if need here init color with new Color(0,0,0)
    }

    private SmartPhone() {
        // Private constructor to deny creating new instance outside by constructor
    }

    public Color getColor() {
        return color;
    }

    /**
     *
     * @return RAM capacity in MegaBytes
     */
    public int getRam() {
        return ram;
    }

    /**
     * Method that creates new instance of Builder
     * @return new instance of class Builder
     */
    public static Builder newBuilder() {
        return new SmartPhone().new Builder();
    }


    public class Builder {

        private Builder() {
            // Private constructor. To create instance, use SmartPhone.newBuilder();
        }

        public Builder setColor(Color color) {
            SmartPhone.this.color = color;
            return this;
        }

        /**
         *
         * @param ram capacity in MegaBytes
         */
        public Builder setRam(int ram) {
            SmartPhone.this.ram = ram;
            return this;
        }

        /**
         * Use it after calling all setters
         * @return instance of SmartPhone
         */
        public SmartPhone build() {
            return SmartPhone.this;
        }

    }

}
