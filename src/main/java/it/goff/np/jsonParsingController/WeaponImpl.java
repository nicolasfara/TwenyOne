package it.goff.np.jsonParsingController;

/**
 *
 */
public class WeaponImpl implements Weapon {

    private String name;
    private String url;

    /**
     *
     * @param wb the builder for this object's type.
     */
    public WeaponImpl(final WeaponBuilder wb) {
        this.name = wb.name;
        this.url = wb.url;
    }

    /**
     * @return the weapon's name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the weapon's url image.
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Static inner class for a weapon's builder.
     */
    public static class WeaponBuilder {
        private boolean isBuilded = false;
        private String name;
        private String url;

        /**
         * Empty builder constructor.
         */
        public WeaponBuilder() { }

        /**
         * Add a name to the object to build.
         * @param name The weapon's name.
         * @return the instance class.
         */
        public WeaponBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         *
         * @param url The weapon's url image.
         * @return the instance class.
         */
        public WeaponBuilder url(final String url) {
            this.url = url;
            return this;
        }

        /**
         *
         * @return the weapon object built.
         */
        public Weapon build() {
            if (isBuilded) {
                throw new IllegalStateException();
            }
            isBuilded = true;
            return new WeaponImpl(this);
        }
    }
}
