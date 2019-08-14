package ua.nure.hordiienko.practice7.entity;

import java.math.BigInteger;

public class KnifeVisual {

	   
    protected KnifeVisual.KnifeBlade knifeBlade;

    protected KnifeVisual.KnifeHandle knifeHandle;

    /**
     * Gets the value of the knifeBlade property.
     *
     * @return possible object is
     * {@link KnifeDesc.KnifeVisual.KnifeBlade }
     */
    public KnifeVisual.KnifeBlade getKnifeBlade() {
        return knifeBlade;
    }

    /**
     * Sets the value of the knifeBlade property.
     *
     * @param value allowed object is
     *              {@link KnifeDesc.KnifeVisual.KnifeBlade }
     */
    public void setKnifeBlade(KnifeVisual.KnifeBlade value) {
        this.knifeBlade = value;
    }

    /**
     * Gets the value of the knifeHandle property.
     *
     * @return possible object is
     * {@link KnifeDesc.KnifeVisual.KnifeHandle }
     */
    public KnifeVisual.KnifeHandle getKnifeHandle() {
        return knifeHandle;
    }

    /**
     * Sets the value of the knifeHandle property.
     *
     * @param value allowed object is
     *              {@link KnifeDesc.KnifeVisual.KnifeHandle }
     */
    public void setKnifeHandle(KnifeVisual.KnifeHandle value) {
        this.knifeHandle = value;
    }

    public static class KnifeBlade {

        protected BigInteger length;
     
        protected BigInteger width;
  
        protected String metal;

        /**
         * Gets the value of the length property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getLength() {
            return length;
        }

        /**
         * Sets the value of the length property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setLength(BigInteger value) {
            this.length = value;
        }

        /**
         * Gets the value of the width property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getWidth() {
            return width;
        }

        /**
         * Sets the value of the width property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setWidth(BigInteger value) {
            this.width = value;
        }

        /**
         * Gets the value of the metal property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMetal() {
            return metal;
        }

        /**
         * Sets the value of the metal property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMetal(String value) {
            this.metal = value;
        }

    }

    public static class KnifeHandle {

        protected KnifeVisual.KnifeHandle.Wood wood;
        protected Object leatherCoated;
        protected Object plastic;

        /**
         * Gets the value of the wood property.
         *
         * @return possible object is
         * {@link KnifeDesc.KnifeVisual.KnifeHandle.Wood }
         */
        public KnifeVisual.KnifeHandle.Wood getWood() {
            return wood;
        }

        /**
         * Sets the value of the wood property.
         *
         * @param value allowed object is
         *              {@link KnifeDesc.KnifeVisual.KnifeHandle.Wood }
         */
        public void setWood(KnifeVisual.KnifeHandle.Wood value) {
            this.wood = value;
        }

        /**
         * Gets the value of the leatherCoated property.
         *
         * @return possible object is
         * {@link Object }
         */
        public Object getLeatherCoated() {
            return leatherCoated;
        }

        /**
         * Sets the value of the leatherCoated property.
         *
         * @param value allowed object is
         *              {@link Object }
         */
        public void setLeatherCoated(Object value) {
            this.leatherCoated = value;
        }

        /**
         * Gets the value of the plastic property.
         *
         * @return possible object is
         * {@link Object }
         */
        public Object getPlastic() {
            return plastic;
        }

        /**
         * Sets the value of the plastic property.
         *
         * @param value allowed object is
         *              {@link Object }
         */
        public void setPlastic(Object value) {
            this.plastic = value;
        }


        public static class Wood {

            protected String type;

            /**
             * Gets the value of the type property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setType(String value) {
                this.type = value;
            }
        }
    }
}